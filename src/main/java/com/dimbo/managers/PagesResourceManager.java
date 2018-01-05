package com.dimbo.managers;

import java.util.ResourceBundle;

public final class PagesResourceManager implements ResourceManager {
    private static PagesResourceManager instance = null;
    private static ResourceBundle resourceBundle;
    
    private PagesResourceManager() { }
    
    public static PagesResourceManager getInstance() {
        if (instance == null) {
            synchronized (PagesResourceManager.class) {
                if (instance == null) {
                    instance = new PagesResourceManager();
                    resourceBundle = ResourceBundle.getBundle(ResourceTypes.pages.name());
                }
            }
        }
        
        return instance;
    }
    
    /**
     * @see ResourceManager#getParameter(String)
     */
    public String getParameter(String key) {
        return (String) resourceBundle.getObject(key);
    }
    
    /**
     * @see ResourceManager#parameterExists(String)
     */
    public boolean parameterExists(String key) {
        return resourceBundle.containsKey(key);
    }
}