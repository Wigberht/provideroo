package com.d_cherkashyn.epam.command.admin;

import com.d_cherkashyn.epam.command.Command;
import com.d_cherkashyn.epam.helper.service.ServiceService;
import com.d_cherkashyn.epam.helper.validator.MainValidator;
import com.d_cherkashyn.epam.manager.PagesResourceManager;
import com.d_cherkashyn.epam.model.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


/**
 * Command that processes new service addition to system
 */
public class AddServiceCommand implements Command {
    
    private Logger LOGGER = LoggerFactory.getLogger(AddServiceCommand.class);
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(HttpServletRequest request) {
        String page;
        String title = request.getParameter("title");
        HttpSession s = request.getSession();
        
        if (!MainValidator.simpleText(title)) {
            s.setAttribute("validationError", true);
            s.setAttribute("serviceError", true);
            
            page = PagesResourceManager.getPage("add_service");
        } else {
            if (ServiceService.createService(new Service(0, title)) != null) {
                request.getSession().setAttribute("serviceSuccess", true);
            }
            
            page = PagesResourceManager.getPage("service_list");
        }
        
        return page;
    }
}