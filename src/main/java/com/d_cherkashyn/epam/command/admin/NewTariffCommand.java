package com.d_cherkashyn.epam.command.admin;

import com.d_cherkashyn.epam.command.Command;
import com.d_cherkashyn.epam.helper.service.ServiceService;
import com.d_cherkashyn.epam.manager.PagesResourceManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

/**
 * Command that fetches all required data to be used
 * on a page for creating new tariff
 */
public class NewTariffCommand implements Command {
    
    Logger LOGGER = LoggerFactory.getLogger(NewTariffCommand.class);
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute("services", ServiceService.getAllServices());
        
        return PagesResourceManager.getPage("new_tariff_jsp");
    }
}