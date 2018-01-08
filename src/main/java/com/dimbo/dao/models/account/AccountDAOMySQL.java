package com.dimbo.dao.models.account;

import com.dimbo.dao.DAOException;
import com.dimbo.dao.models.DAOModel;
import com.dimbo.model.Account;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AccountDAOMySQL extends DAOModel implements AccountDAO {

    private static final String FIND_BY_ID = "SELECT * FROM account WHERE id = ?";
    private static final String CREATE_ACCOUNT = "INSERT INTO account(id) VALUES(DEFAULT)";
    private static final String UPDATE_ACCOUNT_BY_ID = "UPDATE account SET balance = ? WHERE id = ?";
    private static final String DELETE_ACCOUNT_BY_ID = "DELETE FROM account WHERE id = ?";


    Connection connection;

    public AccountDAOMySQL(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Account find(Long id) throws DAOException {

        Account account = null;

        try (
            PreparedStatement statement = prepareStatement(connection, FIND_BY_ID, false, id);
            ResultSet resultSet = statement.executeQuery()
        ) {
            if (resultSet.next()) {
                account = map(resultSet);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }

        return account;
    }

    @Override
    public boolean delete(Long id) throws DAOException {
        boolean success;
        try (
            PreparedStatement statement = prepareStatement(
                connection,
                DELETE_ACCOUNT_BY_ID,
                false,
                id
            )
        ) {
            success = (statement.executeUpdate() > 0);

        } catch (SQLException e) {
            throw new DAOException(e);
        }

        return success;
    }

    @Override
    public Account update(Account account) throws DAOException {
        return null;
    }

    @Override
    public Account create(Account account) throws DAOException {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(CREATE_ACCOUNT, Statement.RETURN_GENERATED_KEYS);

            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                account.setId(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }

        return account;
    }

    private static Account map(ResultSet resultset) throws SQLException {
        return new Account(
            resultset.getLong("id"),
            resultset.getDouble("balance"),
            resultset.getString("currency_shortname"),
            resultset.getString("currency_symbol")
        );
    }
}
