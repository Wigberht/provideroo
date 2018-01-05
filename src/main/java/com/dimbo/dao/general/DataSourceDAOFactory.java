package com.dimbo.dao.general;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DataSourceDAOFactory extends DAOFactory {
    private DataSource dataSource;
    
    public DataSourceDAOFactory(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    
    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
