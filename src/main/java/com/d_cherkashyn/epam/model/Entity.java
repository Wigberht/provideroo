package com.d_cherkashyn.epam.model;

import java.io.Serializable;

/**
 * Entity class. Provides general methods and fields that are ready to me extended by
 * all of the Entities in system
 */
public class Entity implements Serializable {
    
    protected long id;
    
    protected static final long serialVersionUID = 1L;
    
    public Entity() { }
    
    public Entity(long id) {
        this.id = id;
    }
    
    public long getId() {
        return id;
    }
    
    public void setId(long id) {
        this.id = id;
    }
}
