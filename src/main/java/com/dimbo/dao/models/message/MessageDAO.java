package com.dimbo.dao.models.message;

import com.dimbo.model.Message;

import java.util.List;

public interface MessageDAO {
    
    Message find(long id);
    
    List<Message> getMessages(long chatId);
    
    boolean deleteMessage(long id);
    
    Message create(Message message);
}
