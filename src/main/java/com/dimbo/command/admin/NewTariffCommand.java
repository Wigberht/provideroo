package com.dimbo.command.admin;

import com.dimbo.command.Command;
import com.dimbo.helper.service.ServiceService;
import com.dimbo.helper.service.TariffService;
import com.dimbo.managers.PagesResourceManager;
import com.dimbo.model.Tariff;
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