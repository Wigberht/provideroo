package com.d_cherkashyn.epam.helper.schedule;

import com.d_cherkashyn.epam.helper.service.SubscriberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UpdateSubscriptionsJob implements Runnable {
    Logger LOGGER = LoggerFactory.getLogger(UpdateSubscriptionsJob.class);
    
    @Override
    public void run() {
        LOGGER.info("Running the job");
        SubscriberService subscriberService = new SubscriberService();
        
        double collectedFees = subscriberService.collectSubscriptionFees();
        LOGGER.info("Fees collected! Amount: {}", collectedFees);
        
        subscriberService.returnConnection();
        
    }
}
