package com.dimbo.listener;

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
        String baseLocale = httpSessionEvent.getSession()
                                            .getServletContext()
                                            .getInitParameter("db_type");
        if (baseLocale == null || baseLocale.equals("")) {
            baseLocale = "ru_RU";
        }
        
        httpSessionEvent.getSession().setAttribute("locale", baseLocale);
    }
    
    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        SessionNumberHolder.getInstance().removeSession();
        LOGGER.info("Session invalidated");
    }
}
