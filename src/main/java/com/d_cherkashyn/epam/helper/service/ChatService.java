package com.d_cherkashyn.epam.helper.service;

import com.d_cherkashyn.epam.dao.factory.DAOFactory;
import com.d_cherkashyn.epam.model.Chat;
import com.d_cherkashyn.epam.model.Message;
import com.d_cherkashyn.epam.model.User;

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
        return DAOFactory.getFactory()
                         .makeChatDAO()
                         .findByUser(userId);
    }
    
    public Chat getChat(long chatId) {
        Chat chat = DAOFactory.getFactory()
                              .makeChatDAO()
                              .find(chatId);
        List<Message> messages = DAOFactory.getFactory()
                                           .makeMessageDAO()
                                           .getMessages(chatId);
        
        List<User> members = DAOFactory.getFactory()
                                       .makeUserDAO()
                                       .findChatMembers(chatId);
        
        chat.setMessages(messages);
        chat.setUsers(members);
        
        return chat;
    }
    
    public Message pushMessage(Message message) {
        message = DAOFactory.getFactory()
                            .makeMessageDAO()
                            .create(message);
        
        /* get new one because it has id, created_at and updated_at data */
        if (message.getId() > 0) {
            message = DAOFactory.getFactory()
                                .makeMessageDAO()
                                .find(message.getId());
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
        chat = DAOFactory.getFactory()
                         .makeChatDAO()
                         .create(chat);
        
        boolean creatorAdded;
        boolean receiverAdded;
        boolean success = false;
        
        if (receiverId == creatorId) {
            success = DAOFactory.getFactory()
                                .makeChatDAO()
                                .addUserToChat(creatorId, chat.getId());
        } else if (chat.getId() != 0) {
            creatorAdded = DAOFactory.getFactory()
                                     .makeChatDAO()
                                     .addUserToChat(creatorId, chat.getId());
            
            receiverAdded = DAOFactory.getFactory()
                                      .makeChatDAO()
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
        List<Chat> creatorChats = DAOFactory.getFactory()
                                            .makeChatDAO()
                                            .findByUser(creatorId);
        
        List<Chat> receiverChats = DAOFactory.getFactory()
                                             .makeChatDAO()
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