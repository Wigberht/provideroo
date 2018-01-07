package com.dimbo.command.general;

import com.dimbo.command.Command;
import com.dimbo.helpers.auth.Registration;
import com.dimbo.managers.PagesResourceManager;
import com.dimbo.model.User;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class RegistrationCommand implements Command {

    private static final Logger LOGGER = LoggerFactory.getLogger(RegistrationCommand.class);

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(HttpServletRequest request) {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String repeat_password = request.getParameter("repeat_password");

        Registration.register(new User(login, password));

        String page = PagesResourceManager.getPage("registration");

        return page;
    }
}
