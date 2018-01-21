package com.dimbo.helper;

public class SessionNumberHolder {
    
    private static long sessions = 0;
    
    private static SessionNumberHolder ourInstance = new SessionNumberHolder();
    
    public static SessionNumberHolder getInstance() {
        return ourInstance;
    }
    
    private SessionNumberHolder() {
    }
    
    public void addSession() {
        sessions++;
    }
    
    public long getSessions() {
        return sessions;
    }
    
    public void removeSession() {
        sessions--;
    }
}
