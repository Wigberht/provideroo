package com.d_cherkashyn.epam.command.admin;

import com.d_cherkashyn.epam.command.Command;
import com.d_cherkashyn.epam.helper.Pagination;
import com.d_cherkashyn.epam.helper.service.SubscriberService;
import com.d_cherkashyn.epam.manager.PagesResourceManager;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

/**
 * Command that fetches and shows data about list of subscribers available to system
 */
public class SubscriberListCommand implements Command {
    
    Logger LOGGER = LoggerFactory.getLogger(SubscriberListCommand.class);
    
    @Override
    public String execute(HttpServletRequest request) {
        int page = 0;
        int limit = 10;
        
        request.getSession()
               .setAttribute("subscriber.limit", limit);
        
        boolean containsLimit = request.getParameterMap()
                                       .containsKey("limit");
        boolean limitNumeric = StringUtils.isNumeric(request.getParameter("limit"));
        
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
        
        long numberOfSubscribers = ss.getNumberOfSubscribers();
        int pages = (int) Math.ceil((double) numberOfSubscribers / 10);
        
        Pagination pagination = new Pagination();
        for (int i = 0; i < pages; i++) {
            pagination.addPage(PagesResourceManager.getPage("subscriber_list"), i + 1);
        }
        request.setAttribute("pagination", pagination);
        LOGGER.info("pages: {}, users :{}", pages, numberOfSubscribers);
        
        ss.returnConnection();
        
        return PagesResourceManager.getPage("subscriber_list_jsp");
    }
}