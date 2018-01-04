package com.dimbo.dao.message;

import com.dimbo.bean.Message;

public interface MessageDAO {

    public Message[] getMessages(int chatId);

    public boolean deleteMessage(int id);

    public boolean addMessage(int chatId, Message message);
}
