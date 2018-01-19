package com.dimbo.listeners;

import com.dimbo.ConnectionPool;
import com.dimbo.dao.factory.FactoryGenerator;
import com.dimbo.dao.models.role.RoleDAO;
import com.dimbo.dao.models.service.ServiceDAO;
import com.dimbo.model.Role;

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
        
        /* fetch role ids from DB */
        RoleDAO roleDAO = FactoryGenerator.getFactory().makeRoleDAO(connection);
        Role admin = roleDAO.find("admin");
        Role subscriber = roleDAO.find("subscriber");
        
        servletContextEvent.getServletContext()
                           .setAttribute("admin_role_id", admin.getId());
        servletContextEvent.getServletContext()
                           .setAttribute("subscriber_role_id",
                               subscriber.getId());
        
        ConnectionPool.returnConn(connection);
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

