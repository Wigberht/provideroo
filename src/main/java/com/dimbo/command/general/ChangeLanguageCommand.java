package com.dimbo.command.general;

import com.dimbo.command.Command;
import com.dimbo.helper.auth.Auth;
import com.dimbo.managers.PagesResourceManager;
import com.dimbo.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;


/**
 * The Class LoginCommand.
 */
public class ChangeLanguageCommand implements Command {
    
    /**
     * The Constant LOGGER.
     */
    static final Logger LOGGER = LoggerFactory.getLogger(ChangeLanguageCommand.class);
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(HttpServletRequest request) {
        String languageCode = request.getParameter("language");
        
        request.getSession().setAttribute("locale", languageCode);
        
        return request.getHeader("referer");
    }
}
