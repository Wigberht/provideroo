package com.dimbo.managers;

import sun.net.ResourceManager;

public enum ResourceManagerFactory {
    INSTANCE;

    public DBResourceManager dbManager() {
        return DBResourceManager.getInstance();
    }

    public PagesResourceManager pagesManager() {
        return PagesResourceManager.getInstance();
    }
}
