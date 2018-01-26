package com.d_cherkashyn.epam.dao.models.role;

import com.d_cherkashyn.epam.dao.DAOException;
import com.d_cherkashyn.epam.dao.models.DAOModel;
import com.d_cherkashyn.epam.model.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RoleDAOMySQL extends DAOModel implements RoleDAO {
    
    private static final String FIND_BY_NAME =
        "SELECT * FROM role WHERE name = ?";
    
    private Connection connection;
    
    public RoleDAOMySQL(Connection connection) {
        this.connection = connection;
    }
    
    @Override
    public Role find(String name) throws DAOException {
        Role role = null;
        
        try (
            PreparedStatement statement = prepareStatement(connection,
                FIND_BY_NAME, false, name);
            ResultSet resultSet = statement.executeQuery()
        ) {
            if (resultSet.next()) {
                role = map(resultSet);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        
        return role;
    }
    
    private static Role map(ResultSet resultset) throws SQLException {
        return new Role(
            resultset.getLong("id"),
            resultset.getString("name")
        );
    }
}
