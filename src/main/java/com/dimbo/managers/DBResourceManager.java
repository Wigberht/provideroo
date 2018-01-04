package com.dimbo.managers;

import java.util.ResourceBundle;
import java.util.concurrent.locks.ReentrantLock;

/**
 * The Class DBResourceManager/
 */
public final class DBResourceManager {
    /**
     * Singleton instance.
     */
    private static DBResourceManager instance = null;

    /**
     * The lock.
     */
    private static ReentrantLock lock = new ReentrantLock();

    /**
     * The resource bundle.
     */
    private static ResourceBundle resourceBundle;

    /**
     * Instantiates a new resource manager.
     */
    private DBResourceManager() {

    }

    /**
     * Gets the single instance of DBResourceManager for database.
     *
     * @return single instance of DBResourceManager
     */
    public static DBResourceManager getInstance() {
        if (instance == null) {
            try {
                lock.lock();
                if (instance == null) {
                    instance = new DBResourceManager();
                    resourceBundle = ResourceBundle.getBundle(ResourceTypes.db.name());
                }
            } finally {
                lock.unlock();
            }

        }
        return instance;
    }

    /**
     * Gets the properties parameter.
     *
     * @param key the key
     * @return the parameter
     */
    public String getParameter(String key) {
        return (String) resourceBundle.getObject(key);
    }

}

