package com.dimbo.dao.models.user;

import com.dimbo.dao.general.DAOException;
import com.dimbo.model.User;

public interface UserDAO {
    public User find(Long id) throws DAOException;
    
    public User find(String login, String password) throws DAOException;
}
