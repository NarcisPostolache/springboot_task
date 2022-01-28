package com.spectre.app.controller;

import com.spectre.app.entity.BankOrder;
import com.spectre.app.exception.StatementsException;
import com.spectre.app.service.PaymentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@RestController
public class StatementsController {

    @Autowired
    private PaymentsService paymentsService;

    @GetMapping("/getStatements")
    public List<BankOrder> getStatements(@RequestParam String accountNumber, @RequestParam String dateFrom, @RequestParam String dateTo){
        try{
            return paymentsService.getOrders(accountNumber, LocalDate.parse(dateFrom), LocalDate.parse(dateTo));
        } catch (StatementsException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
    }
}
