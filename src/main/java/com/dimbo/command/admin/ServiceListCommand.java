package com.dimbo.command.admin;

import com.dimbo.ConnectionPool;
import com.dimbo.command.Command;
import com.dimbo.dao.factory.FactoryGenerator;
import com.dimbo.dao.models.service.ServiceDAO;
import com.dimbo.managers.PagesResourceManager;
import com.dimbo.model.Service;
import java.sql.Connection;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServiceListCommand implements Command {

    Logger LOGGER = LoggerFactory.getLogger(ServiceListCommand.class);

    @Override
    public String execute(HttpServletRequest request) {

        Connection connection = ConnectionPool.conn();
        ServiceDAO serviceDAO = FactoryGenerator.getFactory()
                                                .makeServiceDAO(connection);

        List<Service> services = serviceDAO.all();
        request.setAttribute("services", services);

        ConnectionPool.returnConn(connection);

        return PagesResourceManager.getPage("service_list_jsp");
    }
}



