package com.d_cherkashyn.epam.dao.models.chat;

import com.d_cherkashyn.epam.dao.DAOException;
import com.d_cherkashyn.epam.model.Chat;
import com.d_cherkashyn.epam.model.Message;

import java.util.List;

public interface ChatDAO {
    
    Chat find(Long id) throws DAOException;
    
    boolean addMessage(long chatId, long messageId) throws DAOException;
    
    boolean addUserToChat(long userId, long chatId) throws DAOException;
    
    List<Chat> findByUser(long userId) throws DAOException;
    
    List<Message> findMessages(long chatId) throws DAOException;
    
    boolean delete(Long id) throws DAOException;
    
    Chat update(Chat chat) throws DAOException;
    
    Chat create(Chat chat) throws DAOException;
    
    
}
