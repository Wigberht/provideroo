package com.dimbo.dao.general;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DataSourceWithLoginDAOFactory extends DAOFactory {
    
    private DataSource dataSource;
    private String username;
    private String password;
    
    public DataSourceWithLoginDAOFactory(DataSource dataSource, String username, String password) {
        this.dataSource = dataSource;
        this.username = username;
        this.password = password;
    }
    
    public Connection getConnection() throws SQLException {
        return dataSource.getConnection(username, password);
    }
}
