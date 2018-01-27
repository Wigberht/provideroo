package com.d_cherkashyn.epam.command.subscriber;

import com.d_cherkashyn.epam.command.Command;
import com.d_cherkashyn.epam.helper.service.SubscriberService;
import com.d_cherkashyn.epam.manager.PagesResourceManager;
import com.d_cherkashyn.epam.model.Subscriber;
import com.d_cherkashyn.epam.model.User;
import com.d_cherkashyn.epam.rest.JSONService;
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
        
        long userId = ((User) s.getAttribute("user")).getId();
        
        Subscriber subscriber = subscriberService.findSubscriberByUserId(userId);
        s.setAttribute("subscriber", subscriber);
        
        subscriberService.returnConnection();
        
        return PagesResourceManager.getPage("subscriber_profile_jsp");
    }
}
