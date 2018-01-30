package com.d_cherkashyn.epam.helper.service;

import com.d_cherkashyn.epam.dao.factory.DAOFactory;
import com.d_cherkashyn.epam.model.Account;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;

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
            return DAOFactory.getFactory()
                             .makeAccountDAO()
                             .update(account);
        } else {
            return false;
        }
        
    }
}
