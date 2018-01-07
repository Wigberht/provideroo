package com.dimbo.helpers.auth;

import com.dimbo.ConnectionPool;
import com.dimbo.dao.factory.FactoryGenerator;
import com.dimbo.dao.models.user.UserDAO;
import com.dimbo.helpers.Passwords;
import com.dimbo.model.User;
import java.sql.Connection;

public class Login {

    public static User login(User user) {
        Connection conn = ConnectionPool.conn();
        UserDAO userDAO = FactoryGenerator.getFactory().makeUserDAO(conn);

        User DBUser = userDAO.find(user.getLogin());

        if (DBUser != null && Passwords.checkPassword(user.getPassword(), DBUser.getPassword())) {
            return DBUser;
        }

        return null;
    }
}
