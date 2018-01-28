package com.d_cherkashyn.epam.command.general;

import com.d_cherkashyn.epam.command.Command;
import com.d_cherkashyn.epam.helper.auth.Auth;
import com.d_cherkashyn.epam.manager.PagesResourceManager;
import com.d_cherkashyn.epam.model.User;
import com.d_cherkashyn.epam.rest.JSONService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;


/**
 * The Class LoginCommand provides functionality to authenticate user.
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
        
        user.setPassword(null);
        request.getSession().setAttribute("user", user);
        request.getSession().setAttribute("banned", user.isBanned());
        
        /*for front-end purposes*/
        request.getSession().setAttribute("userJSON", JSONService.toJSON(user));
        
        if (user.isAdmin()) {
            return PagesResourceManager.getPage("admin.control_panel");
        } else {
            return PagesResourceManager.getPage("subscriber.control_panel");
        }
    }
}
