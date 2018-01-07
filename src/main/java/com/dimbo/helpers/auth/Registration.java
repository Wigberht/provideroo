package com.dimbo.helpers.auth;

import com.dimbo.ConnectionPool;
import com.dimbo.dao.factory.FactoryGenerator;
import com.dimbo.dao.models.user.UserDAO;
import com.dimbo.helpers.Passwords;
import com.dimbo.model.Roles;
import com.dimbo.model.User;
import java.sql.Connection;
import org.mindrot.jbcrypt.BCrypt;

public class Registration {

    public static User register(User user) {
        Connection connection = ConnectionPool.conn();
        UserDAO userDAO = FactoryGenerator.getFactory().makeUserDAO(connection);

        user.setPassword(Passwords.getPasswordHash(user.getPassword()));
        user.setBanned(false);
        user.setRole_id(Roles.SUBSCRIBER.getId());

        userDAO.create(user);

        ConnectionPool.returnConn(connection);

        return user;
    }
}
