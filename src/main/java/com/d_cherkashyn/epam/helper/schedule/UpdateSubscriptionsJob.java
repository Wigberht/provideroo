package com.d_cherkashyn.epam.helper.schedule;

import com.d_cherkashyn.epam.helper.service.SubscriberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Runnable class that used to recheck subscription statuses (like once a day)
 */
public class UpdateSubscriptionsJob implements Runnable {
    Logger LOGGER = LoggerFactory.getLogger(UpdateSubscriptionsJob.class);
    
    @Override
    public void run() {
        LOGGER.info("Running the job");
        
        double collectedFees = SubscriberService.collectSubscriptionFees();
        
        LOGGER.info("Fees collected! Amount: {}", collectedFees);
    }
}
