package com.dimbo.managers;

import java.util.ResourceBundle;

public final class DBResourceManager implements ResourceManager {
    private static DBResourceManager instance = null;
    private static ResourceBundle resourceBundle;
    
    private DBResourceManager() { }
    
    public static synchronized DBResourceManager getInstance() {
        if (instance == null) {
            synchronized (DBResourceManager.class) {
                if (instance == null) {
                    instance = new DBResourceManager();
                    resourceBundle = ResourceBundle.getBundle(ResourceTypes.db.name());
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