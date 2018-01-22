package com.dimbo.helper.service;

import com.dimbo.dao.factory.FactoryGenerator;

import java.sql.Connection;

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
    
}
