package com.d_cherkashyn.epam.dao.models.chat;

import com.d_cherkashyn.epam.ConnectionPool;
import com.d_cherkashyn.epam.dao.DAOException;
import com.d_cherkashyn.epam.dao.models.DAOModel;
import com.d_cherkashyn.epam.model.Chat;
import com.d_cherkashyn.epam.model.Message;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of ChatDAO for MYSQL RDBMS
 */
public class ChatDAOMySQL extends DAOModel implements ChatDAO {
    
    private static final String FIND_BY_ID = "SELECT DISTINCT * FROM chat WHERE id = ?";
    private static final String FIND_CHATS_BY_USER = "SELECT c.id,c.title\n" +
        "FROM chat as c\n" +
        "INNER JOIN chat_user ON c.id = chat_user.chat_id\n" +
        "WHERE user_id=?";
    
    private static final String FIND_MESSAGES = "SELECT\n"
        + "  message.id,\n"
        + "  message.message,\n"
        + "  message.user_id,\n"
        + "  message.chat_id,\n"
        + "  message.created_at,\n"
        + "  message.created_at\n"
        + "FROM message\n"
        + "WHERE message.chat_id = ?";
    private static final String CREATE_CHAT = "INSERT INTO chat VALUES(DEFAULT, ?)";
    private static final String UPDATE_CHAT_BY_ID = "UPDATE chat "
        + "SET title = ? "
        + "WHERE id = ?";
    
    private static final String ADD_MESSAGE = "INSERT INTO message_chat VALUES(?,?)";
    private static final String ADD_USER_TO_CHAT = "INSERT INTO chat_user VALUES(?,?)";
    
    
    private static final String DELETE_CHAT_BY_ID = "DELETE FROM chat WHERE id = ?";
    
    /**
     * Creates instance of ChatDAO
     */
    public ChatDAOMySQL() {}
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Chat find(Long id) throws DAOException {
        Chat chat = null;
        
        Connection connection = ConnectionPool.conn();
        try (
            PreparedStatement statement = prepareStatement(connection, FIND_BY_ID, false,
                                                           id);
            ResultSet resultSet = statement.executeQuery()
        ) {
            if (resultSet.next()) {
                chat = map(resultSet);
            }
            
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            ConnectionPool.returnConn(connection);
        }
        
        return chat;
    }
    
    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean addUserToChat(long userId, long chatId) throws DAOException {
        Connection connection = ConnectionPool.conn();
        try (
            PreparedStatement statement = prepareStatement(
                connection, ADD_USER_TO_CHAT, true,
                userId, chatId
            )
        ) {
            
            return (statement.executeUpdate() > 0);
            
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            ConnectionPool.returnConn(connection);
        }
    }
    
    /**
     * {@inheritDoc}
     */
    public List<Chat> findByUser(long userId) {
        List<Chat> chats = new ArrayList<>();
        Connection connection = ConnectionPool.conn();
        try (
            PreparedStatement stmt = prepareStatement(connection, FIND_CHATS_BY_USER,
                                                      false,
                                                      userId);
            ResultSet resultSet = stmt.executeQuery()
        ) {
            while (resultSet.next()) {
                chats.add(map(resultSet));
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            ConnectionPool.returnConn(connection);
        }
        
        return chats;
    }
    
    
    /**
     * {@inheritDoc}
     */
    @Override
    public List<Message> findMessages(long chatId) {
        List<Message> messages = new ArrayList<>();
        Connection connection = ConnectionPool.conn();
        try (
            PreparedStatement stmt = prepareStatement(connection, FIND_MESSAGES,
                                                      false,
                                                      chatId);
            ResultSet resultSet = stmt.executeQuery()
        ) {
            while (resultSet.next()) {
                messages.add(mapMessage(resultSet));
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            ConnectionPool.returnConn(connection);
        }
        
        return messages;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean delete(Long id) throws DAOException {
        return false;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Chat update(Chat chat) throws DAOException {
        return null;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Chat create(Chat chat) throws DAOException {
        Connection connection = ConnectionPool.conn();
        try (
            PreparedStatement statement = prepareStatement(
                connection, CREATE_CHAT, true,
                chat.getTitle()
            )
        ) {
            statement.executeUpdate();
            
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                chat.setId(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            ConnectionPool.returnConn(connection);
        }
        
        return chat;
    }
    
    private static Chat map(ResultSet resultset) throws SQLException {
        return new Chat(
            resultset.getLong("id"),
            resultset.getString("title")
        );
    }
    
    private static Message mapMessage(ResultSet resultSet) throws SQLException {
        return new Message(
            resultSet.getLong("id"),
            resultSet.getString("message"),
            resultSet.getBoolean("read"),
            resultSet.getString("updated_at"),
            resultSet.getString("created_at"),
            resultSet.getLong("user_id"),
            resultSet.getLong("chat_id")
        );
    }
}
