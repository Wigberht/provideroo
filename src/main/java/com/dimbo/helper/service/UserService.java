package com.dimbo.helper.service;

import com.dimbo.dao.factory.FactoryGenerator;
import com.dimbo.model.User;

import java.sql.Connection;
import java.util.List;

public class UserService extends ServiceHelper {
    
    public UserService() {
        super();
    }
    
    public UserService(Connection connection) {
        super(connection);
    }
    
    public boolean setBanned(long userId, boolean banned) {
        return FactoryGenerator.getFactory()
                               .makeUserDAO(connection)
                               .setBanned(userId, banned);
    }
    
    public List<User> getAllUsers() {
        return FactoryGenerator.getFactory()
                               .makeUserDAO(connection)
                               .all();
    }
    
}
