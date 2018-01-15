package com.dimbo.listeners;

import com.dimbo.ConnectionPool;
import com.dimbo.dao.factory.FactoryGenerator;
import com.dimbo.dao.models.service.ServiceDAO;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.sql.Connection;

public class ContextListener implements ServletContextListener {
    
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        
        // init database type
        String dbType = servletContextEvent.getServletContext()
                                           .getInitParameter("db_type");
        FactoryGenerator.setDbType(dbType);
        
        // fill service list
        Connection connection = ConnectionPool.conn();
        ServiceDAO serviceDAO = FactoryGenerator.getFactory()
                                                .makeServiceDAO(connection);
        servletContextEvent.getServletContext()
                           .setAttribute("services", serviceDAO.all());
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void contextDestroyed(ServletContextEvent arg0) {
        ConnectionPool.getInstance()
                      .returnAllConnections();
    }
}

