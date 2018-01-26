package com.d_cherkashyn.epam.command.general;

import com.d_cherkashyn.epam.command.Command;
import com.d_cherkashyn.epam.manager.PagesResourceManager;
import com.d_cherkashyn.epam.model.User;

import javax.servlet.http.HttpServletRequest;

public class ChatListCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        if (((User) request.getSession().getAttribute("user")).isAdmin()) {
            return PagesResourceManager.getPage("chat_list_admin");
        } else {
            return PagesResourceManager.getPage("chat_list_subscriber");
        }
    }
}
