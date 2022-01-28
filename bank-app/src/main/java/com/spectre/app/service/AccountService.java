package com.spectre.app.service;


import com.spectre.app.entity.BankAccount;

public interface AccountService {
    boolean createAccount(BankAccount bankAccount);

    BankAccount getAccount(String accountNumber);

    void updateAccount(BankAccount bankAccount);
}
