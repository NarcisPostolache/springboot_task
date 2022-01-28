package com.spectre.app.service;

import com.spectre.app.entity.BankAccount;
import com.spectre.app.entity.AccountBuilder;
import com.spectre.app.entity.User;
import com.spectre.app.entity.UserBuilder;
import com.spectre.app.enums.AccountType;
import com.spectre.app.enums.Status;
import com.spectre.app.exception.RegistrationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Date;
import java.util.regex.Pattern;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    @Autowired
    UserService userService;

    @Autowired
    AccountService accountService;

    @Override
    public boolean registerUser(String userId, String firstName, String lastName, String gender, short age, String phoneNumber, String email) throws RegistrationException {
        boolean canRegister = false;

        if(hasValidAge(age) && hasValidEmail(email) && hasUniqueUserId(userId) && hasValidPhoneNumber(phoneNumber)){
            canRegister = true;
        }

        User newUser = new UserBuilder()
                .withUserId(userId)
                .withFirstName(firstName)
                .withLastName(lastName)
                .withGender(gender)
                .withAge(age)
                .withEmail(email)
                .withPhoneNumber(phoneNumber)
                .withRegistrationDate(Date.from(Instant.now()))
                .withStatus(Status.NEW)
                .build();

        if (canRegister) return userService.registerUser(newUser);
        return false;
    }

    private boolean hasValidPhoneNumber(String phoneNumber) {
        boolean success = Pattern.compile("\\d{10}$")
                .matcher(phoneNumber)
                .matches();

        if (!success) throw new RegistrationException("Not a valid phone number");
        return true;
    }

    @Override
    public boolean createAccount(String accountNumber, String userId, AccountType accountType, String currency, BigDecimal openingBalance) {
        BankAccount bankAccount = new AccountBuilder()
                .withAccountNumber(accountNumber)
                .withUserId(userId)
                .withAccountType(accountType)
                .withAccountCurrency(currency)
                .withAccountBalance(openingBalance)
                .withCreationDate(Date.from(Instant.now()))
                .withLastModified(Date.from(Instant.now()))
                .withLastModifiedBy(userService.getUser(userId))
                .build();
        return accountService.createAccount(bankAccount);
    }

    private boolean hasUniqueUserId(String userId) {
        return true;
    }

    private boolean hasValidEmail(String email) {
        boolean success = Pattern.compile("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$")
                .matcher(email)
                .matches();
        if (!success) throw new RegistrationException("Not a valid email");
        return true;
    }

    private boolean hasValidAge(Short age) {
        boolean success = age>18 && age<60;
        if (!success) throw new RegistrationException("Not a valid age");
        return true;
    }
}
