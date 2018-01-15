package com.dimbo.helper.auth;

import com.dimbo.ConnectionPool;
import com.dimbo.dao.factory.FactoryGenerator;
import com.dimbo.dao.models.user.UserDAO;
import com.dimbo.helper.Passwords;
import com.dimbo.model.User;

import java.sql.Connection;

public class Auth {
    
    public static User login(String login, String password) {
        User user = null;
        Connection conn = ConnectionPool.getInstance()
                                        .getConnection();
        UserDAO userDAO = FactoryGenerator.getFactory()
                                          .makeUserDAO(conn);
        
        User DBUser = userDAO.find(login);
        
        if (DBUser != null && Passwords.checkPassword(password, DBUser.getPassword())) {
            user = DBUser;
        }
        
        ConnectionPool.getInstance()
                      .returnConnection(conn);
        
        return user;
    }
}