package com.dimbo.command.subscriber;

import com.dimbo.command.Command;
import com.dimbo.helper.service.SubscriberService;
import com.dimbo.managers.PagesResourceManager;
import com.dimbo.model.Subscriber;
import com.dimbo.model.User;
import com.dimbo.rest.JSONService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SubscriberProfileCommand implements Command {
    Logger LOGGER = LoggerFactory.getLogger(SubscriberProfileCommand.class);
    
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession s = request.getSession();
        SubscriberService subscriberService = new SubscriberService();
        JSONService jsonService = new JSONService();
        
        long userId = ((User) s.getAttribute("user")).getId();
        
        Subscriber subscriber = subscriberService.findSubscriberByUserId(userId);
        s.setAttribute("subscriber", subscriber);
        
        s.setAttribute("subscriberJSON", jsonService.toJSON(subscriber));
        LOGGER.info("JSON: " + jsonService.toJSON(subscriber));
        
        subscriberService.returnConnection();
        
        return PagesResourceManager.getPage("subscriber_profile_jsp");
    }
}
