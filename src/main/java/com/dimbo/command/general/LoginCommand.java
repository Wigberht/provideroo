package com.dimbo.command.general;

import com.dimbo.command.Command;
import com.dimbo.helper.auth.Auth;
import com.dimbo.manager.PagesResourceManager;
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
            request.getSession().setAttribute("validationError", true);
            request.getSession().setAttribute("authError", true);
            return PagesResourceManager.getPage("login");
        }
        
        request.getSession().invalidate();
        request.getSession(true);
        
        request.getSession().setAttribute("user", user);
        request.getSession().setAttribute("banned", user.isBanned());
        
        if (user.isAdmin()) {
            return PagesResourceManager.getPage("admin.control_panel");
        } else {
            return PagesResourceManager.getPage("subscriber.control_panel");
        }
    }
}
