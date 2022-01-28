package com.spectre.app.entity;

import com.spectre.app.enums.AccountType;

import java.math.BigDecimal;
import java.util.Date;

public class AccountBuilder {
    private String accountNumber;
    private String userId;
    private AccountType accountType;
    private String currency;
    private BigDecimal balance;
    private Date lastModified;
    private User lastModifiedBy;
    private Date creationDate;

    public AccountBuilder withAccountNumber(String accountNumber){
        this.accountNumber = accountNumber;
        return this;
    }
    public AccountBuilder withUserId(String userId){
        this.userId = userId;
        return this;
    }
    public AccountBuilder withAccountType(AccountType accountType){
        this.accountType = accountType;
        return this;
    }
    public AccountBuilder withAccountCurrency(String currency){
        this.currency = currency;
        return this;
    }
    public AccountBuilder withAccountBalance(BigDecimal balance){
        this.balance = balance;
        return this;
    }
    public AccountBuilder withLastModified(Date lastModified){
        this.lastModified = lastModified;
        return this;
    }
    public AccountBuilder withLastModifiedBy(User lastModifiedBy){
        this.lastModifiedBy = lastModifiedBy;
        return this;
    }
    public AccountBuilder withCreationDate(Date creationDate){
        this.creationDate = creationDate;
        return this;
    }
    public BankAccount build(){
        return new BankAccount(accountNumber, userId, accountType, currency, balance, lastModified, lastModifiedBy, creationDate);
    }
}
