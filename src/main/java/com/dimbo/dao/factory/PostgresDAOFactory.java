package com.dimbo.dao.factory;

import com.dimbo.dao.models.account.AccountDAO;
import com.dimbo.dao.models.chat.ChatDAO;
import com.dimbo.dao.models.message.MessageDAO;
import com.dimbo.dao.models.service.ServiceDAO;
import com.dimbo.dao.models.subscriber.SubscriberDAO;
import com.dimbo.dao.models.tariff.TariffDAO;
import com.dimbo.dao.models.user.UserDAO;
import java.sql.Connection;

public class PostgresDAOFactory implements DAOAbstractFactory {
    @Override
    public MessageDAO makeMessageDAO(Connection connection) {
        return null;
    }

    @Override
    public UserDAO makeUserDAO(Connection connection) {
        return null;
    }

    @Override
    public AccountDAO makeAccountDAO(Connection connection) {
        return null;
    }

    @Override
    public SubscriberDAO makeSubscriberDAO(Connection connection) {
        return null;
    }

    @Override
    public ChatDAO makeChatDAO(Connection connection) {
        return null;
    }

    @Override
    public TariffDAO makeTariffDAO(Connection connection) {
        return null;
    }

    @Override
    public ServiceDAO makeServiceDAO(Connection connection) {
        return null;
    }
}
