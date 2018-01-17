package com.dimbo.dao.factory;

import com.dimbo.dao.models.account.AccountDAO;
import com.dimbo.dao.models.account.AccountDAOMySQL;
import com.dimbo.dao.models.chat.ChatDAO;
import com.dimbo.dao.models.chat.ChatDAOMySQL;
import com.dimbo.dao.models.message.MessageDAO;
import com.dimbo.dao.models.message.MessageDAOMySQL;
import com.dimbo.dao.models.role.RoleDAO;
import com.dimbo.dao.models.role.RoleDAOMySQL;
import com.dimbo.dao.models.service.ServiceDAO;
import com.dimbo.dao.models.service.ServiceDAOMySQL;
import com.dimbo.dao.models.subscriber.SubscriberDAO;
import com.dimbo.dao.models.subscriber.SubscriberDAOMySQL;
import com.dimbo.dao.models.tariff.TariffDAO;
import com.dimbo.dao.models.tariff.TariffDAOMySQL;
import com.dimbo.dao.models.user.UserDAO;
import com.dimbo.dao.models.user.UserDAOMySQL;

import com.dimbo.model.Service;

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
}
