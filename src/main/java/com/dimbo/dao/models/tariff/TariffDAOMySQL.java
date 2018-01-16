package com.dimbo.dao.models.tariff;

import com.dimbo.dao.DAOException;
import com.dimbo.dao.models.DAOModel;
import com.dimbo.model.Subscriber;
import com.dimbo.model.Tariff;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TariffDAOMySQL extends DAOModel implements TariffDAO {
    
    private static final String FIND_BY_ID = "SELECT * FROM tariff WHERE id = ?";
    private static final String FIND_BY_SERVICE_ID = "SELECT * FROM tariff WHERE service_id = ?";
    private static final String CREATE_TARIFF = "INSERT INTO tariff VALUES(DEFAULT, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_TARIFF_BY_ID = "UPDATE tariff "
        + "SET title = ?, description = ?, number_of_days = ?, cost = ?, currency_shortname = ? "
        + "WHERE id = ?";
    private static final String DELETE_TARIFF_BY_ID = "DELETE FROM tariff WHERE id = ?";


    Connection connection;

    public TariffDAOMySQL(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Tariff> findByService(Long serviceId) throws DAOException {
        List<Tariff> tariffs = new ArrayList<>();

        try (
            PreparedStatement statement = prepareStatement(connection, FIND_BY_SERVICE_ID, false,
                serviceId);
            ResultSet resultSet = statement.executeQuery()
        ) {
            while (resultSet.next()) {
                tariffs.add(map(resultSet));
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }

        return tariffs;
    }

    @Override
    public Tariff find(Long id) throws DAOException {
        Tariff tariff = null;

        try (
            PreparedStatement statement = prepareStatement(connection, FIND_BY_ID, false, id);
            ResultSet resultSet = statement.executeQuery()
        ) {
            if (resultSet.next()) {
                tariff = map(resultSet);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }

        return tariff;
    }

    @Override
    public boolean delete(Long id) throws DAOException {
        return false;
    }

    @Override
    public Tariff update(Tariff tariff) throws DAOException {
        return null;
    }

    @Override
    public Tariff create(Tariff tariff) throws DAOException {
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
        }

        return tariff;
    }

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
}
