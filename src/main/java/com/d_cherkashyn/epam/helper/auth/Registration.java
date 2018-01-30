package com.d_cherkashyn.epam.helper.auth;

import com.d_cherkashyn.epam.ConnectionPool;
import com.d_cherkashyn.epam.dao.DAOException;
import com.d_cherkashyn.epam.dao.factory.DAOFactoryGenerator;
import com.d_cherkashyn.epam.dao.models.account.AccountDAO;
import com.d_cherkashyn.epam.dao.models.subscriber.SubscriberDAO;
import com.d_cherkashyn.epam.dao.models.user.UserDAO;
import com.d_cherkashyn.epam.helper.Passwords;
import com.d_cherkashyn.epam.model.Account;
import com.d_cherkashyn.epam.model.Roles;
import com.d_cherkashyn.epam.model.Subscriber;
import com.d_cherkashyn.epam.model.User;

import java.sql.Connection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class for registration of new users
 */
public class Registration {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(Registration.class);
    
    private Connection connection;
    
    /**
     * Creates instance of the class and creates connection for managing users
     */
    public Registration() {
        this.connection = ConnectionPool.getInstance()
                                        .getConnection();
    }
    
    /**
     * Closes the connection that was used in this class
     */
    public void closeConnection() {
        ConnectionPool.returnConn(this.connection);
    }
    
    /**
     * Creates a subscriber
     *
     * @param subscriber
     * @return
     */
    public Subscriber registerSubscriber(Subscriber subscriber) {
        Subscriber resultingSubscriber = null;
        SubscriberDAO subscriberDAO = DAOFactoryGenerator.getFactory()
                                                         .makeSubscriberDAO();
//        try {
        resultingSubscriber = subscriberDAO.create(subscriber);
//            LOGGER.info("Subscriber created");
//        } catch (DAOException e) {
//            LOGGER.error("Subscriber not created",e);
//        }
        
        return resultingSubscriber;
    }
    
    /**
     * Registers account for subscriber
     *
     * @return
     */
    public Account registerAccount() {
        Account resultingAccount = null;
        AccountDAO accountDAO = DAOFactoryGenerator.getFactory().makeAccountDAO();
        try {
            resultingAccount = accountDAO.create(new Account());
            LOGGER.info("User account created successfully");
        } catch (DAOException e) {
            LOGGER.error("Failure at creating user account", e);
        }
        
        return resultingAccount;
    }
    
    /**
     * Register user for general use in system
     *
     * @param user
     * @return
     */
    public User registerUser(User user) {
        UserDAO userDAO = DAOFactoryGenerator.getFactory()
                                             .makeUserDAO();
        
        user.setPassword(Passwords.getPasswordHash(user.getPassword()));
        user.setBanned(false);
        user.setRoleId(Roles.SUBSCRIBER.getId());
        
        try {
            user = userDAO.create(user);
            LOGGER.info("User '" + user.getLogin() + "' created successfully");
        } catch (DAOException e) {
            LOGGER.error("Unable to register user");
        }
        
        return user;
    }
    
}
