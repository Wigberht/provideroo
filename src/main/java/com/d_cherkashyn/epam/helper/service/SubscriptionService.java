package com.d_cherkashyn.epam.helper.service;

import com.d_cherkashyn.epam.dao.factory.DAOFactoryGenerator;
import com.d_cherkashyn.epam.helper.TimeHelper;
import com.d_cherkashyn.epam.model.Account;
import com.d_cherkashyn.epam.model.Subscriber;
import com.d_cherkashyn.epam.model.Subscription;
import com.d_cherkashyn.epam.model.Tariff;
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
                                                     start, end, true, tariffId,
                                                     subscriber.getId()
        );
        
        boolean subscriptionCreated = false;
        if (accountService.withdrawMoney(account, tariff.getCost())) {
            subscriptionCreated = createSubscription(subscription) != null;
        }
        
        return subscriptionCreated;
    }
    
    public Subscription createSubscription(Subscription subscription) {
        return DAOFactoryGenerator.getFactory()
                                  .makeSubscriptionDAO()
                                  .create(subscription);
    }
    
    public List<Subscription> getSubscriptions(long subscriberId) {
        return DAOFactoryGenerator.getFactory()
                                  .makeSubscriptionDAO()
                                  .findBySubscriber(subscriberId);
    }
    
    public boolean setSubscriptionProlong(Subscription subscription,
                                          boolean prolong) {
        subscription.setProlong(prolong);
        
        LOGGER.info("setSubscriptionProlong " + subscription.isProlong());
        return DAOFactoryGenerator.getFactory()
                                  .makeSubscriptionDAO()
                                  .update(subscription);
    }
    
    public Subscription findSubscription(long tariffId, long subscriberId) {
        return DAOFactoryGenerator.getFactory()
                                  .makeSubscriptionDAO()
                                  .find(tariffId, subscriberId);
    }
    
    public boolean prolongSubscriptions(long subscriberId) {
        return DAOFactoryGenerator.getFactory()
                                  .makeSubscriptionDAO()
                                  .prolongSubscriptions(subscriberId);
    }
}
