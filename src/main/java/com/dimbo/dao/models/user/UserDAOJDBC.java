package com.dimbo.dao.models.user;

import com.dimbo.dao.general.DAOException;
import com.dimbo.dao.general.DAOFactory;
import com.dimbo.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.dimbo.dao.models.DAOCommon.*;

public class UserDAOJDBC implements UserDAO {
    private static final String FIND_BY_ID = "" +
            "SELECT * from user WHERE id = ?";
    private static final String FIND_BY_LOGIN_AND_PASSWORD = "" +
            "SELECT * from user WHERE login = ? AND password = ?";
    
    private DAOFactory daoFactory;
    
    public UserDAOJDBC(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }
    
    public User find(Long id) throws DAOException {
        return find(FIND_BY_ID, id);
    }
    
    @Override
    public User find(String login, String password) throws DAOException {
        return find(FIND_BY_LOGIN_AND_PASSWORD, login, password);
    }
    
    private User find(String sql, Object... values) throws DAOException {
        User user = null;
        
        try (
                Connection connection = daoFactory.getConnection();
                PreparedStatement statement = prepareStatement(connection, sql, false, values);
                ResultSet resultSet = statement.executeQuery();
        ) {
            if (resultSet.next()) {
                user = map(resultSet);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        
        return user;
    }
    
    private static User map(ResultSet resultset) throws SQLException {
        User user = new User();
        
        user.setId(resultset.getLong("id"));
        user.setLogin(resultset.getString("login"));
        user.setBanned(resultset.getBoolean("banned"));
        user.setRole_id(resultset.getLong("role_id"));
        
        return user;
    }
}
