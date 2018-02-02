package com.d_cherkashyn.epam.helper.service;

import com.d_cherkashyn.epam.dao.factory.DAOFactory;
import com.d_cherkashyn.epam.model.User;

import java.util.List;

public class UserService {
    
    public static boolean setBanned(long userId, boolean banned) {
        return DAOFactory.getFactory()
                         .makeUserDAO()
                         .setBanned(userId, banned);
    }
    
    public static List<User> getAllUsers() {
        return DAOFactory.getFactory()
                         .makeUserDAO()
                         .all();
    }
    
    
    public static String getUserUpdatedTime(long id) {
        return DAOFactory.getFactory()
                         .makeUserDAO()
                         .getUpdateTime(id);
    }
    
    public static User getUser(long id) {
        return DAOFactory.getFactory()
                         .makeUserDAO()
                         .find(id);
    }
}
