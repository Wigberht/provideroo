package com.dimbo.command.admin;

import com.dimbo.ConnectionPool;
import com.dimbo.command.Command;
import com.dimbo.helper.service.ServiceService;
import com.dimbo.helper.service.SubscriberService;
import com.dimbo.helper.service.SubscriptionService;
import com.dimbo.manager.PagesResourceManager;
import com.dimbo.model.*;

import java.sql.Connection;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.dimbo.rest.JSONService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServiceListAdminCommand implements Command {
    
    Logger LOGGER = LoggerFactory.getLogger(ServiceListAdminCommand.class);
    
    @Override
    public String execute(HttpServletRequest request) {
        
        
        Connection connection = ConnectionPool.conn();
        ServiceService serviceService = new ServiceService(connection);
        List<Service> services;
        
        HttpSession s = request.getSession();
        Object sortParam = request.getParameter("sort");
        LOGGER.info("filling services");
        if (sortParam != null) {
            s.setAttribute("sort", sortParam);
        }
        
        sortParam = s.getAttribute("sort");
        
        if (sortParam != null) {
            String sort = (String) sortParam;
            services = serviceService.getAllServices(sort, true);
        } else {
            services = serviceService.getAllServices();
        }
        
        ConnectionPool.returnConn(connection);
        
        request.setAttribute("services", services);
        
        return PagesResourceManager.getPage("service_list_jsp");
    }
}