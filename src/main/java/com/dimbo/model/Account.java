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
}
