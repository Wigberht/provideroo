package com.dimbo.helper.service;

import com.dimbo.ConnectionPool;

import java.sql.Connection;

public class ServiceHelper {
    protected Connection connection;
    
    ServiceHelper() {
        connection = ConnectionPool.conn();
    }
    
    public void returnConnection() {
        ConnectionPool.returnConn(connection);
    }
}
