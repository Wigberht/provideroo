package com.dimbo.command.admin;

import com.dimbo.command.Command;
import com.dimbo.helper.service.ServiceService;
import com.dimbo.helper.validator.MainValidator;
import com.dimbo.managers.PagesResourceManager;
import com.dimbo.model.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class AddServiceCommand implements Command {
    
    private Logger LOGGER = LoggerFactory.getLogger(AddServiceCommand.class);
    
    @Override
    public String execute(HttpServletRequest request) {
        String page = PagesResourceManager.getPage("add_service");
        String title = request.getParameter("title");
        HttpSession s = request.getSession();
        
        LOGGER.info("Service title is: '" + title + "'");
        LOGGER.info("Valid: " + MainValidator.simpleText(title));
        if (!MainValidator.simpleText(title)) {
            s.setAttribute("validationError", true);
            s.setAttribute("serviceError", true);
            
            return page;
        }
        
        ServiceService ss = new ServiceService();
        
        if (ss.createService(new Service(0, title)) != null) {
            request.getSession().setAttribute("serviceSuccess", true);
        } else {
            request.getSession().setAttribute("serviceSuccess", false);
        }
        
        request.getSession()
               .getServletContext()
               .setAttribute("services", ss.getAllServices());
        
        ss.returnConnection();
        
        return page;
    }
}