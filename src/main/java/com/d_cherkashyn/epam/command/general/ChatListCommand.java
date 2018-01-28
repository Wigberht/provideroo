package com.d_cherkashyn.epam.command.general;

import com.d_cherkashyn.epam.command.Command;
import com.d_cherkashyn.epam.manager.PagesResourceManager;
import com.d_cherkashyn.epam.model.User;

import javax.servlet.http.HttpServletRequest;

/**
 * The Class ChatListCommand provides functionality to forward user to specific page
 * based on his role.
 */
public class ChatListCommand implements Command {
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(HttpServletRequest request) {
        if (((User) request.getSession().getAttribute("user")).isAdmin()) {
            return PagesResourceManager.getPage("chat_list_admin");
        } else {
            return PagesResourceManager.getPage("chat_list_subscriber");
        }
    }
}
