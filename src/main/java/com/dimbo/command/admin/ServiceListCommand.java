package com.dimbo.command.admin;

import com.dimbo.ConnectionPool;
import com.dimbo.command.Command;
import com.dimbo.dao.factory.FactoryGenerator;
import com.dimbo.dao.models.service.ServiceDAO;
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

public class ServiceListCommand implements Command {
    
    Logger LOGGER = LoggerFactory.getLogger(ServiceListCommand.class);
    
    @Override
    public String execute(HttpServletRequest request) {
        Connection connection = ConnectionPool.conn();
        
        fillServices(request, connection);
        fillSubscriptions(request, connection);
        
        ConnectionPool.returnConn(connection);
        
        return PagesResourceManager.getPage("service_list_jsp");
    }
    
    private void fillServices(HttpServletRequest request, Connection connection) {
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
        
        request.setAttribute("services", services);
    }
    
    private void fillSubscriptions(HttpServletRequest request, Connection connection) {
        SubscriberService subscriberService = new SubscriberService(connection);
        SubscriptionService subscriptionService = new SubscriptionService(connection);
        JSONService jsonService = new JSONService();
        Subscriber subscriber;
        List<Subscription> subscriptions;
        
        Object userObj = request.getSession().getAttribute("user");
        if (userObj != null && !((User) userObj).isAdmin()) {
            User user = (User) userObj;
            
            subscriber = subscriberService.findSubscriberByUserId(user.getId());
            subscriptions = subscriptionService.getSubscriptions(subscriber.getId());
            
            String jsonSubscriptions = jsonService.toJSON(subscriptions);
            
            request.setAttribute("subscriptions", jsonSubscriptions);
        }
    }
    
}