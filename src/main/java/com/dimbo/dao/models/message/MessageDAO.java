package com.dimbo.dao.models.message;

import com.dimbo.model.Message;

import java.util.List;

public interface MessageDAO {

    public List<Message> getMessages(int chatId);

    public boolean deleteMessage(int id);

    public boolean addMessage(int chatId, Message message);
}
