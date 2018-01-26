package com.d_cherkashyn.epam.model;

public class Role extends Entity {
    private String name;
    
    public Role(long id, String name) {
        super(id);
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
}
