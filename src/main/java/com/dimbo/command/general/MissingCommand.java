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
public class MissingCommand implements Command {
    
    /**
     * The Constant LOGGER.
     */
    static final Logger LOGGER = LoggerFactory.getLogger(MissingCommand.class);
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(HttpServletRequest request) {
        return PagesResourceManager.getPage("index");
    }
}
