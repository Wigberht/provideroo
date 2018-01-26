package com.d_cherkashyn.epam.command.admin;

import com.d_cherkashyn.epam.ConnectionPool;
import com.d_cherkashyn.epam.command.Command;
import com.d_cherkashyn.epam.dao.factory.FactoryGenerator;
import com.d_cherkashyn.epam.dao.models.service.ServiceDAO;
import com.d_cherkashyn.epam.manager.PagesResourceManager;
import com.d_cherkashyn.epam.model.Service;
import java.sql.Connection;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

public class TariffListCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {

        Connection connection = ConnectionPool.conn();
        ServiceDAO serviceDAO = FactoryGenerator.getFactory()
                                                .makeServiceDAO(connection);

        List<Service> services = serviceDAO.all();
        request.setAttribute("services", services);

        ConnectionPool.returnConn(connection);

        return PagesResourceManager.getPage("tariff_list");
    }
}



