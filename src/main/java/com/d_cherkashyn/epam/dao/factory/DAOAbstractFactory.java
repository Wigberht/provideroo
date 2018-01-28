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
 * Abstract class required to create a Factory of DAO objects
 */
public interface DAOAbstractFactory {
    
    /**
     * Creates MessageDAO
     *
     * @param connection
     * @return MessageDAO instance
     */
    MessageDAO makeMessageDAO(Connection connection);
    
    /**
     * Creates UserDAO
     *
     * @param connection
     * @return UserDAO instance
     */
    UserDAO makeUserDAO(Connection connection);
    
    /**
     * Creates AccoutnDAO
     *
     * @param connection
     * @return AccountDAO instance
     */
    AccountDAO makeAccountDAO(Connection connection);
    
    /**
     * Creates SubscriberDAO
     *
     * @param connection
     * @return MessageDAO instance
     */
    SubscriberDAO makeSubscriberDAO(Connection connection);
    
    /**
     * Creates ChatDAO
     *
     * @param connection
     * @return ChatDAO instance
     */
    ChatDAO makeChatDAO(Connection connection);
    
    /**
     * Creates TariffDAO
     *
     * @param connection
     * @return TariffDAO instance
     */
    TariffDAO makeTariffDAO(Connection connection);
    
    /**
     * Creates ServiceDAO
     *
     * @param connection
     * @return ServiceDAO instance
     */
    ServiceDAO makeServiceDAO(Connection connection);
    
    /**
     * Creates RoleDAO
     *
     * @param connection
     * @return RoleDAO instance
     */
    RoleDAO makeRoleDAO(Connection connection);
    
    /**
     * Creates SubscriptionDAO
     *
     * @param connection
     * @return SubscriptionDAO instance
     */
    SubscriptionDAO makeSubscriptionDAO(Connection connection);
    
}
