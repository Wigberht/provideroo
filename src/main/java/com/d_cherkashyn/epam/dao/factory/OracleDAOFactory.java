package com.d_cherkashyn.epam.dao.factory;

import com.d_cherkashyn.epam.dao.models.account.AccountDAO;
import com.d_cherkashyn.epam.dao.models.chat.ChatDAO;
import com.d_cherkashyn.epam.dao.models.message.MessageDAO;
import com.d_cherkashyn.epam.dao.models.role.RoleDAO;
import com.d_cherkashyn.epam.dao.models.service.ServiceDAO;
import com.d_cherkashyn.epam.dao.models.subscriber.SubscriberDAO;
import com.d_cherkashyn.epam.dao.models.subscription.SubscriptionDAO;
import com.d_cherkashyn.epam.dao.models.tariff.TariffDAO;
import com.d_cherkashyn.epam.dao.models.user.UserDAO;
import java.sql.Connection;

public class OracleDAOFactory implements DAOAbstractFactory {
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
    
    @Override
    public RoleDAO makeRoleDAO(Connection connection) {
        return null;
    }
    
    @Override
    public SubscriptionDAO makeSubscriptionDAO(Connection connection) {
        return null;
    }
}
