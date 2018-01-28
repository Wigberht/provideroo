package com.d_cherkashyn.epam.model;

public class Tariff extends Entity {
    
    private String title;
    private String description;
    private int numberOfDays;
    private double cost;
    private String currencyShortname;
    private long serviceId;
    private int subscriberAmount;
    
    private String serviceTitle;
    
    public Tariff() {
    }
    
    public Tariff(long id, String title, String description, int numberOfDays,
                  double cost,
                  String currencyShortname, long serviceId) {
        super(id);
        this.title = title;
        this.description = description;
        this.numberOfDays = numberOfDays;
        this.cost = cost;
        this.currencyShortname = currencyShortname;
        this.serviceId = serviceId;
    }
    
    public Tariff(long id, String title, String description,
                  int numberOfDays, double cost,
                  String currencyShortname, long serviceId, int subscriberAmount) {
        super(id);
        this.title = title;
        this.description = description;
        this.numberOfDays = numberOfDays;
        this.cost = cost;
        this.currencyShortname = currencyShortname;
        this.serviceId = serviceId;
        this.subscriberAmount = subscriberAmount;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public int getNumberOfDays() {
        return numberOfDays;
    }
    
    public void setNumberOfDays(int numberOfDays) {
        this.numberOfDays = numberOfDays;
    }
    
    public double getCost() {
        return cost;
    }
    
    public void setCost(double cost) {
        this.cost = cost;
    }
    
    public String getCurrencyShortname() {
        return currencyShortname;
    }
    
    public void setCurrencyShortname(String currencyShortname) {
        this.currencyShortname = currencyShortname;
    }
    
    public long getServiceId() {
        return serviceId;
    }
    
    public void setServiceId(long serviceId) {
        this.serviceId = serviceId;
    }
    
    public int getSubscriberAmount() {
        return subscriberAmount;
    }
    
    public void setSubscriberAmount(int subscriberAmount) {
        this.subscriberAmount = subscriberAmount;
    }
    
    public String getServiceTitle() {
        return serviceTitle;
    }
    
    public void setServiceTitle(String serviceTitle) {
        this.serviceTitle = serviceTitle;
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Tariff{");
        sb.append("title='").append(title).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", numberOfDays=").append(numberOfDays);
        sb.append(", cost=").append(cost);
        sb.append(", currencyShortname='").append(currencyShortname).append('\'');
        sb.append(", serviceId=").append(serviceId);
        sb.append(", subscriberAmount=").append(subscriberAmount);
        sb.append(", serviceTitle='").append(serviceTitle).append('\'');
        sb.append(", id=").append(id);
        sb.append('}');
        return sb.toString();
    }
}
