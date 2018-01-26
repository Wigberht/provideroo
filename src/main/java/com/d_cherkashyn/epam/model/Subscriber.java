package com.d_cherkashyn.epam.model;

import java.util.List;

public class Subscriber extends Entity {

    private String firstName;
    private String lastName;
    private String birthDate;
    private long userId;
    private long accountId;
    private User user;
    private Account account;
    private List<Tariff> tariffs;

    public Subscriber() {
    }

    public Subscriber(String firstName, String lastName, String birthDate, long userId,
        long accountId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.userId = userId;
        this.accountId = accountId;
    }

    public Subscriber(String firstName, String lastName, String birthDate, long userId,
        long accountId,
        User user, Account account) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.userId = userId;
        this.accountId = accountId;
        this.user = user;
        this.account = account;
    }

    public Subscriber(long id, String firstName, String lastName, String birthDate, long userId,
        long accountId) {
        super(id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.userId = userId;
        this.accountId = accountId;
    }

    public Subscriber(long id, String firstName, String lastName, String birthDate, long userId,
        long accountId, User user, Account account) {
        super(id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.userId = userId;
        this.accountId = accountId;
        this.user = user;
        this.account = account;
    }

    public Subscriber(long id, String firstName, String lastName, String birthDate, long userId,
        long accountId, User user, Account account, List<Tariff> tariffs) {
        super(id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.userId = userId;
        this.accountId = accountId;
        this.user = user;
        this.account = account;
        this.tariffs = tariffs;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public List<Tariff> getTariffs() {
        return tariffs;
    }

    public void setTariffs(List<Tariff> tariffs) {
        this.tariffs = tariffs;
    }
}
