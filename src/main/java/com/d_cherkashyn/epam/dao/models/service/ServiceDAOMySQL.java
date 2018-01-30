package com.d_cherkashyn.epam.dao.models.service;

import com.d_cherkashyn.epam.ConnectionPool;
import com.d_cherkashyn.epam.dao.DAOException;
import com.d_cherkashyn.epam.dao.factory.FactoryGenerator;
import com.d_cherkashyn.epam.dao.models.DAOModel;
import com.d_cherkashyn.epam.model.Service;
import com.d_cherkashyn.epam.model.Tariff;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of ServiceDAO for MYSQL RDBMS
 */
public class ServiceDAOMySQL extends DAOModel implements ServiceDAO {
    
    private static final String FIND_ALL = "SELECT * FROM service";
    private static final String FIND_BY_ID = "SELECT * FROM service WHERE id = ?";
    private static final String CREATE_SERVICE = "INSERT INTO service VALUES(DEFAULT, ?)";
    private static final String UPDATE_SERVICE_BY_ID = "UPDATE service "
        + "SET title = ? "
        + "WHERE id = ?";
    private static final String DELETE_SERVICE_BY_ID = "DELETE FROM service WHERE id = ?";
    
    /**
     * Creates instance of ServiceDAO
     */
    public ServiceDAOMySQL() {}
    
    /**
     * {@inheritDoc}
     */
    @Override
    public List<Service> all() throws DAOException {
        return findAllServices(FIND_ALL);
    }
    
    private List<Service> findAllServices(String sql) {
        List<Service> services = new ArrayList<>();
        
        Connection connection = ConnectionPool.conn();
        try (Statement statement = connection.createStatement()) {
            statement.executeQuery(sql);
            
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()) {
                services.add(map(resultSet));
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            ConnectionPool.returnConn(connection);
        }
        
        return services;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Service find(Long id) throws DAOException {
        Service service = null;
        Connection connection = ConnectionPool.conn();
        try (
            PreparedStatement statement = prepareStatement(connection, FIND_BY_ID, false,
                                                           id);
            ResultSet resultSet = statement.executeQuery()
        ) {
            if (resultSet.next()) {
                service = map(resultSet);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            ConnectionPool.returnConn(connection);
        }
        
        return service;
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
    public Service update(Service service) throws DAOException {
        return null;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Service create(Service service) throws DAOException {
        Connection connection = ConnectionPool.conn();
        try (
            PreparedStatement statement = prepareStatement(
                connection, CREATE_SERVICE, true,
                service.getTitle()
            )
        ) {
            statement.executeUpdate();
            
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                service.setId(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            ConnectionPool.returnConn(connection);
        }
        
        return service;
    }
    
    private static Service map(ResultSet resultset) throws SQLException {
        return new Service(
            resultset.getLong("id"),
            resultset.getString("title")
        );
    }
}
