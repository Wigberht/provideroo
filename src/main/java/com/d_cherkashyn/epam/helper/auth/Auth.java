package com.d_cherkashyn.epam.helper.auth;

import com.d_cherkashyn.epam.ConnectionPool;
import com.d_cherkashyn.epam.dao.factory.FactoryGenerator;
import com.d_cherkashyn.epam.dao.models.user.UserDAO;
import com.d_cherkashyn.epam.helper.Passwords;
import com.d_cherkashyn.epam.model.User;

import java.sql.Connection;

/**
 * Helper class that provides functionality to authenticate user
 */
public class Auth {
    
    /**
     * Log-ins the user
     *
     * @param login
     * @param password
     * @return
     */
    public static User login(String login, String password) {
        User user = null;
        
        Connection conn = ConnectionPool.conn();
        UserDAO userDAO = FactoryGenerator.getFactory()
                                          .makeUserDAO();
        
        User DBUser = userDAO.find(login);
        
        if (DBUser != null && Passwords.checkPassword(password, DBUser.getPassword())) {
            user = DBUser;
        }
        
        ConnectionPool.returnConn(conn);
        
        return user;
    }
}