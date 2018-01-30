package com.d_cherkashyn.epam.dao.models;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

public class DAOModel {
    
    public static final Logger LOGGER = LoggerFactory.getLogger(DAOModel.class);
    
    /**
     * Prepares statement to be executed.
     * Fills it with passed values
     *
     * @param connection
     * @param sql
     * @param returnGeneratedKeys
     * @param values
     * @return
     * @throws SQLException
     */
    public static PreparedStatement prepareStatement(Connection connection,
                                                     String sql,
                                                     boolean returnGeneratedKeys,
                                                     Object... values) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(sql,
                                                                  returnGeneratedKeys ? Statement.RETURN_GENERATED_KEYS : Statement.NO_GENERATED_KEYS);
        setValues(statement, values);
        
        return statement;
    }
    
    public static void setValues(PreparedStatement statement, Object... values)
        throws SQLException {
        for (int i = 0; i < values.length; i++) {
            statement.setObject(i + 1, values[i]);
        }
    }
    
    public static Date toSqlDate(java.util.Date date) {
        return (date != null) ? new Date(date.getTime()) : null;
    }
    
    public void closeStatement(Statement statement) {
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            LOGGER.error("Statement was unable to close", e);
        }
    }
}
