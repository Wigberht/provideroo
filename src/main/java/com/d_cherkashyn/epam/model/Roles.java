package com.d_cherkashyn.epam.model;

public enum Roles {
    ADMIN(1), SUBSCRIBER(2);
    
    long id;
    
    Roles(long id) {
        this.id = id;
    }
    
    public long getId() {
        return id;
    }
}
