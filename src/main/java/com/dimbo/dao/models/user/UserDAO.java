package com.dimbo.dao.models.user;

import com.dimbo.dao.DAOException;
import com.dimbo.model.Roles;
import com.dimbo.model.User;

import java.util.List;

public interface UserDAO {
    
    public User find(Long id) throws DAOException;
    
    public User find(String login) throws DAOException;
    
    public User find(String login, String password) throws DAOException;
    
    public List<User> find(Roles role) throws DAOException;
    
    public boolean delete(Long id) throws DAOException;
    
    public boolean delete(String login) throws DAOException;
    
    public boolean update(User user) throws DAOException;
    
    public boolean setBanned(long userId, boolean banned) throws DAOException;
    
    public User create(User user) throws DAOException;
}
