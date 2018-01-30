package com.d_cherkashyn.epam.model;

import java.io.Serializable;

/**
 * Entity class. Provides general methods and fields that are ready to me extended by
 * all of the Entities in system
 */
public class Entity implements Serializable {
    
    protected static final long serialVersionUID = 1L;
    protected long id;
    
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
