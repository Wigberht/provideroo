package com.d_cherkashyn.epam.command.general;

import com.d_cherkashyn.epam.command.Command;
import com.d_cherkashyn.epam.manager.PagesResourceManager;

import javax.servlet.http.HttpServletRequest;

public class LogoutCommand implements Command {
    
    @Override
    public String execute(HttpServletRequest request) {
        request.getSession().removeAttribute("user");
        request.getSession().invalidate();
        
        return PagesResourceManager.getPage("index");
    }
}
