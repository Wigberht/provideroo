package com.dimbo.dao.models.chat;

import com.dimbo.dao.DAOException;
import com.dimbo.model.Chat;
import com.dimbo.model.Message;
import java.util.List;

public interface ChatDAO {

    public Chat find(Long id) throws DAOException;

    public List<Message> findMessages(long chatId) throws DAOException;

    public boolean delete(Long id) throws DAOException;

    public Chat update(Chat chat) throws DAOException;

    public Chat create(Chat chat) throws DAOException;


}
