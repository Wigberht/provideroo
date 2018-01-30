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
    
    private AccountDAOMySQL accountDAOMySQL;
    private ChatDAOMySQL chatDAOMySQL;
    private MessageDAOMySQL messageDAOMySQL;
    private RoleDAOMySQL roleDAOMySQL;
    private ServiceDAOMySQL serviceDAOMySQL;
    private SubscriberDAOMySQL subscriberDAOMySQL;
    private SubscriptionDAOMySQL subscriptionDAOMySQL;
    private TariffDAOMySQL tariffDAOMySQL;
    private UserDAOMySQL userDAOMySQL;
    
    MySQLDAO() {
        accountDAOMySQL = new AccountDAOMySQL();
        chatDAOMySQL = new ChatDAOMySQL();
        messageDAOMySQL = new MessageDAOMySQL();
        roleDAOMySQL = new RoleDAOMySQL();
        serviceDAOMySQL = new ServiceDAOMySQL();
        subscriberDAOMySQL = new SubscriberDAOMySQL();
        subscriptionDAOMySQL = new SubscriptionDAOMySQL();
        tariffDAOMySQL = new TariffDAOMySQL();
        userDAOMySQL = new UserDAOMySQL();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public MessageDAO makeMessageDAO() {
        return this.messageDAOMySQL;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public UserDAO makeUserDAO() {
        return this.userDAOMySQL;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public AccountDAO makeAccountDAO() {
        return this.accountDAOMySQL;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public SubscriberDAO makeSubscriberDAO() {
        return this.subscriberDAOMySQL;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public ChatDAO makeChatDAO() {
        return this.chatDAOMySQL;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public TariffDAO makeTariffDAO() {
        return this.tariffDAOMySQL;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public ServiceDAO makeServiceDAO() {
        return this.serviceDAOMySQL;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public RoleDAO makeRoleDAO() {
        return this.roleDAOMySQL;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public SubscriptionDAO makeSubscriptionDAO() {
        return this.subscriptionDAOMySQL;
    }
}