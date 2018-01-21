package com.dimbo.listeners;

import com.dimbo.helper.SessionNumberHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionListener implements HttpSessionListener {
    Logger LOGGER = LoggerFactory.getLogger(SessionListener.class);
    
    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        SessionNumberHolder.getInstance().addSession();
        LOGGER.info("Session created");
        httpSessionEvent.getSession().setAttribute("locale", "ru_RU");
        LOGGER.info("Session language set");
        
    }
    
    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        SessionNumberHolder.getInstance().removeSession();
        LOGGER.info("Session invalidated");
    }
}
