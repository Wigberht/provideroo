package com.dimbo.helpers.auth;

import com.dimbo.ConnectionPool;
import com.dimbo.dao.factory.FactoryGenerator;
import com.dimbo.dao.models.user.UserDAO;
import com.dimbo.helpers.Passwords;
import com.dimbo.model.Roles;
import com.dimbo.model.User;
import com.sun.deploy.net.HttpRequest;
import java.sql.Connection;

public class Auth {

    public static User login(String login, String password) {
        User user = null;
        Connection conn = ConnectionPool.conn();
        UserDAO userDAO = FactoryGenerator.getFactory().makeUserDAO(conn);

        User DBUser = userDAO.find(login);

        if (DBUser != null && Passwords.checkPassword(password, DBUser.getPassword())) {
            user = DBUser;
        }

        ConnectionPool.returnConn(conn);

        return user;
    }

    public static User register(User user) {
        Connection connection = ConnectionPool.conn();
        UserDAO userDAO = FactoryGenerator.getFactory().makeUserDAO(connection);

        user.setPassword(Passwords.getPasswordHash(user.getPassword()));
        user.setBanned(false);
        user.setRoleId(Roles.SUBSCRIBER.getId());

        userDAO.create(user);

        ConnectionPool.returnConn(connection);

        return user;
    }
}