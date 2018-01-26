package com.d_cherkashyn.epam.listener;

import com.d_cherkashyn.epam.ConnectionPool;
import com.d_cherkashyn.epam.dao.factory.FactoryGenerator;
import com.d_cherkashyn.epam.dao.models.role.RoleDAO;
import com.d_cherkashyn.epam.helper.SessionNumberHolder;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.sql.Connection;
import java.util.concurrent.ScheduledExecutorService;

public class ContextListener implements ServletContextListener {
    
    private ScheduledExecutorService scheduler;
    
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        
        // init database type
        String dbType = servletContextEvent.getServletContext()
                                           .getInitParameter("db_type");
        FactoryGenerator.setDbType(dbType);
        
        /* fetch role ids from DB */
        Connection connection = ConnectionPool.conn();
        RoleDAO roleDAO = FactoryGenerator.getFactory().makeRoleDAO(connection);
        long adminId = roleDAO.find("admin").getId();
        long subscriberId = roleDAO.find("subscriber").getId();
        
        servletContextEvent.getServletContext()
                           .setAttribute("admin_role_id", adminId);
        servletContextEvent.getServletContext()
                           .setAttribute("subscriber_role_id", subscriberId);
        
        ConnectionPool.returnConn(connection);
        
        /* start scheduled tasks */
//        scheduler = Executors.newSingleThreadScheduledExecutor();
//        scheduler.scheduleAtFixedRate(
//            new UpdateSubscriptionsJob(), 0, 2, TimeUnit.SECONDS);
        
        SessionNumberHolder.getInstance().setSessions(0);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void contextDestroyed(ServletContextEvent arg0) {
        ConnectionPool.getInstance()
                      .returnAllConnections();

//        scheduler.shutdownNow();
    }
}

