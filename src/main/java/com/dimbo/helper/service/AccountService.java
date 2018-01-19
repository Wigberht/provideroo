package com.dimbo.helper.service;

import com.dimbo.dao.factory.FactoryGenerator;
import com.dimbo.model.Account;
import com.dimbo.model.Subscriber;
import com.dimbo.model.Subscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.util.List;

public class AccountService extends ServiceHelper {
    Logger LOGGER = LoggerFactory.getLogger(AccountService.class);
    
    
    public AccountService() {
        super();
    }
    
    public AccountService(Connection connection) {
        super(connection);
    }
    
    
    public boolean withdrawMoney(Account account, double amount) {
        if (account.withdraw(amount)) {
            return FactoryGenerator.getFactory()
                                   .makeAccountDAO(connection)
                                   .update(account);
        } else {
            return false;
        }
        
    }
}
