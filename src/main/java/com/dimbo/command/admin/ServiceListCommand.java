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

import com.dimbo.rest.JSONService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServiceListCommand implements Command {
    
    Logger LOGGER = LoggerFactory.getLogger(ServiceListCommand.class);
    
    @Override
    public String execute(HttpServletRequest request) {
        
        Connection connection = ConnectionPool.conn();
        
        ServiceService serviceService = new ServiceService(connection);
        SubscriberService subscriberService = new SubscriberService(connection);
        SubscriptionService subscriptionService = new SubscriptionService(connection);
        
        request.setAttribute("services", serviceService.getAllServices());
        
        User user = (User) request.getSession().getAttribute("user");
        if (!user.isAdmin()) {
            Subscriber subscriber = subscriberService
                .findSubscriberByUserId(user.getId());
            
            List<Subscription> subscriptions = subscriptionService
                .getSubscriptions(subscriber.getId());
            
            JSONService jsonService = new JSONService();
            String jsonSubscriptions = jsonService.toJSON(subscriptions);
            
            request.setAttribute("subscriptions", jsonSubscriptions);
        }
        
        subscriberService.returnConnection();
        
        return PagesResourceManager.getPage("service_list_jsp");
    }
}



