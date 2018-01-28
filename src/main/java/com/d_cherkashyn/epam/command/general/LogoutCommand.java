package com.d_cherkashyn.epam.command.general;

import com.d_cherkashyn.epam.command.Command;
import com.d_cherkashyn.epam.manager.PagesResourceManager;

import javax.servlet.http.HttpServletRequest;

/**
 * The Class LogoutCommand provides functionality to de-authenticate user.
 */
public class LogoutCommand implements Command {
    
    @Override
    public String execute(HttpServletRequest request) {
        request.getSession().invalidate();
        
        return PagesResourceManager.getPage("index");
    }
}
