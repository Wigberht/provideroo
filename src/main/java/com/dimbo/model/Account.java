package com.dimbo.model;

public class Account extends Entity {

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

    public Account(long id, double balance, String currencyShortname, String currencySymbol) {
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
}
