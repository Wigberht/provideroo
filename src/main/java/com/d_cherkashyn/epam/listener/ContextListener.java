package com.d_cherkashyn.epam.listener;

import com.d_cherkashyn.epam.ConnectionPool;
import com.d_cherkashyn.epam.dao.factory.FactoryGenerator;
import com.d_cherkashyn.epam.dao.models.role.RoleDAO;
import com.d_cherkashyn.epam.helper.SessionNumberHolder;
import com.d_cherkashyn.epam.helper.schedule.UpdateSubscriptionsJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Context listener. Watches over context events
 */
public class ContextListener implements ServletContextListener {
    Logger LOGGER = LoggerFactory.getLogger(ContextListener.class);
    
    private ScheduledExecutorService scheduler;
    
    /**
     * {@inheritDoc}
     */
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
        scheduler = Executors.newScheduledThreadPool(4);
        
        LocalDateTime startOfDay = LocalDate.now().plusDays(1).atStartOfDay();
        long initialDelayInMinutes = LocalDateTime.now()
                                                  .until(startOfDay, ChronoUnit.MINUTES);
        initialDelayInMinutes = 5;
        
        long continuousDelayInMinutes = TimeUnit.DAYS.toMinutes(1);
        continuousDelayInMinutes = 5;
        
        
        scheduler.scheduleAtFixedRate(new UpdateSubscriptionsJob(),
                                      initialDelayInMinutes,
                                      continuousDelayInMinutes,
                                      TimeUnit.MINUTES);
        LOGGER.info("SCHEDULER officially started! will run next time in {} minutes " +
                        "with delays of {} minutes",
                    initialDelayInMinutes, continuousDelayInMinutes);
        
        SessionNumberHolder.getInstance().setSessions(0);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void contextDestroyed(ServletContextEvent arg0) {
        ConnectionPool.getInstance()
                      .returnAllConnections();
        
        scheduler.shutdownNow();
        LOGGER.info("SCHEDULER have been shut down");
    }
}

