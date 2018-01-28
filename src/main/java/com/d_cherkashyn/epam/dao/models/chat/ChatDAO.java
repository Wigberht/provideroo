package com.d_cherkashyn.epam.dao.models.chat;

import com.d_cherkashyn.epam.dao.DAOException;
import com.d_cherkashyn.epam.model.Chat;
import com.d_cherkashyn.epam.model.Message;

import java.util.List;

/**
 * Interface with methods required to manage Chat in DB as DAO
 */
public interface ChatDAO {
    
    /**
     * Seeks chat by id
     *
     * @param id
     * @return
     * @throws DAOException
     */
    Chat find(Long id) throws DAOException;
    
    /**
     * Binds user to chat
     *
     * @param userId
     * @param chatId
     * @return
     * @throws DAOException
     */
    boolean addUserToChat(long userId, long chatId) throws DAOException;
    
    /**
     * Finds chats of specific user
     *
     * @param userId
     * @return
     * @throws DAOException
     */
    List<Chat> findByUser(long userId) throws DAOException;
    
    /**
     * Find messages of specific chat
     *
     * @param chatId
     * @return
     * @throws DAOException
     */
    List<Message> findMessages(long chatId) throws DAOException;
    
    /**
     * Deletes chat by id
     *
     * @param id
     * @return
     * @throws DAOException
     */
    boolean delete(Long id) throws DAOException;
    
    /**
     * Updates chat
     *
     * @param chat
     * @return
     * @throws DAOException
     */
    Chat update(Chat chat) throws DAOException;
    
    /**
     * Creates chat
     *
     * @param chat
     * @return
     * @throws DAOException
     */
    Chat create(Chat chat) throws DAOException;
    
    
}
