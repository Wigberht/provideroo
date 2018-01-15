package com.dimbo.command.admin;

import com.dimbo.command.Command;
import com.dimbo.helper.service.TariffService;
import com.dimbo.managers.PagesResourceManager;
import com.dimbo.model.Tariff;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

public class AddTariffCommand implements Command {
    
    Logger LOGGER = LoggerFactory.getLogger(AddTariffCommand.class);
    
    @Override
    public String execute(HttpServletRequest request) {
        String page;
        
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        int days = Integer.parseInt(request.getParameter("days"));
        double cost = Double.parseDouble(request.getParameter("cost"));
        String currency = request.getParameter("currency");
        long serviceId = Long.parseLong(request.getParameter("service_id"));
        
        TariffService ts = new TariffService();
        Tariff newTariff = new Tariff(0, title, description, days, cost, currency, serviceId);
        
        if (ts.createTariff(newTariff) != null) {
            request.getSession().setAttribute("tariffSuccess", true);
            page = PagesResourceManager.getPage("service_list");
        } else {
            request.getSession().setAttribute("tariffSuccess", false);
            page = PagesResourceManager.getPage("add_tariff");
        }
        
        ts.returnConnection();
        
        return page;
    }
}