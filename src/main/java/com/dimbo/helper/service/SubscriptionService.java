package com.dimbo.helper.service;

import com.dimbo.dao.factory.FactoryGenerator;
import com.dimbo.helper.TimeHelper;
import com.dimbo.model.Account;
import com.dimbo.model.Subscriber;
import com.dimbo.model.Subscription;
import com.dimbo.model.Tariff;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;

public class SubscriptionService extends ServiceHelper {
    
    Logger LOGGER = LoggerFactory.getLogger(SubscriptionService.class);
    
    public SubscriptionService() {
        super();
    }
    
    public SubscriptionService(Connection connection) {
        super(connection);
    }
    
    public boolean createSubscription(long userId, long tariffId) {
        TariffService tariffService = new TariffService(connection);
        SubscriberService subscriberService = new SubscriberService(connection);
        AccountService accountService = new AccountService(connection);
        
        Tariff tariff = tariffService.getTariff(tariffId);
        Subscriber subscriber = subscriberService
            .findSubscriberByUserId(userId);
        
        Account account = subscriber.getAccount();
        
        /*bummer*/
        if (tariff.getCost() >= account.getBalance()) return false;
        
        LocalDate localDate = LocalDate.now();
        String start = TimeHelper.getFormattedDate(localDate);
        String end = TimeHelper
            .addDaysToDate(localDate, tariff.getNumberOfDays());
        
        Subscription subscription = new Subscription(0,
            start, end, true, tariffId, subscriber.getId()
        );
        
        boolean subscriptionCreated = false;
        if (accountService.withdrawMoney(account, tariff.getCost())) {
            subscriptionCreated = createSubscription(subscription) != null;
        }
        
        
        LOGGER.info("subscription generated");
        
        return subscriptionCreated;
    }
    
    public Subscription createSubscription(Subscription subscription) {
        return FactoryGenerator.getFactory()
                               .makeSubscriptionDAO(connection)
                               .create(subscription);
    }
    
    public List<Subscription> getSubscriptions(long subscriberId) {
        return FactoryGenerator.getFactory()
                               .makeSubscriptionDAO(connection)
                               .findBySubscriber(subscriberId);
    }
    
    public boolean setSubscriptionProlong(Subscription subscription,
                                          boolean prolong) {
        subscription = FactoryGenerator.getFactory()
                                       .makeSubscriptionDAO(connection)
                                       .find(subscription.getId());
        subscription.setProlong(prolong);
        
        return FactoryGenerator.getFactory()
                               .makeSubscriptionDAO(connection)
                               .update(subscription);
    }
    
    public Subscription findSubscription(long tariffId, long subscriberId) {
        return FactoryGenerator.getFactory()
                               .makeSubscriptionDAO(connection)
                               .find(tariffId, subscriberId);
    }
}
