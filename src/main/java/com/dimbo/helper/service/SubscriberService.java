package com.dimbo.helper.service;

import com.dimbo.dao.factory.FactoryGenerator;
import com.dimbo.model.Subscriber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class SubscriberService extends ServiceHelper {
    Logger LOGGER = LoggerFactory.getLogger(SubscriberService.class);
    
    private int limit;
    private int page;
    
    public SubscriberService() {
        super();
    }
    
    public SubscriberService(int page, int limit) {
        this();
        this.limit = limit;
        this.page = page;
    }
    
    public List<Subscriber> getSubscribers() {
        int page = (this.page == 0) ? 0 : this.page - 1;
        int offset = page * limit;
        
        return FactoryGenerator.getFactory()
                               .makeSubscriberDAO(connection)
                               .all(limit, offset);
    }
}
