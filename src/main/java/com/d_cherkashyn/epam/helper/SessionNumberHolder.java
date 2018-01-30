package com.d_cherkashyn.epam.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SessionNumberHolder {
    private static long sessions = 0;
    private static SessionNumberHolder ourInstance = new SessionNumberHolder();
    Logger LOGGER = LoggerFactory.getLogger(SessionNumberHolder.class);
    
    private SessionNumberHolder() {
    }
    
    public static SessionNumberHolder getInstance() {
        return ourInstance;
    }
    
    public void addSession() {
        sessions++;
        LOGGER.info("Session amount now (add):" + sessions);
    }
    
    public long getSessions() {
        LOGGER.info("Session amount now :" + sessions);
        return sessions;
    }
    
    public void setSessions(int amount) {
        sessions = amount;
    }
    
    public void removeSession() {
        sessions--;
        LOGGER.info("Session amount now (remove):" + sessions);
    }
}
