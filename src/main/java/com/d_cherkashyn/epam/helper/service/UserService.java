package com.d_cherkashyn.epam.helper.service;

import com.d_cherkashyn.epam.dao.factory.FactoryGenerator;
import com.d_cherkashyn.epam.model.User;

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
                               .makeUserDAO()
                               .setBanned(userId, banned);
    }
    
    public List<User> getAllUsers() {
        return FactoryGenerator.getFactory()
                               .makeUserDAO()
                               .all();
    }
    
    
    public String getUserUpdatedTime(long id) {
        return FactoryGenerator.getFactory()
                               .makeUserDAO()
                               .getUpdateTime(id);
    }
    
    public User getUser(long id) {
        return FactoryGenerator.getFactory()
                               .makeUserDAO()
                               .find(id);
    }
}
