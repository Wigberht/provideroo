package com.dimbo.dao.models.message;

import com.dimbo.dao.DAOException;
import com.dimbo.dao.models.DAOModel;
import com.dimbo.model.Message;
import com.dimbo.model.Tariff;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class MessageDAOMySQL extends DAOModel implements MessageDAO {
    private static final String GET_MESSAGES_BY_CHAT_ID = "SELECT\n" +
        "  message.id,\n" +
        "  message.message,\n" +
        "  message.user_id,\n" +
        "  message.created_at,\n" +
        "  message.updated_at\n" +
        "FROM message\n" +
        "  INNER JOIN message_chat ON message.id = message_chat.message_id\n" +
        "  INNER JOIN chat ON message_chat.chat_id = chat.id\n" +
        "WHERE chat_id = ?";
    
    Connection connection;
    
    public MessageDAOMySQL(Connection connection) {
        this.connection = connection;
    }
    
    public List<Message> getMessages(int chatId) {
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
    
    private Message extractMessage(ResultSet rs) throws SQLException {
        Message msg = new Message();
        
        msg.setId(rs.getInt("id"));
        msg.setMessage(rs.getString("message"));
        msg.setUserId(rs.getInt("user_id"));
        
        return msg;
    }
    
    @Override
    public boolean deleteMessage(int id) {
        return false;
    }
    
    @Override
    public boolean addMessage(int chatId, Message message) {
        return false;
    }
    
    private Message map(ResultSet resultSet) throws SQLException {
        return new Message(
            resultSet.getLong("id"),
            resultSet.getString("message"),
            resultSet.getBoolean("read"),
            resultSet.getString("updated_at"),
            resultSet.getString("created_at"),
            resultSet.getLong("user_id")
        );
    }
}
