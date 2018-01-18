package com.dimbo.dao.models.subscription;

import com.dimbo.dao.DAOException;
import com.dimbo.dao.models.DAOModel;
import com.dimbo.model.Subscription;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class SubscriptionDAOMySQL extends DAOModel implements SubscriptionDAO {
    
    private static final String FIND_BY_ID =
        "SELECT * " +
            "FROM tariff_subscriber " +
            "WHERE id = ?";
    private static final String FIND_BY_SUBSCRIBER_ID =
        "SELECT * " +
            "FROM tariff_subscriber " +
            "WHERE subscriber_id = ?";
    
    private static final String FIND_BY_TARIFF_AND_SUBSCRIBER =
        "SELECT * " +
            "FROM tariff_subscriber " +
            "WHERE tariff_id = ? AND subscriber_id = ?";
    
    
    private static final String CREATE_SUBSCRIPTION =
        "INSERT INTO tariff_subscriber " +
            "VALUES(DEFAULT, ?, ?, ?, ?, ?)";
    private static final String UPDATE_SUBSCRIPTION =
        "UPDATE tariff_subscriber "
            + "SET start = ?, end = ?, prolong = ? "
            + "WHERE id = ?";
    
    private static final String UPDATE_SUBSCRIPTION_PROLONG =
        "UPDATE tariff_subscriber "
            + "SET start = ?, end = ?, prolong = ? "
            + "WHERE id = ?";
    
    private static final String DELETE_SUBSCRIPTION =
        "DELETE " +
            "FROM tariff_subscriber " +
            "WHERE id = ?";
    
    
    Connection connection;
    
    public SubscriptionDAOMySQL(Connection connection) {
        this.connection = connection;
    }
    
    @Override
    public Subscription find(long id) throws DAOException {
        return findSubscription(FIND_BY_ID, id);
    }
    
    
    @Override
    public Subscription find(long tariffId, long subscriberId) throws DAOException {
        return findSubscription(FIND_BY_TARIFF_AND_SUBSCRIBER, tariffId, subscriberId);
    }
    
    private Subscription findSubscription(String sql, Object... values)
        throws DAOException {
        
        Subscription subscription = null;
        
        try (
            PreparedStatement statement = prepareStatement(
                connection, sql, false, values);
            ResultSet resultSet = statement.executeQuery()
        ) {
            if (resultSet.next()) {
                subscription = map(resultSet);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        
        return subscription;
        
    }
    
    @Override
    public List<Subscription> findBySubscriber(long id) throws DAOException {
        List<Subscription> subscriptions = new ArrayList<>();
        
        try (
            PreparedStatement statement = prepareStatement(
                connection, FIND_BY_SUBSCRIBER_ID, false, id);
            ResultSet resultSet = statement.executeQuery()
        ) {
            
            while (resultSet.next()) {
                subscriptions.add(map(resultSet));
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        
        return subscriptions;
    }
    
    @Override
    public boolean delete(Long id) throws DAOException {
        return false;
    }
    
    @Override
    public boolean update(Subscription subscription) throws DAOException {
        try (
            PreparedStatement statement = prepareStatement(
                connection, UPDATE_SUBSCRIPTION, true,
                subscription.getStart(), subscription.getEnd(),
                subscription.isProlong(), subscription.getId()
            )
        ) {
            int updatedRows = statement.executeUpdate();
            
            return updatedRows > 0;
            
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }
    
    @Override
    public Subscription create(Subscription subscription) throws DAOException {
        try (
            PreparedStatement statement = prepareStatement(
                connection, CREATE_SUBSCRIPTION, true,
                subscription.getStart(),
                subscription.getEnd(),
                subscription.isProlong(),
                subscription.getTariffId(),
                subscription.getSubscriberId()
            )
        ) {
            statement.executeUpdate();
            
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                subscription.setId(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            LOGGER.error("failed to create subscription");
            throw new DAOException(e);
        }
        
        return subscription;
    }
    
    private static Subscription map(ResultSet resultset) throws SQLException {
        return new Subscription(
            resultset.getLong("id"),
            resultset.getString("start"),
            resultset.getString("end"),
            resultset.getBoolean("prolong"),
            resultset.getLong("tariff_id"),
            resultset.getLong("subscriber_id")
        );
    }
}