package com.dimbo.helpers;

import com.dimbo.ConnectionPool;
import com.dimbo.dao.factory.FactoryGenerator;
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
    
    public SubscriberService(int page, int limit) {
        this.limit = limit;
        this.page = page;
        connection = ConnectionPool.conn();
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
