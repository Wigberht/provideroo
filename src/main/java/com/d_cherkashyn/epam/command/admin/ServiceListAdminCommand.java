package com.d_cherkashyn.epam.command.admin;

import com.d_cherkashyn.epam.command.Command;
import com.d_cherkashyn.epam.helper.service.ServiceService;
import com.d_cherkashyn.epam.manager.PagesResourceManager;
import com.d_cherkashyn.epam.model.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Command that fetches all required data used to show on a page with list of tariffs
 */
public class ServiceListAdminCommand implements Command {
    
    Logger LOGGER = LoggerFactory.getLogger(ServiceListAdminCommand.class);
    
    @Override
    public String execute(HttpServletRequest request) {
        
        List<Service> services;
        
        Object sortField = request.getParameter("sortField");
        Object sortOrder = request.getParameter("sortOrder");
        
        if (sortOrder == null || sortField == null) {
            services = ServiceService.getAllServices();
        } else {
            String sort = (String) sortField;
            String order = (String) sortOrder;
            
            services = ServiceService.getSortedServices(sort, order);
        }
        request.setAttribute("services", services);
        
        return PagesResourceManager.getPage("service_list_jsp");
    }
}