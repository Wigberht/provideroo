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

/**
 * Implementation of Abstract factory to provide support for PostgreSQL database
 */
public class PostgresDAO implements AbstractDAO {
    
    /**
     * {@inheritDoc}
     */
    @Override
    public MessageDAO makeMessageDAO() {
        return null;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public UserDAO makeUserDAO() {
        return null;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public AccountDAO makeAccountDAO() {
        return null;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public SubscriberDAO makeSubscriberDAO() {
        return null;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public ChatDAO makeChatDAO() {
        return null;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public TariffDAO makeTariffDAO() {
        return null;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public ServiceDAO makeServiceDAO() {
        return null;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public RoleDAO makeRoleDAO() {
        return null;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public SubscriptionDAO makeSubscriptionDAO() {
        return null;
    }
}
