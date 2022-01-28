package com.spectre.app.service;

import com.spectre.app.entity.BankOrder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface PaymentsService {
    Long createPayment(String fromAccount, String toAccount, BigDecimal amount, String currency, String details);

    String signOrder(Long orderId);

    List<BankOrder> getOrders(String accountNumber, LocalDate dateFrom, LocalDate dateTo);
}
