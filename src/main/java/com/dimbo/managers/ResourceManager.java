package com.dimbo.managers;

public interface ResourceManager {
    
    /**
     * Get property by key from resource bundle.
     *
     * @param key in resource bundle
     * @return String value of property
     */
    String getParameter(String key);
    
    /**
     * @param key in resource bundle
     * @return true if parameter exists
     */
    boolean parameterExists(String key);
}
