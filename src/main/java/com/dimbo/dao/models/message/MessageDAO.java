package com.dimbo.dao.models.message;

import com.dimbo.model.Message;

public interface MessageDAO {

    public Message[] getMessages(int chatId);

    public boolean deleteMessage(int id);

    public boolean addMessage(int chatId, Message message);
}
