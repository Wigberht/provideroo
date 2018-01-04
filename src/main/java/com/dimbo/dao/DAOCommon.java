package com.dimbo.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.sql.Statement;

public class DAOCommon {

    public static final Logger LOGGER = LoggerFactory.getLogger(DAOCommon.class);

    /**
     * Closes statement.
     *
     * @param statement the statement
     */
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
