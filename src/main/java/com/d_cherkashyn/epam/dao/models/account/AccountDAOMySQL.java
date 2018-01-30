package com.d_cherkashyn.epam.dao.models.account;

import com.d_cherkashyn.epam.ConnectionPool;
import com.d_cherkashyn.epam.dao.DAOException;
import com.d_cherkashyn.epam.dao.models.DAOModel;
import com.d_cherkashyn.epam.model.Account;

import java.sql.*;

/**
 * Implementation of accountDAO for MYSQL RDBMS
 */
public class AccountDAOMySQL extends DAOModel implements AccountDAO {
    
    private static final String FIND_BY_ID = "SELECT * FROM account WHERE id = ?";
    private static final String CREATE_ACCOUNT = "INSERT INTO account(id) VALUES(DEFAULT)";
    private static final String UPDATE_ACCOUNT = "UPDATE account SET balance = ? WHERE id = ?";
    private static final String DELETE_ACCOUNT = "DELETE FROM account WHERE id = ?";
    
    /**
     * Creates instance of AccountDAO
     */
    public AccountDAOMySQL() { }
    
    private static Account map(ResultSet resultset) throws SQLException {
        return new Account(
            resultset.getLong("id"),
            resultset.getDouble("balance"),
            resultset.getString("currency_shortname"),
            resultset.getString("currency_symbol")
        );
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Account find(Long id) throws DAOException {
        
        Account account = null;
        Connection connection = ConnectionPool.conn();
        try (
            PreparedStatement statement = prepareStatement(connection, FIND_BY_ID, false,
                                                           id);
            ResultSet resultSet = statement.executeQuery()
        ) {
            if (resultSet.next()) {
                account = map(resultSet);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            ConnectionPool.returnConn(connection);
        }
        
        return account;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean delete(Long id) throws DAOException {
        boolean success;
        Connection connection = ConnectionPool.conn();
        try (
            PreparedStatement statement = prepareStatement(
                connection, DELETE_ACCOUNT, false,
                id
            )
        ) {
            success = (statement.executeUpdate() > 0);
            
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            ConnectionPool.returnConn(connection);
        }
        
        return success;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean update(Account account) throws DAOException {
        
        Connection connection = ConnectionPool.conn();
        try (
            PreparedStatement statement = prepareStatement(
                connection, UPDATE_ACCOUNT, true,
                account.getBalance(), account.getId()
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
    public Account create(Account account) throws DAOException {
        Connection connection = ConnectionPool.conn();
        try (Statement statement = connection.createStatement()) {
            statement
                .executeUpdate(CREATE_ACCOUNT, Statement.RETURN_GENERATED_KEYS);
            
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                account.setId(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            ConnectionPool.returnConn(connection);
        }
        
        return account;
    }
}
