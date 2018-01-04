package com.dimbo.dao.message;

import com.dimbo.bean.Message;
import com.dimbo.dao.DAOCommon;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MysqlMessage extends DAOCommon implements MessageDAO {
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

    public MysqlMessage(Connection connection) {
        this.connection = connection;
    }

    public Message[] getMessages(int chatId) {
        List<Message> messages = new ArrayList<>();
        ResultSet rs;
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(GET_MESSAGES_BY_CHAT_ID);
            statement.setInt(1, chatId);

            rs = statement.executeQuery();
            while (rs.next()) {
                messages.add(extractMessage(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeStatement(statement);
        }

        return messages.toArray(new Message[0]);
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
}
