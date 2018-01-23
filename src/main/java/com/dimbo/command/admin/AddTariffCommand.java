package com.dimbo.command.admin;

import com.dimbo.command.Command;
import com.dimbo.helper.service.TariffService;
import com.dimbo.helper.validator.MainValidator;
import com.dimbo.manager.PagesResourceManager;
import com.dimbo.model.Tariff;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class AddTariffCommand implements Command {
    
    Logger LOGGER = LoggerFactory.getLogger(AddTariffCommand.class);
    
    @Override
    public String execute(HttpServletRequest request) {
        String page;
        HttpSession s = request.getSession();
        
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        int days = Integer.parseInt(request.getParameter("days"));
        double cost = Double.parseDouble(request.getParameter("cost"));
        String currency = request.getParameter("currency");
        long serviceId = Long.parseLong(request.getParameter("service_id"));
        
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
        Tariff newTariff = new Tariff(0, title, description, days, cost, currency,
                                      serviceId);
        
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