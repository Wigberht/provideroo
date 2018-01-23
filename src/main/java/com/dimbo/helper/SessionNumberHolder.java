package com.dimbo.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SessionNumberHolder {
    Logger LOGGER = LoggerFactory.getLogger(SessionNumberHolder.class);
    
    private static long sessions = 0;
    
    private static SessionNumberHolder ourInstance = new SessionNumberHolder();
    
    public static SessionNumberHolder getInstance() {
        return ourInstance;
    }
    
    private SessionNumberHolder() {
    }
    
    public void setSessions(int amount) {
        sessions = amount;
    }
    
    public void addSession() {
        sessions++;
        LOGGER.info("Session amount now (add):" + sessions);
    }
    
    public long getSessions() {
        LOGGER.info("Session amount now :" + sessions);
        return sessions;
    }
    
    public void removeSession() {
        sessions--;
        LOGGER.info("Session amount now (remove):" + sessions);
    }
}
