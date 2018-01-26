package com.d_cherkashyn.epam.helper.service;

import com.d_cherkashyn.epam.ConnectionPool;

import java.sql.Connection;

public class ServiceHelper {
    protected Connection connection;
    
    ServiceHelper() {
        connection = ConnectionPool.conn();
    }
    
    ServiceHelper(Connection connection) {
        this.connection = connection;
    }
    
    public void returnConnection() {
        ConnectionPool.returnConn(connection);
    }
}
