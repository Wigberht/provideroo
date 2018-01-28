package com.d_cherkashyn.epam.manager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

public final class MessagesResourceManager implements ResourceManager {
    
    private static Logger LOGGER = LoggerFactory.getLogger(MessagesResourceManager.class);
    
    private static MessagesResourceManager instance = null;
    private static ResourceBundle resourceBundle;
    private static Map<String, ResourceBundle> bundles = new HashMap<>();
    
    private MessagesResourceManager() { }
    
    public static synchronized ResourceBundle getBundle(String locale) {
        LOGGER.info("Locale requested in bundle: {}", locale);
        
        if (bundles.containsKey(locale)) {
            return bundles.get(locale);
        } else {
            bundles.put(locale, ResourceBundle.getBundle(ResourceTypes.messages.name(),
                                                         Locale.forLanguageTag(locale)));
            return bundles.get(locale);
        }
    }
    
    public static synchronized MessagesResourceManager getInstance(String locale) {
        
        if (instance == null) {
            synchronized (MessagesResourceManager.class) {
                if (instance == null) {
                    instance = new MessagesResourceManager();
                    resourceBundle = ResourceBundle
                        .getBundle(ResourceTypes.messages.name());
                }
            }
        }
        
        return instance;
    }
    
    /**
     * {@inheritDoc}
     */
    public String getParameter(String key) {
        return (String) resourceBundle.getObject(key);
    }
    
    /**
     * {@inheritDoc}
     */
    public boolean parameterExists(String key) {
        return resourceBundle.containsKey(key);
    }
}