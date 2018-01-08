package com.dimbo.dao.models.subscriber;

import com.dimbo.dao.DAOException;
import com.dimbo.dao.models.DAOModel;
import com.dimbo.model.Account;
import com.dimbo.model.Subscriber;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SubscriberDAOMySQL extends DAOModel implements SubscriberDAO {

    private static final String FIND_BY_ID = "SELECT * FROM subscriber WHERE id = ?";
    private static final String CREATE_SUBSCRIBER = "INSERT INTO subscriber VALUES(DEFAULT, ?, ?, ?, ?, ?)";
    private static final String UPDATE_SUBSCRIBER_BY_ID = "UPDATE subscriber "
        + "SET first_name = ?, last_name = ?, birth_date = ? "
        + "WHERE id = ?";
    private static final String DELETE_SUBSCRIBER_BY_ID = "DELETE FROM subscriber WHERE id = ?";


    Connection connection;

    public SubscriberDAOMySQL(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Subscriber find(Long id) throws DAOException {
        Subscriber subscriber = null;

        try (
            PreparedStatement statement = prepareStatement(connection, FIND_BY_ID, false, id);
            ResultSet resultSet = statement.executeQuery()
        ) {
            if (resultSet.next()) {
                subscriber = map(resultSet);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }

        return subscriber;
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
    public Subscriber update(Subscriber subscriber) throws DAOException {
        return null;
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
}
