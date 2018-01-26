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

public interface DAOAbstractFactory {
    
    MessageDAO makeMessageDAO(Connection connection);
    
    UserDAO makeUserDAO(Connection connection);
    
    AccountDAO makeAccountDAO(Connection connection);
    
    SubscriberDAO makeSubscriberDAO(Connection connection);
    
    ChatDAO makeChatDAO(Connection connection);
    
    TariffDAO makeTariffDAO(Connection connection);
    
    ServiceDAO makeServiceDAO(Connection connection);
    
    RoleDAO makeRoleDAO(Connection connection);
    
    SubscriptionDAO makeSubscriptionDAO(Connection connection);
    
}
