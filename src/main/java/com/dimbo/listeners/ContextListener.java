package com.dimbo.listeners;

import com.dimbo.ConnectionPool;
import com.dimbo.dao.factory.FactoryGenerator;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

        // init database type
        String dbType = servletContextEvent.getServletContext()
                                           .getInitParameter("db_type");
        FactoryGenerator.setDbType(dbType);
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

