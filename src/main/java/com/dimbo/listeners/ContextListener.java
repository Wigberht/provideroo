package com.dimbo.listeners;

import com.dimbo.ConnectionPool;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        // did not decide yet what to do on server start
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

