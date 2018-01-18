package com.dimbo.helper.service;

import com.dimbo.dao.factory.FactoryGenerator;

public class UserService extends ServiceHelper {
    
    public boolean setBanned(long userId,boolean banned) {
        return FactoryGenerator.getFactory()
                                          .makeUserDAO(connection)
                                          .setBanned(userId, banned);
    }
    
}
