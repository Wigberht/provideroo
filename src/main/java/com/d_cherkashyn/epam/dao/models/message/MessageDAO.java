package com.d_cherkashyn.epam.dao.models.message;

import com.d_cherkashyn.epam.model.Message;

import java.util.List;

/**
 * Interface with methods required to manage Message in DB as DAO
 */
public interface MessageDAO {
    
    /**
     * Find message by id
     *
     * @param id
     * @return
     */
    Message find(long id);
    
    /**
     * Get messages from specific chat
     *
     * @param chatId
     * @return
     */
    List<Message> getMessages(long chatId);
    
    /**
     * Delete message by id
     *
     * @param id
     * @return
     */
    boolean deleteMessage(long id);
    
    /**
     * Creates message
     *
     * @param message
     * @return
     */
    Message create(Message message);
}
