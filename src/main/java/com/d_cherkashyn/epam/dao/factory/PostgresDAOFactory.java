package com.d_cherkashyn.epam.dao.factory;

import com.d_cherkashyn.epam.dao.models.account.AccountDAO;
import com.d_cherkashyn.epam.dao.models.chat.ChatDAO;
import com.d_cherkashyn.epam.dao.models.message.MessageDAO;
import com.d_cherkashyn.epam.dao.models.role.RoleDAO;
import com.d_cherkashyn.epam.dao.models.service.ServiceDAO;
import com.d_cherkashyn.epam.dao.models.subscriber.SubscriberDAO;
import com.d_cherkashyn.epam.dao.models.subscription.SubscriptionDAO;
import com.d_cherkashyn.epam.dao.models.tariff.TariffDAO;
import com.d_cherkashyn.epam.dao.models.user.UserDAO;

import java.sql.Connection;

/**
 * Implementation of Abstract factory to provide support for PostgreSQL database
 */
public class PostgresDAOFactory implements DAOAbstractFactory {
    
    /**
     * {@inheritDoc}
     */
    @Override
    public MessageDAO makeMessageDAO(Connection connection) {
        return null;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public UserDAO makeUserDAO(Connection connection) {
        return null;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public AccountDAO makeAccountDAO(Connection connection) {
        return null;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public SubscriberDAO makeSubscriberDAO(Connection connection) {
        return null;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public ChatDAO makeChatDAO(Connection connection) {
        return null;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public TariffDAO makeTariffDAO(Connection connection) {
        return null;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public ServiceDAO makeServiceDAO(Connection connection) {
        return null;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public RoleDAO makeRoleDAO(Connection connection) {
        return null;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public SubscriptionDAO makeSubscriptionDAO(Connection connection) {
        return null;
    }
}
