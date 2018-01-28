package com.d_cherkashyn.epam.model;

import java.util.List;

public class Service extends Entity {
    
    private String title;
    private List<Tariff> tariffs;
    
    public Service() {
    }
    
    public Service(long id, String title) {
        super(id);
        this.title = title;
    }
    
    public Service(long id, String title, List<Tariff> tariffs) {
        super(id);
        this.title = title;
        this.tariffs = tariffs;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    
    public List<Tariff> getTariffs() {
        return tariffs;
    }
    
    public void setTariffs(List<Tariff> tariffs) {
        this.tariffs = tariffs;
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Service{");
        sb.append("title='").append(title).append('\'');
        sb.append(", tariffs=").append(tariffs);
        sb.append(", id=").append(id);
        sb.append('}');
        return sb.toString();
    }
}
