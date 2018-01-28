package com.d_cherkashyn.epam.command.subscriber;

import com.d_cherkashyn.epam.ConnectionPool;
import com.d_cherkashyn.epam.command.Command;
import com.d_cherkashyn.epam.helper.service.ServiceService;
import com.d_cherkashyn.epam.helper.service.SubscriberService;
import com.d_cherkashyn.epam.helper.service.SubscriptionService;
import com.d_cherkashyn.epam.manager.PagesResourceManager;
import com.d_cherkashyn.epam.model.Service;
import com.d_cherkashyn.epam.model.Subscriber;
import com.d_cherkashyn.epam.model.Subscription;
import com.d_cherkashyn.epam.model.User;
import com.d_cherkashyn.epam.rest.JSONService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.util.Collections;
import java.util.List;

/**
 * Command that fetches all required data used to show on a page with list of tariffs
 */
public class ServiceListSubscriberCommand implements Command {
    
    Logger LOGGER = LoggerFactory.getLogger(ServiceListSubscriberCommand.class);
    
    @Override
    public String execute(HttpServletRequest request) {
        Connection connection = ConnectionPool.conn();
        
        fillServices(request, connection);
        fillSubscriptions(request, connection);
        
        ConnectionPool.returnConn(connection);
        
        return PagesResourceManager.getPage("service_list_subscriber_jsp");
    }
    
    private void fillServices(HttpServletRequest request, Connection connection) {
        ServiceService serviceService = new ServiceService();
        List<Service> services;
    
        Object sortField = request.getParameter("sortField");
        Object sortOrder = request.getParameter("sortOrder");
    
        if (sortOrder == null || sortField == null) {
            services = serviceService.getAllServices();
        } else {
            String sort = (String) sortField;
            String order = (String) sortOrder;
        
            services = serviceService.getSortedServices(sort, order);
        }
    
        request.setAttribute("services", services);
    }
    
    private void fillSubscriptions(HttpServletRequest request, Connection connection) {
        SubscriberService subscriberService = new SubscriberService(connection);
        SubscriptionService subscriptionService = new SubscriptionService(connection);
        Subscriber subscriber;
        List<Subscription> subscriptions;
        
        Object userObj = request.getSession().getAttribute("user");
        if (userObj != null && !((User) userObj).isAdmin()) {
            User user = (User) userObj;
            
            subscriber = subscriberService.findSubscriberByUserId(user.getId());
            subscriptions = subscriptionService.getSubscriptions(subscriber.getId());
            
            request.setAttribute("subscriptions", subscriptions);
        }
    }
    
}