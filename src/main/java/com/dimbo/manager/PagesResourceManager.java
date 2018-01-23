package com.dimbo.manager;

import java.util.ResourceBundle;

public final class PagesResourceManager implements ResourceManager {

    private static PagesResourceManager instance = null;
    private static ResourceBundle resourceBundle;

    private PagesResourceManager() {
    }

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

    /**
     * Shortcut for PagesResourceManager.getInstance().getParameter(key);
     *
     * @return String jsp path
     */
    public static String getPage(String pageKey) {
        return PagesResourceManager.getInstance().getParameter(pageKey);
    }
}