package com.dimbo.command.admin;

import com.dimbo.ConnectionPool;
import com.dimbo.command.Command;
import com.dimbo.dao.factory.FactoryGenerator;
import com.dimbo.dao.models.service.ServiceDAO;
import com.dimbo.helper.ServiceService;
import com.dimbo.helper.SubscriberService;
import com.dimbo.managers.PagesResourceManager;
import com.dimbo.model.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.util.List;

public class AddServiceCommand implements Command {
    
    Logger LOGGER = LoggerFactory.getLogger(AddServiceCommand.class);
    
    @Override
    public String execute(HttpServletRequest request) {
        String title = request.getParameter("title");
        
        ServiceService ss = new ServiceService();
        
        if (ss.createService(new Service(0, title)) != null) {
            request.getSession().setAttribute("serviceSuccess", true);
        } else {
            request.getSession().setAttribute("serviceSuccess", false);
        }
        
        ss.returnConnection();
        
        return PagesResourceManager.getPage("add_service");
    }
}