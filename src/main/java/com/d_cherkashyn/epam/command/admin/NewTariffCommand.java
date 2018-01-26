package com.d_cherkashyn.epam.command.admin;

import com.d_cherkashyn.epam.command.Command;
import com.d_cherkashyn.epam.helper.service.ServiceService;
import com.d_cherkashyn.epam.manager.PagesResourceManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

public class NewTariffCommand implements Command {
    
    Logger LOGGER = LoggerFactory.getLogger(NewTariffCommand.class);
    
    @Override
    public String execute(HttpServletRequest request) {
        ServiceService serviceService = new ServiceService();
        request.getSession().setAttribute(
            "services", serviceService.getAllServices());
        serviceService.returnConnection();
        
        return PagesResourceManager.getPage("new_tariff_jsp");
    }
}