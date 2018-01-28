package com.d_cherkashyn.epam.command.admin;

import com.d_cherkashyn.epam.command.Command;
import com.d_cherkashyn.epam.helper.service.TariffService;
import com.d_cherkashyn.epam.helper.validator.MainValidator;
import com.d_cherkashyn.epam.manager.PagesResourceManager;
import com.d_cherkashyn.epam.model.Tariff;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Command that processes addition of a new tariff to system
 */
public class AddTariffCommand implements Command {
    
    Logger LOGGER = LoggerFactory.getLogger(AddTariffCommand.class);
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(HttpServletRequest request) {
        String page;
        HttpSession s = request.getSession();
        
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        String days = request.getParameter("days");
        String cost = request.getParameter("cost");
        String currency = request.getParameter("currency");
        String serviceId = request.getParameter("service_id");
        
        boolean validationError = false;
        if (!MainValidator.simpleText(title)) {
            s.setAttribute("titleError", true);
            validationError = true;
        }
        
        if (!MainValidator.simpleText(description)) {
            s.setAttribute("descriptionError", true);
            validationError = true;
        }
        
        if (!MainValidator.positiveNumber(String.valueOf(days))) {
            s.setAttribute("daysError", true);
            validationError = true;
        }
        
        if (!MainValidator.positiveNumber(String.valueOf(cost))) {
            s.setAttribute("costError", true);
            validationError = true;
        }
        
        if (!MainValidator.simpleText(currency)) {
            s.setAttribute("currencyError", true);
            validationError = true;
        }
        
        if (validationError) {
            s.setAttribute("validationError", true);
            return PagesResourceManager.getPage("new_tariff");
        }
        
        
        TariffService ts = new TariffService();
        Tariff newTariff = new Tariff(0,
                                      title,
                                      description,
                                      Integer.parseInt(days),
                                      Double.parseDouble(cost),
                                      currency,
                                      Long.parseLong(serviceId));
        
        if (ts.createTariff(newTariff) != null) {
            page = PagesResourceManager.getPage("service_list");
        } else {
            request.getSession().setAttribute("tariffSuccess", false);
            page = PagesResourceManager.getPage("new_tariff");
        }
        
        ts.returnConnection();
        
        return page;
    }
}