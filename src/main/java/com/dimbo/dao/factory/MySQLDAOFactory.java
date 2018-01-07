package com.dimbo.dao.factory;

import com.dimbo.dao.models.chat.ChatDAO;
import com.dimbo.dao.models.message.MessageDAO;
import com.dimbo.dao.models.message.MessageDAOMySQL;
import com.dimbo.dao.models.user.UserDAO;
import com.dimbo.dao.models.user.UserDAOMySQL;

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
    public ChatDAO makeChatDAO(Connection connection) {
        return null;
    }
}
