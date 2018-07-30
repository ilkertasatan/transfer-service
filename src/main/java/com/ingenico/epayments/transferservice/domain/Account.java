package com.ingenico.epayments.transferservice.domain;

import io.swagger.annotations.ApiModelProperty;

public class Account implements Identifiable {

    @ApiModelProperty(hidden = true)
    private Long id;
    private String name;
    private double balance;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public synchronized void withDraw(double amount) throws Exception {
        if (amount < 0) {
            throw new IllegalArgumentException("amount: " + amount);
        }
        if (balance < amount) {
            throw new Exception();
        }
        balance -= amount;
    }

    public synchronized void deposit(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("amount: " + amount);
        }
        balance += amount;
    }
}
