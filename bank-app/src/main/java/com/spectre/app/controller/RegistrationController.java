package com.spectre.app.controller;

import com.spectre.app.enums.AccountType;
import com.spectre.app.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;

@RestController
public class RegistrationController {

    @Autowired
    RegistrationService registrationService;

    @PostMapping("/registerUser")
    public String registerUser(@RequestParam String firstName, @RequestParam String lastName,
                               @RequestParam String gender, @RequestParam short age, @RequestParam String phoneNumber, @RequestParam String email){

        try {
            String userId = generateUserId(firstName, lastName);
            boolean success = registrationService.registerUser(userId, firstName, lastName, gender, age, phoneNumber, email);
            return success ? "Registration successful! Your new user ID is " + userId : "Could not register";
        } catch (Exception e){
            return e.getMessage();
        }

    }

    @PostMapping("/createAccount")
    public String registerAccount(@RequestParam String userId, @RequestParam AccountType accountType, @RequestParam String currency,
                                  @RequestParam BigDecimal openingBalance){

        String accountNumber = generateAccountNumber(accountType);
        boolean success = registrationService.createAccount(accountNumber, userId, accountType, currency, openingBalance);
        return success ? "Registration successful! Your new account number is " + accountNumber : "Could not register! Please contact support.";
    }

    private String generateUserId(String firstName, String lastName) {
        StringBuilder numbers = new StringBuilder();
        ArrayList<Integer> list = new ArrayList<>();
        for (int i=10; i<=99; i++) {
            list.add(i);
        }
        Collections.shuffle(list);
        for (int i=0; i<2; i++) {
            numbers.append(list.get(i));
        }

        //TODO check db for uniqueness
        return firstName + lastName.substring(0,1) + numbers;
    }

    private String generateAccountNumber(Enum accountType) {
        StringBuilder numbers = new StringBuilder();
        ArrayList<Integer> list = new ArrayList<>();
        for (int i=10; i<=99; i++) {
            list.add(i);
        }
        Collections.shuffle(list);
        for (int i=0; i<5; i++) {
            numbers.append(list.get(i));
        }

        return numbers.toString();
    }
}
