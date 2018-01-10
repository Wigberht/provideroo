package com.dimbo.command.admin;

import com.dimbo.ConnectionPool;
import com.dimbo.command.Command;
import com.dimbo.dao.factory.FactoryGenerator;
import com.dimbo.managers.PagesResourceManager;
import com.dimbo.model.Subscriber;
import java.sql.Connection;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

public class SubscriberListCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        String page = PagesResourceManager.getPage("subscriber_list");

        Connection connection = ConnectionPool.conn();
        List<Subscriber> subscribers = FactoryGenerator.getFactory()
                                                       .makeSubscriberDAO(connection)
                                                       .all();

        request.setAttribute("subscribers", subscribers);

        return page;
    }
}
