package com.d_cherkashyn.epam.listener;

import com.d_cherkashyn.epam.helper.SessionNumberHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Session listener. Watches over event on session (creted/invalidated)
 */
public class SessionListener implements HttpSessionListener {
    Logger LOGGER = LoggerFactory.getLogger(SessionListener.class);
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        SessionNumberHolder.getInstance().addSession();
        String baseLocale = httpSessionEvent.getSession()
                                            .getServletContext()
                                            .getInitParameter("default_locale");
        if (baseLocale == null || baseLocale.equals("")) {
            baseLocale = "ru_RU";
        }
        
        httpSessionEvent.getSession().setAttribute("locale", baseLocale);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        SessionNumberHolder.getInstance().removeSession();
        LOGGER.info("Session invalidated");
    }
}
