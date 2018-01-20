package com.dimbo.command.admin;

import com.dimbo.ConnectionPool;
import com.dimbo.command.Command;
import com.dimbo.dao.factory.FactoryGenerator;
import com.dimbo.dao.models.service.ServiceDAO;
import com.dimbo.helper.service.ServiceService;
import com.dimbo.helper.service.SubscriberService;
import com.dimbo.helper.service.SubscriptionService;
import com.dimbo.managers.PagesResourceManager;
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
        
        HttpSession s = request.getSession();
        
        if (request.getParameter("sort") != null) {
            s.setAttribute("sort", request.getParameter("sort"));
        }
        
        if (s.getAttribute("sort") != null) {
            request.setAttribute("services",
                serviceService.getAllServices(
                    s.getAttribute("sort").toString(), true));
        } else {
            request.setAttribute("services", serviceService.getAllServices());
        }
    }
    
    private void fillSubscriptions(HttpServletRequest request, Connection connection) {
        SubscriberService subscriberService = new SubscriberService(connection);
        SubscriptionService subscriptionService = new SubscriptionService(connection);
        JSONService jsonService = new JSONService();
        
        Object userObj = request.getSession().getAttribute("user");
        if (userObj != null && !((User) userObj).isAdmin()) {
            User user = (User) userObj;
            
            Subscriber subscriber = subscriberService
                .findSubscriberByUserId(user.getId());
            
            List<Subscription> subscriptions = subscriptionService
                .getSubscriptions(subscriber.getId());
            
            String jsonSubscriptions = jsonService.toJSON(subscriptions);
            
            request.setAttribute("subscriptions", jsonSubscriptions);
        }
    }
    
}