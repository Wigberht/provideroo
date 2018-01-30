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
 * Abstract class required to create a Factory of DAO objects
 */
public interface AbstractDAO {
    
    /**
     * Creates MessageDAO
     *
     * @return MessageDAO instance
     */
    MessageDAO makeMessageDAO();
    
    /**
     * Creates UserDAO
     *
     * @return UserDAO instance
     */
    UserDAO makeUserDAO();
    
    /**
     * Creates AccoutnDAO
     *
     * @return AccountDAO instance
     */
    AccountDAO makeAccountDAO();
    
    /**
     * Creates SubscriberDAO
     *
     * @return MessageDAO instance
     */
    SubscriberDAO makeSubscriberDAO();
    
    /**
     * Creates ChatDAO
     *
     * @return ChatDAO instance
     */
    ChatDAO makeChatDAO();
    
    /**
     * Creates TariffDAO
     *
     * @return TariffDAO instance
     */
    TariffDAO makeTariffDAO();
    
    /**
     * Creates ServiceDAO
     *
     * @return ServiceDAO instance
     */
    ServiceDAO makeServiceDAO();
    
    /**
     * Creates RoleDAO
     *
     * @return RoleDAO instance
     */
    RoleDAO makeRoleDAO();
    
    /**
     * Creates SubscriptionDAO
     *
     * @return SubscriptionDAO instance
     */
    SubscriptionDAO makeSubscriptionDAO();
    
}
