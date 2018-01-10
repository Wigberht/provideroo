package com.dimbo.dao.models.chat;

import com.dimbo.dao.DAOException;
import com.dimbo.dao.models.DAOModel;
import com.dimbo.model.Chat;
import com.dimbo.model.Message;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ChatDAOMySQL extends DAOModel implements ChatDAO {

    private static final String FIND_BY_ID = "SELECT * FROM chat WHERE id = ?";
    private static final String FIND_MESSAGES = "SELECT\n"
        + "  message.id,\n"
        + "  message.message,\n"
        + "  message.user_id,\n"
        + "  message.created_at,\n"
        + "  message.created_at\n"
        + "FROM message\n"
        + "  INNER JOIN message_chat ON message.id = message_chat.message_id\n"
        + "WHERE message_chat.chat_id = ?";
    private static final String CREATE_CHAT = "INSERT INTO chat VALUES(DEFAULT, ?)";
    private static final String UPDATE_CHAT_BY_ID = "UPDATE chat "
        + "SET title = ? "
        + "WHERE id = ?";

    private static final String DELETE_CHAT_BY_ID = "DELETE FROM chat WHERE id = ?";


    Connection connection;

    public ChatDAOMySQL(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Chat find(Long id) throws DAOException {
        Chat chat = null;

        try (
            PreparedStatement statement = prepareStatement(connection, FIND_BY_ID, false, id);
            ResultSet resultSet = statement.executeQuery()
        ) {
            if (resultSet.next()) {
                chat = map(resultSet);
                chat.setMessages(findMessages(id));
            }

        } catch (SQLException e) {
            throw new DAOException(e);
        }

        return chat;
    }

    @Override
    public List<Message> findMessages(long chatId) throws DAOException {
        List<Message> messages = new ArrayList<>();

        try (
            PreparedStatement statement = prepareStatement(connection, FIND_MESSAGES, false,
                chatId);
            ResultSet resultSet = statement.executeQuery()
        ) {
            while (resultSet.next()) {
                messages.add(mapMessage(resultSet));
            }

        } catch (SQLException e) {
            throw new DAOException(e);
        }

        return messages;
    }

    @Override
    public boolean delete(Long id) throws DAOException {
        return false;
    }

    @Override
    public Chat update(Chat chat) throws DAOException {
        return null;
    }

    @Override
    public Chat create(Chat chat) throws DAOException {
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
            resultSet.getBoolean("was_read"),
            resultSet.getString("updated_at"),
            resultSet.getString("created_at"),
            resultSet.getLong("user_id")
        );
    }
}
