package com.dimbo.helper.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UpdateSubscriptionsJob implements Runnable {
    Logger LOGGER = LoggerFactory.getLogger(UpdateSubscriptionsJob.class);
    
    @Override
    public void run() {
        LOGGER.info("Running the job");
    }
}
