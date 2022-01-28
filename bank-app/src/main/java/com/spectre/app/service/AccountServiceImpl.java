package com.spectre.app.service;

import com.spectre.app.entity.BankAccount;
import com.spectre.app.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    AccountRepository accountRepository;

    @Override
    public boolean createAccount(BankAccount bankAccount) {
        accountRepository.save(bankAccount);
        return true;
    }

    @Override
    public BankAccount getAccount(String accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber);
    }

    @Override
    public void updateAccount(BankAccount bankAccount) {
        accountRepository.save(bankAccount);
    }
}
