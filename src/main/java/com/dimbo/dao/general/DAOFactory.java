package com.dimbo.dao.general;

import com.dimbo.dao.models.message.MessageDAO;
import com.dimbo.dao.models.user.UserDAO;
import com.dimbo.dao.models.user.UserDAOJDBC;
import com.dimbo.managers.DBResourceManager;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public abstract class DAOFactory {
    private static final String URL = "url";
    private static final String DRIVER = "driver";
    private static final String USERNAME = "user";
    private static final String PASSWORD = "password";
    
    public static DAOFactory getInstance(String name) throws DAOConfigurationException {
        if (name == null) {
            throw new DAOConfigurationException("DB name is not set");
        }
        
        String url = DBResourceManager.getInstance().getParameter(URL);
        String user = DBResourceManager.getInstance().getParameter(USERNAME);
        String pass = DBResourceManager.getInstance().getParameter(PASSWORD);
        String driverClassName = DBResourceManager.getInstance().getParameter(DRIVER);
        
        DAOFactory instance;
        if (DBResourceManager.getInstance().parameterExists("driver")) {
            try {
                Class.forName(driverClassName);
            } catch (ClassNotFoundException e) {
                throw new DAOConfigurationException("Driver class '"
                        + driverClassName + "' is missing in classpath", e);
            }
            instance = new DriverManagerDAOFactory(url, user, pass);
        } else {
            DataSource dataSource;
            try {
                dataSource = (DataSource) new InitialContext().lookup(url);
            } catch (NamingException e) {
                throw new DAOConfigurationException(
                        "Datasource '" + url + "' is missing in JNDI.", e);
            }
            if (DBResourceManager.getInstance().parameterExists("user")) {
                instance = new DataSourceWithLoginDAOFactory(dataSource, user, pass);
            } else {
                instance = new DataSourceDAOFactory(dataSource);
            }
            
        }
        
        return instance;
    }
    
    abstract public Connection getConnection() throws SQLException;
    
    public UserDAO getUserDAO() {
        return new UserDAOJDBC(this);
    }

//    public MessageDAO getMessageDAO() {
//        return new MessageDAOJDBC(this);
//    }
}
