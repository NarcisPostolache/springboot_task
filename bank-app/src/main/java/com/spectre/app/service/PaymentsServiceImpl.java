package com.spectre.app.service;

import com.spectre.app.entity.BankAccount;
import com.spectre.app.entity.BankOrder;
import com.spectre.app.enums.Status;
import com.spectre.app.exception.PaymentsRuntimeException;
import com.spectre.app.exception.StatementsException;
import com.spectre.app.repository.PaymentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class PaymentsServiceImpl implements PaymentsService {

    @Autowired
    AccountService accountService;

    @Autowired
    PaymentsRepository paymentsRepository;

    @Override
    public Long createPayment(String fromAccount, String toAccount, BigDecimal amount, String currency, String details) {

        BankAccount fromBankAccountDb = accountService.getAccount(fromAccount);
        BankAccount toBankAccountDb = accountService.getAccount(toAccount);

        if (fromBankAccountDb == null) throw new PaymentsRuntimeException("Could not found account " +fromAccount+" in db");
        if (toBankAccountDb == null) throw new PaymentsRuntimeException("Could not found account " +toAccount+" in db");

        BankOrder newBankOrder = new BankOrder(fromBankAccountDb, toBankAccountDb, amount, currency, details, Status.NEW, LocalDate.now());
        return paymentsRepository.save(newBankOrder).getId();
    }

    @Override
    public String signOrder(Long orderId) {
        BankOrder bankOrder = paymentsRepository.getById(orderId);
        bankOrder.setStatus(Status.SIGNED);
        bankOrder.setLastModifiedDate(LocalDate.now());
        paymentsRepository.save(bankOrder);

        BigDecimal orderAmount = bankOrder.getAmount();
        BankAccount fromBankAccount = accountService.getAccount(bankOrder.getFromBankAccount().getAccountNumber());
        fromBankAccount.setBalance(fromBankAccount.getBalance().subtract(orderAmount));
        accountService.updateAccount(fromBankAccount);

        BankAccount toBankAccount = accountService.getAccount(bankOrder.getToBankAccount().getAccountNumber());
        toBankAccount.setBalance(toBankAccount.getBalance().add(orderAmount));
        accountService.updateAccount(toBankAccount);

        return "Success";
    }

    @Override
    public List<BankOrder> getOrders(String accountNumber, LocalDate dateFrom, LocalDate dateTo) {
        BankAccount acc = accountService.getAccount(accountNumber);
        if(acc == null) throw new StatementsException("Could not account for account number " + accountNumber);
        Long accountId = acc.getId();
        if(accountId == null) throw new StatementsException("Could not retrieve account id for account number " + accountNumber);
        return paymentsRepository.getOrderByIdAndDate(accountId, dateFrom, dateTo);
    }
}
