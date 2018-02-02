package com.d_cherkashyn.epam.helper.service;

import com.d_cherkashyn.epam.dao.factory.DAOFactory;
import com.d_cherkashyn.epam.model.Account;
import com.d_cherkashyn.epam.model.Subscriber;
import com.d_cherkashyn.epam.model.Subscription;
import com.d_cherkashyn.epam.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class SubscriberService {
    private static final Logger LOGGER = LoggerFactory.getLogger(SubscriberService.class);
    
    private int limit;
    private int page;
    
    public static boolean updateSubscriberProfile(Subscriber subscriber) {
        boolean subscriberUpdated = updateSubscriber(subscriber);
        boolean userUpdated = updateUser(subscriber.getUser());
        
        return subscriberUpdated && userUpdated;
    }
    
    public static boolean updateAccount(Account account) {
        return DAOFactory.getFactory()
                         .makeAccountDAO()
                         .update(account);
    }
    
    public static boolean updateUser(User user) {
        return DAOFactory.getFactory()
                         .makeUserDAO()
                         .update(user);
    }
    
    public static boolean updateSubscriber(Subscriber subscriber) {
        return DAOFactory.getFactory()
                         .makeSubscriberDAO()
                         .update(subscriber);
    }
    
    public static List<Subscriber> getSubscribers(int limit, int page) {
        page = (page == 0) ? 0 : page - 1;
        LOGGER.info("Get subscribers : {}, {}", limit, page);
        int offset = page * limit;
        
        return DAOFactory.getFactory()
                         .makeSubscriberDAO()
                         .all(limit, offset);
    }
    
    public static Subscriber findSubscriber(long id) {
        return DAOFactory.getFactory()
                         .makeSubscriberDAO()
                         .find(id);
    }
    
    public static List<Subscriber> expiredSubscriptionSubscribers() {
        return DAOFactory.getFactory()
                         .makeSubscriberDAO()
                         .findSubscriptionExpirers();
    }
    
    public static double calculateDebt(long subscriberId) {
        return DAOFactory.getFactory()
                         .makeSubscriberDAO()
                         .calculateDebt(subscriberId);
    }
    
    public static long getNumberOfSubscribers() {
        return DAOFactory.getFactory()
                         .makeSubscriberDAO()
                         .numberOfSubscribers();
    }
    
    public static Subscriber findSubscriberByUserId(long userId) {
        return DAOFactory.getFactory()
                         .makeSubscriberDAO()
                         .findByUserId(userId);
    }
    
    public static List<Subscription> findSubscriptions(long id) {
        return DAOFactory.getFactory()
                         .makeSubscriptionDAO()
                         .findBySubscriber(id);
    }
    
    public static double collectSubscriptionFees() {
        double debtCollected = 0;
        
        /* get subscribers that have atleast one expired subscription */
        List<Subscriber> subscribers = expiredSubscriptionSubscribers();
        LOGGER.info("Debters: " + subscribers.size());
        for (Subscriber subscriber : subscribers) {
            double debt = calculateDebt(subscriber.getId());
            LOGGER.info("{} debt: {}", subscriber.getFirstName(), debt);
            
            // if (poor) { ban(this.fella); }
            if (debt > subscriber.getAccount().getBalance()) {
                UserService.setBanned(subscriber.getUser().getId(), true);
                continue;
            }
            
            if (debt > 0 && AccountService.withdrawMoney(subscriber.getAccount(), debt)) {
                SubscriptionService.prolongSubscriptions(subscriber.getId());
                debtCollected += debt;
            }
        }
        
        return debtCollected;
    }
    
    public static boolean refreshSubscriptions(Subscriber subscriber) {
        boolean success;
        
        double debt = calculateDebt(subscriber.getId());
        if (debt > subscriber.getAccount().getBalance()) {
            success = false;
        } else {
            AccountService.withdrawMoney(subscriber.getAccount(), debt);
            SubscriptionService.prolongSubscriptions(subscriber.getId());
            subscriber.getUser().setBanned(false);
            success = updateUser(subscriber.getUser());
        }
        
        return success;
    }
    
    public static boolean updateSubscriptions() {
        return true;
    }
    
    
    public static List<Subscriber> search(String searchQ) {
        String[] strings = searchQ.split(" ");
        String str1 = strings.length > 0 ? strings[0] : null;
        String str2 = strings.length > 1 ? strings[1] : null;
        String str3 = strings.length > 2 ? strings[2] : null;
        
        return DAOFactory.getFactory()
                         .makeSubscriberDAO()
                         .search(str1, str2, str3);
    }
    
}
