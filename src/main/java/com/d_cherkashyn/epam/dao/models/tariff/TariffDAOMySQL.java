package com.d_cherkashyn.epam.dao.models.tariff;

import com.d_cherkashyn.epam.ConnectionPool;
import com.d_cherkashyn.epam.dao.DAOException;
import com.d_cherkashyn.epam.dao.models.DAOModel;
import com.d_cherkashyn.epam.model.Tariff;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TariffDAOMySQL extends DAOModel implements TariffDAO {
    
    private static final String FIND_BY_ID =
        "SELECT *\n" +
            "FROM tariff, (\n" +
            "               SELECT COUNT(id) AS subscribers\n" +
            "               FROM tariff_subscriber\n" +
            "               WHERE tariff_id = ? AND prolong = TRUE\n" +
            "             ) AS counters\n" +
            "WHERE id = ?;";
    
    private static final String CALL_SEARCH_TARIFF = "{call search_tariff_utf(?,?,?)}";
    
    private static final String COUNT_SUBSCRIBERS = "SELECT COUNT(DISTINCT subscriber_id) AS subscribers\n" +
        "FROM tariff_subscriber\n" +
        "WHERE tariff_id = ?";
    
    private static final String FIND_BY_SERVICE_ID = "SELECT * FROM tariff WHERE service_id = ?";
    private static final String FIND_BY_SERVICE_ID_SORTED = "SELECT *\n" +
        "FROM tariff\n" +
        "WHERE service_id = ?\n" +
        "ORDER BY ? ?;";
    
    private static final String CREATE_TARIFF = "INSERT INTO tariff VALUES(DEFAULT, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_TARIFF = "UPDATE tariff "
        + "SET title = ?, description = ?, number_of_days = ?, cost = ?, currency_shortname = ? "
        + "WHERE id = ?";
    private static final String DELETE_TARIFF_BY_ID = "DELETE FROM tariff WHERE id = ?";
    
    
    public TariffDAOMySQL() {}
    
    private static Tariff map(ResultSet resultset) throws SQLException {
        return new Tariff(
            resultset.getLong("id"),
            resultset.getString("title"),
            resultset.getString("description"),
            resultset.getInt("number_of_days"),
            resultset.getDouble("cost"),
            resultset.getString("currency_shortname"),
            resultset.getLong("service_id")
        );
    }
    
    @Override
    public List<Tariff> findByService(long serviceId) throws DAOException {
        List<Tariff> tariffs = new ArrayList<>();
        
        Connection connection = ConnectionPool.conn();
        try (
            PreparedStatement statement = prepareStatement(connection, FIND_BY_SERVICE_ID,
                                                           false,
                                                           serviceId);
            ResultSet resultSet = statement.executeQuery()
        ) {
            while (resultSet.next()) {
                Tariff tariff = map(resultSet);
                tariff.setSubscriberAmount(countSubscribers(tariff.getId()));
                tariffs.add(tariff);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            ConnectionPool.returnConn(connection);
        }
        
        return tariffs;
    }
    
    @Override
    public List<Tariff> findByServiceSorted(long serviceId,
                                            String field,
                                            String order) throws DAOException {
        List<Tariff> tariffs = new ArrayList<>();
        
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM tariff WHERE service_id =")
          .append(serviceId)
          .append(" ORDER BY ")
          .append(field)
          .append(" ")
          .append(order);
        
        Connection connection = ConnectionPool.conn();
        try (
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sb.toString())
        ) {
            while (resultSet.next()) {
                Tariff tariff = map(resultSet);
                tariff.setSubscriberAmount(countSubscribers(tariff.getId()));
                tariffs.add(tariff);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            ConnectionPool.returnConn(connection);
        }
        
        return tariffs;
    }
    
    private int countSubscribers(long tariffId) throws DAOException {
        int subscribers = 0;
        
        Connection connection = ConnectionPool.conn();
        try (
            PreparedStatement statement = prepareStatement(connection, COUNT_SUBSCRIBERS,
                                                           false,
                                                           tariffId);
            ResultSet resultSet = statement.executeQuery()
        ) {
            if (resultSet.next()) {
                subscribers = resultSet.getInt("subscribers");
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            ConnectionPool.returnConn(connection);
        }
        
        return subscribers;
    }
    
    @Override
    public Tariff find(Long id) throws DAOException {
        Tariff tariff = null;
        
        Connection connection = ConnectionPool.conn();
        try (
            PreparedStatement pstmt = prepareStatement(connection, FIND_BY_ID, false,
                                                       id, id);
            ResultSet resultSet = pstmt.executeQuery()
        ) {
            if (resultSet.next()) {
                tariff = map(resultSet);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            ConnectionPool.returnConn(connection);
        }
        
        return tariff;
    }
    
    @Override
    public List<Tariff> search(String word1, String word2,
                               String word3) throws DAOException {
        List<Tariff> tariffs = new ArrayList<>();
        
        Connection connection = ConnectionPool.conn();
        try (
            CallableStatement cstmt = connection.prepareCall(CALL_SEARCH_TARIFF)
        ) {
            cstmt.setString("str1", word1);
            cstmt.setString("str2", word2);
            cstmt.setString("str3", word3);
            cstmt.execute();
            
            ResultSet rs = cstmt.getResultSet();
            while (rs.next()) {
                Tariff tariff = map(rs);
                tariff.setServiceTitle(rs.getString("service_title"));
                tariff.setSubscriberAmount(countSubscribers(tariff.getId()));
                tariffs.add(tariff);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            ConnectionPool.returnConn(connection);
        }
        
        return tariffs;
    }
    
    @Override
    public boolean delete(Long id) throws DAOException {
        
        boolean success;
        Connection connection = ConnectionPool.conn();
        try (
            PreparedStatement statement = prepareStatement(
                connection, DELETE_TARIFF_BY_ID, true,
                id
            )
        ) {
            int updatedRows = statement.executeUpdate();
            
            success = updatedRows > 0;
            
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            ConnectionPool.returnConn(connection);
        }
        
        return success;
    }
    
    @Override
    public boolean update(Tariff tariff) throws DAOException {
        
        boolean success;
        Connection connection = ConnectionPool.conn();
        try (
            PreparedStatement statement = prepareStatement(
                connection, UPDATE_TARIFF, true,
                tariff.getTitle(), tariff.getDescription(),
                tariff.getNumberOfDays(), tariff.getCost(),
                tariff.getCurrencyShortname(), tariff.getId()
            )
        ) {
            int updatedRows = statement.executeUpdate();
            
            success = updatedRows > 0;
            
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            ConnectionPool.returnConn(connection);
        }
        
        return success;
    }
    
    @Override
    public Tariff create(Tariff tariff) throws DAOException {
        
        Connection connection = ConnectionPool.conn();
        try (
            PreparedStatement statement = prepareStatement(
                connection, CREATE_TARIFF, true,
                tariff.getTitle(),
                tariff.getDescription(),
                tariff.getNumberOfDays(),
                tariff.getCost(),
                tariff.getCurrencyShortname(),
                tariff.getServiceId()
            )
        ) {
            statement.executeUpdate();
            
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                tariff.setId(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            ConnectionPool.returnConn(connection);
        }
        
        return tariff;
    }
}
