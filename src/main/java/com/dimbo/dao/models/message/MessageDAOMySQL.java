package com.dimbo.dao.models.message;

import com.dimbo.dao.DAOException;
import com.dimbo.dao.models.DAOModel;
import com.dimbo.model.Chat;
import com.dimbo.model.Message;
import com.dimbo.model.Tariff;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class MessageDAOMySQL extends DAOModel implements MessageDAO {
    
    private static final String FIND_BY_ID = "SELECT * FROM message where id=?";
    
    private static final String GET_MESSAGES_BY_CHAT_ID = "SELECT\n" +
        "  msg.id,\n" +
        "  msg.message,\n" +
        "  msg.was_read,\n" +
        "  msg.created_at,\n" +
        "  msg.updated_at,\n" +
        "  msg.user_id\n" +
        "FROM chat AS c\n" +
        "  INNER JOIN message_chat AS mc ON c.id = mc.chat_id\n" +
        "  INNER JOIN message AS msg ON mc.message_id = msg.id\n" +
        "WHERE c.id = ?";
    
    private static final String CREATE_MESSAGE = "INSERT INTO message" +
        " VALUES(DEFAULT,?,DEFAULT,DEFAULT,DEFAULT,?)";
    
    Connection connection;
    
    public MessageDAOMySQL(Connection connection) {
        this.connection = connection;
    }
    
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
    
    @Override
    public boolean deleteMessage(long id) {
        return false;
    }
    
    @Override
    public Message create(Message message) {
        
        try (
            PreparedStatement statement = prepareStatement(
                connection, CREATE_MESSAGE, true,
                message.getMessage(), message.getUserId()
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
            resultSet.getLong("user_id")
        );
    }
}
