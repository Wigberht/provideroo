package com.dimbo.helper;

import com.dimbo.ConnectionPool;
import com.dimbo.dao.factory.FactoryGenerator;
import com.dimbo.dao.models.service.ServiceDAO;
import com.dimbo.model.Service;
import com.dimbo.model.Subscriber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.util.List;

public class SubscriberService {
    Logger LOGGER = LoggerFactory.getLogger(SubscriberService.class);
    
    private int limit;
    private int page;
    
    Connection connection;
    
    public SubscriberService() {
        connection = ConnectionPool.conn();
    }
    
    public SubscriberService(int page, int limit) {
        this();
        this.limit = limit;
        this.page = page;
    }
    
    public void returnConnection() {
        ConnectionPool.returnConn(connection);
    }
    
    public List<Subscriber> getSubscribers() {
        int page = (this.page == 0) ? 0 : this.page - 1;
        int offset = page * limit;
        
        return FactoryGenerator.getFactory()
                               .makeSubscriberDAO(connection)
                               .all(limit, offset);
    }
}
