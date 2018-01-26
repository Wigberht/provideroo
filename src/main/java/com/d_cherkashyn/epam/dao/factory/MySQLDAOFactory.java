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

public class MySQLDAOFactory implements DAOAbstractFactory {
    
    @Override
    public MessageDAO makeMessageDAO(Connection connection) {
        return new MessageDAOMySQL(connection);
    }
    
    @Override
    public UserDAO makeUserDAO(Connection connection) {
        return new UserDAOMySQL(connection);
    }
    
    @Override
    public AccountDAO makeAccountDAO(Connection connection) {
        return new AccountDAOMySQL(connection);
    }
    
    @Override
    public SubscriberDAO makeSubscriberDAO(Connection connection) {
        return new SubscriberDAOMySQL(connection);
    }
    
    @Override
    public ChatDAO makeChatDAO(Connection connection) {
        return new ChatDAOMySQL(connection);
    }
    
    @Override
    public TariffDAO makeTariffDAO(Connection connection) {
        return new TariffDAOMySQL(connection);
    }
    
    @Override
    public ServiceDAO makeServiceDAO(Connection connection) {
        return new ServiceDAOMySQL(connection);
    }
    
    @Override
    public RoleDAO makeRoleDAO(Connection connection) {
        return new RoleDAOMySQL(connection);
    }
    
    @Override
    public SubscriptionDAO makeSubscriptionDAO(Connection connection) {
        return new SubscriptionDAOMySQL(connection);
    }
}
