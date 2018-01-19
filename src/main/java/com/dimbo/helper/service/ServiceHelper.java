package com.dimbo.helper.service;

import com.dimbo.ConnectionPool;

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
