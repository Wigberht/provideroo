package com.dimbo.model;

public class Subscription extends Entity {
    
    private String start;
    private String end;
    private boolean prolong;
    private long tariffId;
    private long subscriberId;
    
    public Subscription() {
    }
    
    public Subscription(long id, String start, String end,
                        boolean prolong, long tariffId, long subscriberId) {
        super(id);
        this.start = start;
        this.end = end;
        this.prolong = prolong;
        this.tariffId = tariffId;
        this.subscriberId = subscriberId;
    }
    
    public String getStart() {
        return start;
    }
    
    public void setStart(String start) {
        this.start = start;
    }
    
    public String getEnd() {
        return end;
    }
    
    public void setEnd(String end) {
        this.end = end;
    }
    
    public boolean isProlong() {
        return prolong;
    }
    
    public void setProlong(boolean prolong) {
        this.prolong = prolong;
    }
    
    public long getTariffId() {
        return tariffId;
    }
    
    public void setTariffId(long tariffId) {
        this.tariffId = tariffId;
    }
    
    public long getSubscriberId() {
        return subscriberId;
    }
    
    public void setSubscriberId(long subscriberId) {
        this.subscriberId = subscriberId;
    }
}
