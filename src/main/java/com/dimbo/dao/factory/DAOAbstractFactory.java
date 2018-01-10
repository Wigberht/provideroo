package com.dimbo.dao.factory;

import com.dimbo.dao.models.account.AccountDAO;
import com.dimbo.dao.models.chat.ChatDAO;
import com.dimbo.dao.models.message.MessageDAO;
import com.dimbo.dao.models.service.ServiceDAO;
import com.dimbo.dao.models.subscriber.SubscriberDAO;
import com.dimbo.dao.models.tariff.TariffDAO;
import com.dimbo.dao.models.user.UserDAO;

import com.dimbo.model.Service;
import java.sql.Connection;

public interface DAOAbstractFactory {

    MessageDAO makeMessageDAO(Connection connection);

    UserDAO makeUserDAO(Connection connection);

    AccountDAO makeAccountDAO(Connection connection);

    SubscriberDAO makeSubscriberDAO(Connection connection);

    ChatDAO makeChatDAO(Connection connection);

    TariffDAO makeTariffDAO(Connection connection);

    ServiceDAO makeServiceDAO(Connection connection);

}
