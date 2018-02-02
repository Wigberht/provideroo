package com.d_cherkashyn.epam.helper.service;

import com.d_cherkashyn.epam.dao.factory.DAOFactory;
import com.d_cherkashyn.epam.helper.TimeHelper;
import com.d_cherkashyn.epam.model.Account;
import com.d_cherkashyn.epam.model.Subscriber;
import com.d_cherkashyn.epam.model.Subscription;
import com.d_cherkashyn.epam.model.Tariff;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.List;

public class SubscriptionService {
    
    private static final Logger LOGGER = LoggerFactory
        .getLogger(SubscriptionService.class);
    
    public static boolean createSubscription(long userId, long tariffId) {
        
        Tariff tariff = TariffService.getTariff(tariffId);
        Subscriber subscriber = SubscriberService.findSubscriberByUserId(userId);
        
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
        if (AccountService.withdrawMoney(account, tariff.getCost())) {
            subscriptionCreated = createSubscription(subscription) != null;
        }
        
        return subscriptionCreated;
    }
    
    public static Subscription createSubscription(Subscription subscription) {
        return DAOFactory.getFactory()
                         .makeSubscriptionDAO()
                         .create(subscription);
    }
    
    public static List<Subscription> getSubscriptions(long subscriberId) {
        return DAOFactory.getFactory()
                         .makeSubscriptionDAO()
                         .findBySubscriber(subscriberId);
    }
    
    public static boolean setSubscriptionProlong(Subscription subscription,
                                                 boolean prolong) {
        subscription.setProlong(prolong);
        
        LOGGER.info("setSubscriptionProlong " + subscription.isProlong());
        return DAOFactory.getFactory()
                         .makeSubscriptionDAO()
                         .update(subscription);
    }
    
    public static Subscription findSubscription(long tariffId, long subscriberId) {
        return DAOFactory.getFactory()
                         .makeSubscriptionDAO()
                         .find(tariffId, subscriberId);
    }
    
    public static boolean prolongSubscriptions(long subscriberId) {
        return DAOFactory.getFactory()
                         .makeSubscriptionDAO()
                         .prolongSubscriptions(subscriberId);
    }
}
