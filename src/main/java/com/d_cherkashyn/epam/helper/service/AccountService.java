package com.d_cherkashyn.epam.helper.service;

import com.d_cherkashyn.epam.dao.factory.DAOFactory;
import com.d_cherkashyn.epam.model.Account;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AccountService {
    Logger LOGGER = LoggerFactory.getLogger(AccountService.class);
    
    public static boolean withdrawMoney(Account account, double amount) {
        if (account.withdraw(amount)) {
            return DAOFactory.getFactory()
                             .makeAccountDAO()
                             .update(account);
        } else {
            return false;
        }
        
    }
}
