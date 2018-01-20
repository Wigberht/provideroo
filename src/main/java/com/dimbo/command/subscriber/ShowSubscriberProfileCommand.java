package com.dimbo.command.subscriber;

import com.dimbo.command.Command;
import com.dimbo.helper.service.SubscriberService;
import com.dimbo.managers.PagesResourceManager;
import com.dimbo.model.Subscriber;
import com.dimbo.model.User;

import javax.servlet.http.HttpServletRequest;

public class ShowSubscriberProfileCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        
        long userId = ((User) request.getSession()
                                     .getAttribute("user")).getId();
        
        SubscriberService subscriberService = new SubscriberService();
        Subscriber subscriber = subscriberService
            .findSubscriberByUserId(userId);
        
        request.getSession().setAttribute("subscriber", subscriber);
        
        subscriberService.returnConnection();
        
        return PagesResourceManager.getPage("subscriber_profile");
    }
}
