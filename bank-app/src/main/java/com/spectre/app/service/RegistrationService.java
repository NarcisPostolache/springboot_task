package com.spectre.app.service;

import com.spectre.app.enums.AccountType;

import java.math.BigDecimal;

public interface RegistrationService {

    boolean registerUser(String userId, String firstName, String lastName, String gender, short age, String phoneNumber, String email);

    boolean createAccount(String accountNumber, String userId, AccountType accountType, String currency, BigDecimal openingBalance);
}
