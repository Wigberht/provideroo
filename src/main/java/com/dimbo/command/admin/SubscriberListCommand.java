package com.dimbo.command.admin;

import com.dimbo.command.Command;
import com.dimbo.helper.service.SubscriberService;
import com.dimbo.managers.PagesResourceManager;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SubscriberListCommand implements Command {
    
    Logger LOGGER = LoggerFactory.getLogger(SubscriberListCommand.class);
    
    @Override
    public String execute(HttpServletRequest request) {
        int page = 0;
        int limit = 8;

//        default list size
        request.getSession()
               .setAttribute("subscriber.limit", limit);
        
        boolean containsLimit = request.getParameterMap()
                                       .containsKey("limit");
        boolean limitNumeric = StringUtils.isNumeric(request.getParameter("limit"));

//        handle limit for list
        if (containsLimit && limitNumeric) {
            limit = Integer.parseInt(request.getParameter("limit"));
            request.getSession()
                   .setAttribute("subsriber.limit", limit);
        }

//        handle page in list
        if (request.getParameterMap()
                   .containsKey("page")) {
            page = Integer.parseInt(request.getParameter("page"));
        }
        
        SubscriberService ss = new SubscriberService(page, limit);
        
        request.setAttribute("subscribers", ss.getSubscribers());
        ss.returnConnection();
        
        return PagesResourceManager.getPage("subscriber_list_jsp");
    }
}