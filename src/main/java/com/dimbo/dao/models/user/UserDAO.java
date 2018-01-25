package com.dimbo.dao.models.user;

import com.dimbo.dao.DAOException;
import com.dimbo.model.Roles;
import com.dimbo.model.User;

import java.util.List;

public interface UserDAO {
    
    List<User> all() throws DAOException;
    
    User find(Long id) throws DAOException;
    
    User find(String login) throws DAOException;
    
    User find(String login, String password) throws DAOException;
    
    List<User> find(Roles role) throws DAOException;
    
    boolean delete(Long id) throws DAOException;
    
    boolean delete(String login) throws DAOException;
    
    boolean update(User user) throws DAOException;
    
    boolean setBanned(long userId, boolean banned) throws DAOException;
    
    User create(User user) throws DAOException;
}
