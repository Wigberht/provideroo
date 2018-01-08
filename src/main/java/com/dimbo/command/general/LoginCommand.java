package com.dimbo.command.general;

import com.dimbo.command.Command;
import com.dimbo.helpers.auth.Auth;
import com.dimbo.managers.PagesResourceManager;
import com.dimbo.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;


/**
 * The Class LoginCommand.
 */
public class LoginCommand implements Command {

    /**
     * The Constant LOGGER.
     */
    static final Logger LOGGER = LoggerFactory.getLogger(LoginCommand.class);

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(HttpServletRequest request) {
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        User user = Auth.login(login, password);

        if (user == null) {
            return PagesResourceManager.getPage("login");
        }

        request.getSession().setAttribute("user", user);

        if (user.isAdmin()) {
            return PagesResourceManager.getPage("admin_control_panel");
        } else {
            return PagesResourceManager.getPage("subscriber_control_panel");
        }
    }
}
