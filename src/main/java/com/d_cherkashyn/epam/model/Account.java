package com.d_cherkashyn.epam.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Account extends Entity {
    Logger LOGGER = LoggerFactory.getLogger(Account.class);
    
    private double balance;
    private String currencyShortname;
    private String currencySymbol;
    
    public Account() {
    }
    
    public Account(double balance, String currencyShortname, String currencySymbol) {
        this.balance = balance;
        this.currencyShortname = currencyShortname;
        this.currencySymbol = currencySymbol;
    }
    
    public Account(long id, double balance, String currencyShortname,
                   String currencySymbol) {
        super(id);
        this.balance = balance;
        this.currencyShortname = currencyShortname;
        this.currencySymbol = currencySymbol;
    }
    
    public double getBalance() {
        return balance;
    }
    
    public void setBalance(double balance) {
        this.balance = balance;
    }
    
    public boolean withdraw(double amount) {
        double result = balance - amount;
        
        if (result >= 0) {
            balance = result;
            return true;
        } else {
            return false;
        }
    }
    
    public String getCurrencyShortname() {
        return currencyShortname;
    }
    
    public void setCurrencyShortname(String currencyShortname) {
        this.currencyShortname = currencyShortname;
    }
    
    public String getCurrencySymbol() {
        return currencySymbol;
    }
    
    public void setCurrencySymbol(String currencySymbol) {
        this.currencySymbol = currencySymbol;
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Account{");
        sb.append("balance=").append(balance);
        sb.append(", currencyShortname='").append(currencyShortname).append('\'');
        sb.append(", currencySymbol='").append(currencySymbol).append('\'');
        sb.append(", id=").append(id);
        sb.append('}');
        return sb.toString();
    }
}
