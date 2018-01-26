package com.dimbo.helper.service;

import com.dimbo.dao.factory.FactoryGenerator;
import com.dimbo.model.Chat;
import com.dimbo.model.Message;
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
    
    public Chat getChat(long chatId) {
        Chat chat = FactoryGenerator.getFactory()
                                    .makeChatDAO(connection)
                                    .find(chatId);
        List<Message> messages = FactoryGenerator.getFactory()
                                                 .makeMessageDAO(connection)
                                                 .getMessages(chatId);
        
        List<User> members = FactoryGenerator.getFactory()
                                             .makeUserDAO(connection)
                                             .findChatMembers(chatId);
        
        chat.setMessages(messages);
        chat.setUsers(members);
        
        return chat;
    }
    
    public Message pushMessage(Message message, long chatId) {
        message = FactoryGenerator.getFactory()
                                  .makeMessageDAO(connection)
                                  .create(message);
        
        if (message.getId() > 0) {
            message = FactoryGenerator.getFactory()
                                      .makeMessageDAO(connection)
                                      .find(message.getId());
            
            FactoryGenerator.getFactory()
                            .makeChatDAO(connection)
                            .addMessage(chatId, message.getId());
        }
        
        return message;
    }
    
    public Chat createChat(long creatorId, String creatorLogin,
                           long receiverId, String receiverLogin) {
        StringBuilder sb = new StringBuilder();
        
        String chatTitle;
        if (receiverId != creatorId) {
            chatTitle = sb.append(creatorLogin)
                          .append(" => ")
                          .append(receiverLogin)
                          .toString();
        } else {
            chatTitle = creatorLogin;
        }
        
        Chat chat = new Chat(0, chatTitle);
        chat = FactoryGenerator.getFactory()
                               .makeChatDAO(connection)
                               .create(chat);
        
        boolean creatorAdded;
        boolean receiverAdded;
        boolean success = false;
        
        if (receiverId == creatorId) {
            success = FactoryGenerator.getFactory()
                                      .makeChatDAO(connection)
                                      .addUserToChat(creatorId, chat.getId());
        } else if (chat.getId() != 0) {
            creatorAdded = FactoryGenerator.getFactory()
                                           .makeChatDAO(connection)
                                           .addUserToChat(creatorId, chat.getId());
            
            receiverAdded = FactoryGenerator.getFactory()
                                            .makeChatDAO(connection)
                                            .addUserToChat(receiverId, chat.getId());
            success = creatorAdded && receiverAdded;
        }
        
        if (success) {
            return chat;
        } else {
            return null;
        }
    }
    
    public Chat findCommonChat(long creatorId, long receiverId) {
        List<Chat> creatorChats = FactoryGenerator.getFactory()
                                                  .makeChatDAO(connection)
                                                  .findByUser(creatorId);
        
        List<Chat> receiverChats = FactoryGenerator.getFactory()
                                                   .makeChatDAO(connection)
                                                   .findByUser(receiverId);
        
        Chat commonChat = null;
        
        for (Chat c : creatorChats) {
            for (Chat c1 : receiverChats) {
                if (c.getId() == c1.getId()) {
                    commonChat = c;
                }
            }
        }
        
        
        return commonChat;
    }
}