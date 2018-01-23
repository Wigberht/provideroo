package com.dimbo.command.general;

import com.dimbo.command.Command;
import com.dimbo.manager.PagesResourceManager;

import javax.servlet.http.HttpServletRequest;

public class LogoutCommand implements Command {
    
    @Override
    public String execute(HttpServletRequest request) {
        request.getSession().removeAttribute("user");
        request.getSession().invalidate();
        
        return PagesResourceManager.getPage("index");
    }
}
