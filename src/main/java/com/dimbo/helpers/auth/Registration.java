package com.dimbo.helpers.auth;

import com.dimbo.ConnectionPool;
import com.dimbo.dao.DAOException;
import com.dimbo.dao.factory.FactoryGenerator;
import com.dimbo.dao.models.account.AccountDAO;
import com.dimbo.dao.models.subscriber.SubscriberDAO;
import com.dimbo.dao.models.user.UserDAO;
import com.dimbo.helpers.Passwords;
import com.dimbo.model.Account;
import com.dimbo.model.Roles;
import com.dimbo.model.Subscriber;
import com.dimbo.model.User;
import java.sql.Connection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Registration {

    private static final Logger LOGGER = LoggerFactory.getLogger(Registration.class);

    private Connection connection;

    public Registration() {
        this.connection = ConnectionPool.getInstance().getConnection();
    }

    public void closeConnection() {
        ConnectionPool.returnConn(this.connection);
    }

    public Subscriber registerSubscriber(Subscriber subscriber) {
        Subscriber resultingSubscriber = null;
        SubscriberDAO subscriberDAO = FactoryGenerator.getFactory()
                                                      .makeSubscriberDAO(connection);
        try {
            resultingSubscriber = subscriberDAO.create(subscriber);
            LOGGER.info("Subscriber created");
        } catch (DAOException e) {
            LOGGER.error("Subscriber failed to be created");
        }

        return resultingSubscriber;
    }

    public Account registerAccount() {
        Account resultingAccount = null;
        AccountDAO accountDAO = FactoryGenerator.getFactory()
                                                .makeAccountDAO(connection);
        try {
            resultingAccount = accountDAO.create(new Account());
            LOGGER.info("User account created successfully");
        } catch (DAOException e) {
            LOGGER.error("Failure at creating user account", e);
        }

        return resultingAccount;
    }

    public User registerUser(User user) {
        UserDAO userDAO = FactoryGenerator.getFactory()
                                          .makeUserDAO(connection);
        User resultingUser = null;

        try {
            resultingUser = userDAO.create(
                new User(
                    user.getLogin(),
                    Passwords.getPasswordHash(user.getPassword()),
                    false,
                    Roles.SUBSCRIBER.getId()
                )
            );
            LOGGER.info("User " + user.getLogin() + " created successfully");
        } catch (DAOException e) {
            LOGGER.error("Unable to register user");
        }

        return resultingUser;
    }

}
