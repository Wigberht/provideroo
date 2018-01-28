package com.d_cherkashyn.epam.dao.models.message;

import com.d_cherkashyn.epam.dao.DAOException;
import com.d_cherkashyn.epam.dao.models.DAOModel;
import com.d_cherkashyn.epam.model.Message;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of MessageDAO for MYSQL RDBMS
 */
public class MessageDAOMySQL extends DAOModel implements MessageDAO {
    
    private static final String FIND_BY_ID = "SELECT * FROM message where id=?";
    
    private static final String GET_MESSAGES_BY_CHAT_ID = "SELECT\n"
        + "  message.id,\n"
        + "  message.message,\n"
        + "  message.was_read,\n"
        + "  message.user_id,\n"
        + "  message.chat_id,\n"
        + "  message.created_at,\n"
        + "  message.updated_at\n"
        + "FROM message\n"
        + "WHERE message.chat_id = ?";
    
    private static final String CREATE_MESSAGE = "INSERT INTO message" +
        " VALUES(DEFAULT,?,DEFAULT,DEFAULT,DEFAULT,?,?)";
    
    Connection connection;
    
    /**
     * Creates instance of MessageDAO
     *
     * @param connection to be connected with db
     */
    public MessageDAOMySQL(Connection connection) {
        this.connection = connection;
    }
    
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Message find(long id) {
        Message message = null;
        
        try (
            PreparedStatement statement = prepareStatement(connection, FIND_BY_ID, false,
                                                           id);
            ResultSet resultSet = statement.executeQuery()
        ) {
            if (resultSet.next()) {
                message = map(resultSet);
            }
            
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        
        return message;
    }
    
    /**
     * {@inheritDoc}
     */
    public List<Message> getMessages(long chatId) {
        List<Message> messages = new ArrayList<>();
        try (
            PreparedStatement statement = prepareStatement(connection,
                                                           GET_MESSAGES_BY_CHAT_ID,
                                                           false,
                                                           chatId);
            ResultSet resultSet = statement.executeQuery()
        ) {
            while (resultSet.next()) {
                messages.add(map(resultSet));
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        
        return messages;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean deleteMessage(long id) {
        return false;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Message create(Message message) {
        
        try (
            PreparedStatement statement = prepareStatement(
                connection, CREATE_MESSAGE, true,
                message.getMessage(), message.getUserId(), message.getChatId()
            )
        ) {
            statement.executeUpdate();
            
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                message.setId(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        
        return message;
    }
    
    private Message map(ResultSet resultSet) throws SQLException {
        return new Message(
            resultSet.getLong("id"),
            resultSet.getString("message"),
            resultSet.getBoolean("was_read"),
            resultSet.getString("updated_at"),
            resultSet.getString("created_at"),
            resultSet.getLong("user_id"),
            resultSet.getLong("chat_id")
        );
    }
}
