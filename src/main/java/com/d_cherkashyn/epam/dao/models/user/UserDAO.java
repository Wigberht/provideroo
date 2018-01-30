package com.d_cherkashyn.epam.dao.models.user;

import com.d_cherkashyn.epam.dao.DAOException;
import com.d_cherkashyn.epam.model.Roles;
import com.d_cherkashyn.epam.model.User;

import java.util.List;

public interface UserDAO {
    
    List<User> all() throws DAOException;
    
    String getUpdateTime(long id) throws DAOException;
    
    User find(Long id) throws DAOException;
    
    User find(String login) throws DAOException;
    
    User find(String login, String password) throws DAOException;
    
    List<User> find(Roles role) throws DAOException;
    
    List<User> findChatMembers(long chatId) throws DAOException;
    
    boolean delete(Long id) throws DAOException;
    
    boolean delete(String login) throws DAOException;
    
    boolean update(User user) throws DAOException;
    
    boolean setBanned(long userId, boolean banned) throws DAOException;
    
    User create(User user) throws DAOException;
}
