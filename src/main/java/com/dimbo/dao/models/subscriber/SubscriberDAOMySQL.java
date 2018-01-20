package com.dimbo.dao.models.subscriber;

import com.dimbo.dao.DAOException;
import com.dimbo.dao.models.DAOModel;
import com.dimbo.dao.models.account.AccountDAOMySQL;
import com.dimbo.dao.models.user.UserDAOMySQL;
import com.dimbo.model.Account;
import com.dimbo.model.Subscriber;
import com.dimbo.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SubscriberDAOMySQL extends DAOModel implements SubscriberDAO {
    
    private static final String FIND_BY_ID =
        "SELECT * " +
            "FROM subscriber " +
            "WHERE id = ?";
    private static final String FIND_BY_USER_ID =
        "SELECT * " +
            "FROM subscriber " +
            "WHERE user_id = ?";
    
    private static final String FIND_ALL = "SELECT\n"
        + "  subscriber.id as subscriber_id,\n"
        + "  subscriber.first_name,\n"
        + "  subscriber.last_name,\n"
        + "  subscriber.birth_date,\n"
        + "  user.id as user_id,\n"
        + "  user.login,\n"
        + "  user.password,\n"
        + "  user.banned,\n"
        + "  user.role_id,\n"
        + "  account.id as account_id,\n"
        + "  account.balance,\n"
        + "  account.currency_shortname\n"
        + "\n"
        + "FROM subscriber\n"
        + "  INNER JOIN account ON subscriber.account_id = account.id\n"
        + "  INNER JOIN user ON subscriber.user_id = user.id";
    private static final String FIND_ALL_LIMIT_OFFSET = "SELECT\n"
        + "  subscriber.id as subscriber_id,\n"
        + "  subscriber.first_name,\n"
        + "  subscriber.last_name,\n"
        + "  subscriber.birth_date,\n"
        + "  user.id as user_id,\n"
        + "  user.login,\n"
        + "  user.password,\n"
        + "  user.banned,\n"
        + "  user.role_id,\n"
        + "  account.id as account_id,\n"
        + "  account.balance,\n"
        + "  account.currency_shortname\n"
        + "\n"
        + "FROM subscriber\n"
        + "  INNER JOIN account ON subscriber.account_id = account.id\n"
        + "  INNER JOIN user ON subscriber.user_id = user.id\n"
        + "LIMIT ? OFFSET ?";
    
    private static final String CREATE_SUBSCRIBER =
        "INSERT INTO subscriber " +
            "VALUES(DEFAULT, ?, ?, ?, ?, ?)";
    private static final String UPDATE_SUBSCRIBER =
        "UPDATE subscriber "
            + "SET first_name = ?, last_name = ?, birth_date = ? "
            + "WHERE id = ?";
    private static final String DELETE_SUBSCRIBER_BY_ID =
        "DELETE " +
            "FROM subscriber " +
            "WHERE id = ?";
    
    
    Connection connection;
    
    private int limit = 15;
    private int offset = 0;
    
    public SubscriberDAOMySQL(Connection connection) {
        this.connection = connection;
    }
    
    @Override
    public List<Subscriber> all() throws DAOException {
        List<Subscriber> subscribers = new ArrayList<>();
        
        try (Statement statement = connection.createStatement()) {
            statement.executeQuery(FIND_ALL);
            
            ResultSet resultSet = statement.getResultSet();
            
            while (resultSet.next()) {
                subscribers.add(mapAll(resultSet));
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        
        return subscribers;
    }
    
    @Override
    public List<Subscriber> all(boolean limited) throws DAOException {
        if (!limited) {
            return all();
        }
        
        List<Subscriber> subscribers = new ArrayList<>();
        
        try (
            PreparedStatement statement = prepareStatement(connection, FIND_ALL_LIMIT_OFFSET, false, limit, offset);
            ResultSet resultSet = statement.executeQuery()
        ) {
            while (resultSet.next()) {
                subscribers.add(mapAll(resultSet));
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        
        return subscribers;
    }
    
    @Override
    public List<Subscriber> all(int limit) throws DAOException {
        this.limit = limit;
        
        return all(true);
    }
    
    @Override
    public List<Subscriber> all(int limit, int offset) throws DAOException {
        this.limit = limit;
        this.offset = offset;
        
        return all(true);
    }
    
    @Override
    public Subscriber find(Long id) throws DAOException {
        return findSubscriber(FIND_BY_ID, id);
    }
    
    @Override
    public Subscriber findByUserId(Long id) throws DAOException {
        return findSubscriber(FIND_BY_USER_ID, id);
    }
    
    private Subscriber findSubscriber(String sql, Object... objects)
        throws DAOException {
        
        Subscriber subscriber = null;
        
        try (
            PreparedStatement statement = prepareStatement(
                connection, sql, false, objects);
            ResultSet resultSet = statement.executeQuery()
        ) {
            if (resultSet.next()) {
                subscriber = map(resultSet);
                subscriber.setUser(findUser(subscriber.getUserId()));
                subscriber.setAccount(findAccount(subscriber.getAccountId()));
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        
        return subscriber;
        
    }
    
    private User findUser(long userId) {
        return new UserDAOMySQL(connection).find(userId);
    }
    
    private Account findAccount(long accountId) {
        return new AccountDAOMySQL(connection).find(accountId);
    }
    
    @Override
    public Subscriber find(String firstName, String lastName) throws DAOException {
        return null;
    }
    
    @Override
    public boolean delete(Long id) throws DAOException {
        return false;
    }
    
    @Override
    public boolean update(Subscriber subscriber) throws DAOException {
        try (
            PreparedStatement statement = prepareStatement(
                connection, UPDATE_SUBSCRIBER, true,
                subscriber.getFirstName(), subscriber.getLastName(),
                subscriber.getBirthDate(), subscriber.getId()
            )
        ) {
            int updatedRows = statement.executeUpdate();
            
            return updatedRows > 0;
            
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }
    
    @Override
    public Subscriber create(Subscriber subscriber) throws DAOException {
        try (
            PreparedStatement statement = prepareStatement(
                connection, CREATE_SUBSCRIBER, true,
                subscriber.getFirstName(),
                subscriber.getLastName(),
                subscriber.getBirthDate(),
                subscriber.getUserId(),
                subscriber.getAccountId()
            )
        ) {
            statement.executeUpdate();
            
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                subscriber.setId(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        
        return subscriber;
    }
    
    private static Subscriber map(ResultSet resultset) throws SQLException {
        return new Subscriber(
            resultset.getLong("id"),
            resultset.getString("first_name"),
            resultset.getString("last_name"),
            resultset.getString("birth_date"),
            resultset.getLong("user_id"),
            resultset.getLong("account_id")
        );
    }
    
    private static Subscriber mapAll(ResultSet resultSet) throws SQLException {
        User user = new User(
            resultSet.getLong("user_id"),
            resultSet.getString("login"),
            resultSet.getString("password"),
            resultSet.getBoolean("banned"),
            resultSet.getLong("role_id")
        );
        
        Account account = new Account(
            resultSet.getLong("account_id"),
            resultSet.getDouble("balance"),
            resultSet.getString("currency_shortname"),
            ""
        );
        
        return new Subscriber(
            resultSet.getLong("subscriber_id"),
            resultSet.getString("first_name"),
            resultSet.getString("last_name"),
            resultSet.getString("birth_date"),
            resultSet.getLong("user_id"),
            resultSet.getLong("account_id"),
            user,
            account
        );
    }
}