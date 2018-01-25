package com.dimbo.helper.service;

import com.dimbo.dao.factory.FactoryGenerator;
import com.dimbo.model.Chat;
import com.dimbo.model.User;

import java.sql.Connection;
import java.util.List;

public class ChatService extends ServiceHelper {
    
    public ChatService() {
        super();
    }
    
    public ChatService(Connection connection) {
        super(connection);
    }
    
    public List<Chat> getUserChats(long userId) {
        return FactoryGenerator.getFactory()
                               .makeChatDAO(connection)
                               .findByUser(userId);
    }
    
}
