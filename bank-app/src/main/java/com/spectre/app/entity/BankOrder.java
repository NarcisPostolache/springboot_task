package com.spectre.app.entity;

import com.spectre.app.enums.Status;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class BankOrder {

    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    private BankAccount fromBankAccount;
    @ManyToOne
    private BankAccount toBankAccount;
    private BigDecimal amount;
    private String currency;
    private String details;
    private Status status;
    private LocalDate lastModifiedDate;

    public BankOrder(){}

    public BankOrder(BankAccount fromBankAccount, BankAccount toBankAccount, BigDecimal amount, String currency, String details, Status status, LocalDate lastModifiedDate) {
        this.fromBankAccount = fromBankAccount;
        this.toBankAccount = toBankAccount;
        this.amount = amount;
        this.currency = currency;
        this.details = details;
        this.status = status;
        this.lastModifiedDate = lastModifiedDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BankAccount getFromBankAccount() {
        return fromBankAccount;
    }

    public void setFromBankAccount(BankAccount fromBankAccount) {
        this.fromBankAccount = fromBankAccount;
    }

    public BankAccount getToBankAccount() {
        return toBankAccount;
    }

    public void setToBankAccount(BankAccount toBankAccount) {
        this.toBankAccount = toBankAccount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDate getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(LocalDate lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }
}
