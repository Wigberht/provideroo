package com.d_cherkashyn.epam.dao.models.subscriber;

import com.d_cherkashyn.epam.ConnectionPool;
import com.d_cherkashyn.epam.dao.DAOException;
import com.d_cherkashyn.epam.dao.models.DAOModel;
import com.d_cherkashyn.epam.model.Account;
import com.d_cherkashyn.epam.model.Subscriber;
import com.d_cherkashyn.epam.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of ServiceDAO for MYSQL RDBMS
 */
public class SubscriberDAOMySQL extends DAOModel implements SubscriberDAO {
    
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
        + "  INNER JOIN user ON subscriber.user_id = user.id ";
    
    private static final String FIND_INTERNETS = "SELECT count(*) as internets from subscriber as sub\n" +
        "    INNER JOIN tariff_subscriber as ts ON sub.id = ts.subscriber_id\n" +
        "    INNER JOIN tariff as t ON ts.tariff_id = t.id\n" +
        "    INNER JOIN service ON t.service_id = service.id\n" +
        "  WHERE sub.id=?\n" +
        "AND service.title LIKE '%intern%'";
    
    private static final String FIND_BY_ID = FIND_ALL + " WHERE id = ?";
    private static final String FIND_BY_USER_ID = FIND_ALL + " WHERE user_id = ?;";
    private static final String FIND_ALL_LIMIT_OFFSET = FIND_ALL + " LIMIT ? OFFSET ?";
    private static final String FIND_SUBSCRIPTION_EXPIRERS = FIND_ALL +
        " WHERE subscriber.id IN (\n" +
        "  SELECT DISTINCT subscriber_id\n" +
        "  FROM tariff_subscriber\n" +
        "  WHERE end <= CURDATE() AND prolong = TRUE\n" +
        ");";
    
    private static final String FIND_NUMBER_OF_SUBSCRIBERS = "SELECT count(*) as " +
        "subscribers from subscriber;";
    
    private static final String CALL_SEARCH_SUBSCRIBER = "{call search_subscriber_utf" +
        "(?,?,?)}";
    
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
    
    private static final String CALCULATE_DEBT = "SELECT SUM(cost) AS debt\n" +
        "FROM tariff\n" +
        "WHERE id IN (\n" +
        "  SELECT tariff_id\n" +
        "  FROM tariff_subscriber\n" +
        "  WHERE subscriber_id = ? AND end <= CURDATE() AND prolong = TRUE\n" +
        ");";
    
    private int limit = 10;
    private int offset = 0;
    
    /**
     * Creates instance of SubscriberDAO
     */
    public SubscriberDAOMySQL() { }
    
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
    
    /**
     * {@inheritDoc}
     */
    @Override
    public List<Subscriber> all() throws DAOException {
        List<Subscriber> subscribers = new ArrayList<>();
        
        Connection connection = ConnectionPool.conn();
        try (Statement statement = connection.createStatement()) {
            statement.executeQuery(FIND_ALL);
            ResultSet resultSet = statement.getResultSet();
            
            while (resultSet.next()) {
                subscribers.add(mapAll(resultSet));
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            ConnectionPool.returnConn(connection);
        }
        
        return subscribers;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public long numberOfSubscribers() throws DAOException {
        long number = 0;
        
        Connection connection = ConnectionPool.conn();
        try (Statement statement = connection.createStatement()) {
            statement.executeQuery(FIND_NUMBER_OF_SUBSCRIBERS);
            
            ResultSet resultSet = statement.getResultSet();
            
            if (resultSet.next()) {
                number = resultSet.getLong("subscribers");
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            ConnectionPool.returnConn(connection);
        }
        
        return number;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public List<Subscriber> all(boolean limited) throws DAOException {
        if (!limited) {
            return all();
        }
        
        List<Subscriber> subscribers = new ArrayList<>();
        
        Connection connection = ConnectionPool.conn();
        try (
            PreparedStatement statement = prepareStatement(connection,
                                                           FIND_ALL_LIMIT_OFFSET, false,
                                                           limit, offset);
            ResultSet resultSet = statement.executeQuery()
        ) {
            while (resultSet.next()) {
                Subscriber subscriber = mapAll(resultSet);
                subscriber.setInternetSubscriptions(findInternets(subscriber.getId()));
                subscribers.add(subscriber);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            ConnectionPool.returnConn(connection);
        }
        
        return subscribers;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public List<Subscriber> all(int limit) throws DAOException {
        this.limit = limit;
        
        return all(true);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public List<Subscriber> all(int limit, int offset) throws DAOException {
        this.limit = limit;
        this.offset = offset;
        
        return all(true);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Subscriber find(Long id) throws DAOException {
        return findSubscriber(FIND_BY_ID, id);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Subscriber findByUserId(Long id) throws DAOException {
        return findSubscriber(FIND_BY_USER_ID, id);
    }
    
    private Subscriber findSubscriber(String sql, Object... objects)
        throws DAOException {
        
        Subscriber subscriber = null;
        
        Connection connection = ConnectionPool.conn();
        try (
            PreparedStatement statement = prepareStatement(
                connection, sql, false, objects);
            ResultSet resultSet = statement.executeQuery()
        ) {
            if (resultSet.next()) {
                subscriber = mapAll(resultSet);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            ConnectionPool.returnConn(connection);
        }
        
        return subscriber;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public List<Subscriber> findSubscriptionExpirers() throws DAOException {
        List<Subscriber> subscribers = new ArrayList<>();
        
        Connection connection = ConnectionPool.conn();
        try (
            PreparedStatement statement = prepareStatement(connection,
                                                           FIND_SUBSCRIPTION_EXPIRERS,
                                                           false);
            ResultSet resultSet = statement.executeQuery()
        ) {
            while (resultSet.next()) {
                subscribers.add(mapAll(resultSet));
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            ConnectionPool.returnConn(connection);
        }
        
        return subscribers;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public double calculateDebt(long id) throws DAOException {
        double debt = 0;
        
        Connection connection = ConnectionPool.conn();
        try (
            PreparedStatement statement = prepareStatement(
                connection, CALCULATE_DEBT, false, id);
            ResultSet resultSet = statement.executeQuery()
        ) {
            if (resultSet.next()) {
                debt = resultSet.getDouble("debt");
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            ConnectionPool.returnConn(connection);
        }
        
        return debt;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Subscriber find(String firstName, String lastName) throws DAOException {
        return null;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean delete(Long id) throws DAOException {
        return false;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean update(Subscriber subscriber) throws DAOException {
        
        Connection connection = ConnectionPool.conn();
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
        } finally {
            ConnectionPool.returnConn(connection);
        }
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Subscriber create(Subscriber subscriber) throws DAOException {
        Connection connection = ConnectionPool.conn();
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
        } finally {
            ConnectionPool.returnConn(connection);
        }
        
        return subscriber;
    }
    
    @Override
    public List<Subscriber> search(String word1, String word2,
                                   String word3) throws DAOException {
        List<Subscriber> subscribers = new ArrayList<>();
        Connection connection = ConnectionPool.conn();
        try (
            CallableStatement cstmt = connection.prepareCall(CALL_SEARCH_SUBSCRIBER)
        ) {
            cstmt.setString("str1", word1);
            cstmt.setString("str2", word2);
            cstmt.setString("str3", word3);
            cstmt.execute();
            
            ResultSet rs = cstmt.getResultSet();
            while (rs.next()) {
                Subscriber subscriber = mapAll(rs);
                subscribers.add(subscriber);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            ConnectionPool.returnConn(connection);
        }
        
        return subscribers;
    }
    
    private int findInternets(long subId) {
        int amount = 0;
        
        Connection connection = ConnectionPool.conn();
        try (
            PreparedStatement statement = prepareStatement(
                connection, FIND_INTERNETS, false, subId);
            ResultSet resultSet = statement.executeQuery()
        ) {
            if (resultSet.next()) {
                amount = resultSet.getInt("internets");
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        
        return amount;
    }
}