package com.dimbo.managers;

import java.util.ResourceBundle;
import java.util.concurrent.locks.ReentrantLock;

/**
 * The Class PagesResourceManager/
 */
public final class PagesResourceManager {
    /**
     * Singleton instance.
     */
    private static PagesResourceManager instance = null;

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
    private PagesResourceManager() {

    }

    /**
     * Gets the single instance of PagesResourceManager.
     *
     * @return single instance of PagesResourceManager
     */
    public static PagesResourceManager getInstance() {
        if (instance == null) {
            try {
                lock.lock();
                if (instance == null) {
                    instance = new PagesResourceManager();
                    resourceBundle = ResourceBundle.getBundle(ResourceTypes.pages.name());
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
