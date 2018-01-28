package com.d_cherkashyn.epam.helper.service;

import com.d_cherkashyn.epam.ConnectionPool;

import java.sql.Connection;

/**
 * Helper class to help in managing connection for service
 */
public class ServiceHelper {
    protected Connection connection;
    
    /**
     * Creates instance of service and creates connection for it
     */
    ServiceHelper() {
        connection = ConnectionPool.conn();
    }
    
    /**
     * Creates instance of service and uses provided connection
     */
    ServiceHelper(Connection connection) {
        this.connection = connection;
    }
    
    /**
     * Returns connection to connection pool
     */
    public void returnConnection() {
        ConnectionPool.returnConn(connection);
    }
}
