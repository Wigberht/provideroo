package com.dimbo.helper.service;

import com.dimbo.dao.factory.FactoryGenerator;
import com.dimbo.model.Account;
import com.dimbo.model.Subscriber;
import com.dimbo.model.Subscription;
import com.dimbo.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.util.List;

public class SubscriberService extends ServiceHelper {
    Logger LOGGER = LoggerFactory.getLogger(SubscriberService.class);
    
    private int limit;
    private int page;
    
    public SubscriberService() {
        super();
    }
    
    public SubscriberService(Connection connection) {
        super(connection);
    }
    
    public SubscriberService(int page, int limit) {
        this();
        this.limit = limit;
        this.page = page;
    }
    
    public SubscriberService(int page, int limit, Connection connection) {
        super(connection);
        this.limit = limit;
        this.page = page;
    }
    
    public boolean updateSubscriberProfile(Subscriber subscriber) {
        boolean subscriberUpdated = updateSubscriber(subscriber);
        boolean userUpdated = updateUser(subscriber.getUser());
        
        return subscriberUpdated && userUpdated;
    }
    
    public boolean updateAccount(Account account) {
        return FactoryGenerator.getFactory()
                               .makeAccountDAO(connection)
                               .update(account);
    }
    
    public boolean updateUser(User user) {
        return FactoryGenerator.getFactory()
                               .makeUserDAO(connection)
                               .update(user);
    }
    
    public boolean updateSubscriber(Subscriber subscriber) {
        return FactoryGenerator.getFactory()
                               .makeSubscriberDAO(connection)
                               .update(subscriber);
    }
    
    public List<Subscriber> getSubscribers() {
        int page = (this.page == 0) ? 0 : this.page - 1;
        int offset = page * limit;
        
        return FactoryGenerator.getFactory()
                               .makeSubscriberDAO(connection)
                               .all(limit, offset);
    }
    
    public Subscriber findSubscriber(long id) {
        return FactoryGenerator.getFactory()
                               .makeSubscriberDAO(connection)
                               .find(id);
    }
    
    public List<Subscriber> expiredSubscriptionSubscribers() {
        return FactoryGenerator.getFactory()
                               .makeSubscriberDAO(connection)
                               .findSubscriptionExpirers();
    }
    
    public double calculateDebt(long subscriberId) {
        return FactoryGenerator.getFactory()
                               .makeSubscriberDAO(connection)
                               .calculateDebt(subscriberId);
    }
    
    public Subscriber findSubscriberByUserId(long userId) {
        return FactoryGenerator.getFactory()
                               .makeSubscriberDAO(connection)
                               .findByUserId(userId);
    }
    
    public List<Subscription> findSubscriptions(long id) {
        return FactoryGenerator.getFactory()
                               .makeSubscriptionDAO(connection)
                               .findBySubscriber(id);
    }
    
    public double collectSubscriptionFees() {
        UserService userService = new UserService(connection);
        AccountService accountService = new AccountService(connection);
        SubscriptionService subscriptionService = new SubscriptionService(connection);
        
        double debtCollected = 0;
        List<Subscriber> subscribers = expiredSubscriptionSubscribers();
        for (Subscriber subscriber : subscribers) {
            
            double debt = calculateDebt(subscriber.getId());
            
            // if (poor) { ban(this.fella); }
            if (debt > subscriber.getAccount().getBalance()) {
                userService.setBanned(subscriber.getUser().getId(), true);
                continue;
            }
            
            if (debt > 0 && accountService.withdrawMoney(subscriber.getAccount(), debt)) {
                subscriptionService.prolongSubscriptions(subscriber.getId());
                debtCollected += debt;
            }
        }
        
        return debtCollected;
    }
    
    public boolean refreshSubscriptions(Subscriber subscriber) {
        boolean success;
        SubscriptionService subscriptionService = new SubscriptionService(connection);
        AccountService accountService = new AccountService(connection);
        
        double debt = calculateDebt(subscriber.getId());
        if (debt > subscriber.getAccount().getBalance()) {
            success = false;
        } else {
            accountService.withdrawMoney(subscriber.getAccount(), debt);
            subscriptionService.prolongSubscriptions(subscriber.getId());
            subscriber.getUser().setBanned(false);
            success = updateUser(subscriber.getUser());
        }
        
        return success;
    }
    
    public boolean updateSubscriptions() {
        return true;
    }
    
    
}
