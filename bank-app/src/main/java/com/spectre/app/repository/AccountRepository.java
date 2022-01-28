package com.spectre.app.repository;

import com.spectre.app.entity.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<BankAccount, Long> {

    BankAccount findByAccountNumber(String accountNumber);
}
