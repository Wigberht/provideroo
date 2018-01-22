package com.dimbo.dao.models.user;

import com.dimbo.dao.DAOException;
import com.dimbo.dao.models.DAOModel;
import com.dimbo.model.Roles;
import com.dimbo.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAOMySQL extends DAOModel implements UserDAO {
    Logger LOGGER = LoggerFactory.getLogger(UserDAOMySQL.class);
    
    private static final String FIND_BY_ID = "" +
        "SELECT * FROM user WHERE id = ?";
    private static final String FIND_BY_LOGIN = "" +
        "SELECT * FROM user WHERE login = ?";
    private static final String FIND_BY_LOGIN_AND_PASSWORD = "" +
        "SELECT * FROM user WHERE login = ? AND password = ?";
    private static final String FIND_BY_ROLE = ""
        + "SELECT * FROM user WHERE role_id = ?";
    
    private static final String DELETE_BY_ID = "DELETE FROM user WHERE id=?";
    private static final String DELETE_BY_LOGIN = "DELETE FROM user WHERE login=?";
    
    private static final String CREATE_USER = "INSERT INTO user VALUES(DEFAULT,?,?,?,?)";
    
    private static final String UPDATE_USER = "UPDATE user "
        + "SET login = ?, password = ?, banned = ?, role_id= ? "
        + "WHERE id = ?";
    
    private static final String UPDATE_LOGIN_WHERE_ID = "UPDATE user SET login=? WHERE id=?";
    private static final String SET_BANNED_WHERE_ID = "UPDATE user SET banned=? WHERE id=?";
    private static final String SET_BANNED_WHERE_LOGIN = "UPDATE user SET banned=? WHERE login=?";
    private static final String UPDATE_PASSWORD_WHERE_ID = "UPDATE user SET password=? WHERE id=?";
    private static final String UPDATE_PASSWORD_WHERE_LOGIN = "UPDATE user SET password=? WHERE login=?";
    
    Connection connection;
    
    public UserDAOMySQL(Connection connection) {
        this.connection = connection;
    }
    
    public User find(Long id) throws DAOException {
        return findUser(FIND_BY_ID, id);
    }
    
    public User find(String login) throws DAOException {
        return findUser(FIND_BY_LOGIN, login);
    }
    
    public User find(String login, String password) throws DAOException {
        return findUser(FIND_BY_LOGIN_AND_PASSWORD, login, password);
    }
    
    public List<User> find(Roles role) {
        return findUsers(FIND_BY_ROLE, role.getId());
    }
    
    
    @Override
    public boolean delete(Long id) throws DAOException {
        return false;
    }
    
    @Override
    public boolean delete(String login) throws DAOException {
        return false;
    }
    
    @Override
    public boolean update(User user) throws DAOException {
        try (
            PreparedStatement statement = prepareStatement(
                connection, UPDATE_USER, true,
                user.getLogin(), user.getPassword(),
                user.isBanned(), user.getRoleId(),
                user.getId()
            )
        ) {
            int updatedRows = statement.executeUpdate();
            LOGGER.info("Updated rows: " + updatedRows);
            return updatedRows > 0;
            
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }
    
    @Override
    public boolean setBanned(long userId, boolean banned) throws DAOException {
        boolean success;
        try (
            PreparedStatement statement = prepareStatement(
                connection, SET_BANNED_WHERE_ID, true,
                banned, userId)
        ) {
            int updatedRows = statement.executeUpdate();
            success = updatedRows != 0;
            
        } catch (SQLException e) {
            LOGGER.error("UserDAOMySQL#setBanned was unsuccessful");
            throw new DAOException(e);
        }
        
        return success;
    }
    
    @Override
    public User create(User user) throws DAOException {
        
        try (
            PreparedStatement statement = prepareStatement(
                connection, CREATE_USER, true,
                user.getLogin(), user.getPassword(), user.isBanned(), user
                    .getRoleId())
        ) {
            statement.executeUpdate();
            
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                user.setId(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        
        return user;
    }
    
    private User findUser(String sql, Object... values) throws DAOException {
        User user = null;
        
        try (
            PreparedStatement statement = prepareStatement(connection, sql, false,
                                                           values);
            ResultSet resultSet = statement.executeQuery()
        ) {
            if (resultSet.next()) {
                user = map(resultSet);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        
        return user;
    }
    
    private List<User> findUsers(String sql, Object... values) throws DAOException {
        List<User> users = new ArrayList<>();
        
        try (
            PreparedStatement statement = prepareStatement(connection, sql, false,
                                                           values);
            ResultSet resultSet = statement.executeQuery()
        ) {
            while (resultSet.next()) {
                users.add(map(resultSet));
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        
        return users;
    }
    
    private static User map(ResultSet resultset) throws SQLException {
        return new User(
            resultset.getLong("id"),
            resultset.getString("login"),
            resultset.getString("password"),
            resultset.getBoolean("banned"),
            resultset.getLong("role_id")
        );
    }
}
