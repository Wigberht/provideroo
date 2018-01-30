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

/**
 * Implementation of Abstract factory to provide support for MySQL database
 */
public class MySQLDAO implements AbstractDAO {
    
    /**
     * {@inheritDoc}
     */
    @Override
    public MessageDAO makeMessageDAO() {
        return new MessageDAOMySQL();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public UserDAO makeUserDAO() {
        return new UserDAOMySQL();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public AccountDAO makeAccountDAO() {
        return new AccountDAOMySQL();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public SubscriberDAO makeSubscriberDAO() {
        return new SubscriberDAOMySQL();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public ChatDAO makeChatDAO() {
        return new ChatDAOMySQL();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public TariffDAO makeTariffDAO() {
        return new TariffDAOMySQL();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public ServiceDAO makeServiceDAO() {
        return new ServiceDAOMySQL();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public RoleDAO makeRoleDAO() {
        return new RoleDAOMySQL();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public SubscriptionDAO makeSubscriptionDAO() {
        return new SubscriptionDAOMySQL();
    }
}