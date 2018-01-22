package com.dimbo.helper.schedule;

import com.dimbo.helper.service.SubscriberService;
import com.dimbo.model.Subscriber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class UpdateSubscriptionsJob implements Runnable {
    Logger LOGGER = LoggerFactory.getLogger(UpdateSubscriptionsJob.class);
    
    @Override
    public void run() {
        LOGGER.info("Running the job");
        SubscriberService subscriberService = new SubscriberService();
        
        subscriberService.collectSubscriptionFees();
        subscriberService.returnConnection();
        
    }
}
