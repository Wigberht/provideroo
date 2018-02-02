package com.d_cherkashyn.epam.command.subscriber;

import com.d_cherkashyn.epam.command.Command;
import com.d_cherkashyn.epam.helper.service.SubscriberService;
import com.d_cherkashyn.epam.manager.PagesResourceManager;
import com.d_cherkashyn.epam.model.Subscriber;
import com.d_cherkashyn.epam.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Command that fetches all required data used to show on a user's profile page
 */
public class SubscriberProfileCommand implements Command {
    Logger LOGGER = LoggerFactory.getLogger(SubscriberProfileCommand.class);
    
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession s = request.getSession();
        
        long userId = ((User) s.getAttribute("user")).getId();
        
        Subscriber subscriber = SubscriberService.findSubscriberByUserId(userId);
        s.setAttribute("subscriber", subscriber);
        
        return PagesResourceManager.getPage("subscriber_profile_jsp");
    }
}
