package com.d_cherkashyn.epam.dao.factory;

import com.d_cherkashyn.epam.dao.models.account.AccountDAO;
import com.d_cherkashyn.epam.dao.models.account.AccountDAOMySQL;
import com.d_cherkashyn.epam.dao.models.chat.ChatDAO;
import com.d_cherkashyn.epam.dao.models.chat.ChatDAOMySQL;
import com.d_cherkashyn.epam.dao.models.message.MessageDAO;
import com.d_cherkashyn.epam.dao.models.message.MessageDAOMySQL;
import com.d_cherkashyn.epam.dao.models.role.RoleDAO;
import com.d_cherkashyn.epam.dao.models.role.RoleDAOMySQL;
import com.d_cherkashyn.epam.dao.models.service.ServiceDAO;
import com.d_cherkashyn.epam.dao.models.service.ServiceDAOMySQL;
import com.d_cherkashyn.epam.dao.models.subscriber.SubscriberDAO;
import com.d_cherkashyn.epam.dao.models.subscriber.SubscriberDAOMySQL;
import com.d_cherkashyn.epam.dao.models.subscription.SubscriptionDAO;
import com.d_cherkashyn.epam.dao.models.subscription.SubscriptionDAOMySQL;
import com.d_cherkashyn.epam.dao.models.tariff.TariffDAO;
import com.d_cherkashyn.epam.dao.models.tariff.TariffDAOMySQL;
import com.d_cherkashyn.epam.dao.models.user.UserDAO;
import com.d_cherkashyn.epam.dao.models.user.UserDAOMySQL;

import java.sql.Connection;

/**
 * Implementation of Abstract factory to provide support for MySQL database
 */
public class MySQLDAOFactory implements DAOAbstractFactory {
    
    /**
     * {@inheritDoc}
     */
    @Override
    public MessageDAO makeMessageDAO(Connection connection) {
        return new MessageDAOMySQL(connection);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public UserDAO makeUserDAO(Connection connection) {
        return new UserDAOMySQL(connection);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public AccountDAO makeAccountDAO(Connection connection) {
        return new AccountDAOMySQL(connection);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public SubscriberDAO makeSubscriberDAO(Connection connection) {
        return new SubscriberDAOMySQL(connection);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public ChatDAO makeChatDAO(Connection connection) {
        return new ChatDAOMySQL(connection);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public TariffDAO makeTariffDAO(Connection connection) {
        return new TariffDAOMySQL(connection);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public ServiceDAO makeServiceDAO(Connection connection) {
        return new ServiceDAOMySQL(connection);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public RoleDAO makeRoleDAO(Connection connection) {
        return new RoleDAOMySQL(connection);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public SubscriptionDAO makeSubscriptionDAO(Connection connection) {
        return new SubscriptionDAOMySQL(connection);
    }
}