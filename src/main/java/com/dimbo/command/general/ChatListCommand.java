package com.dimbo.command.general;

import com.dimbo.command.Command;
import com.dimbo.manager.PagesResourceManager;
import com.dimbo.model.User;

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
