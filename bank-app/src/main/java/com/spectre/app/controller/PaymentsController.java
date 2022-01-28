package com.spectre.app.controller;

import com.spectre.app.exception.PaymentsRuntimeException;
import com.spectre.app.service.PaymentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController("/payment")
public class PaymentsController {

    @Autowired
    PaymentsService paymentsService;

    @PostMapping("/create")
    public String createPayment(@RequestParam String fromAccount, @RequestParam String toAccount, @RequestParam BigDecimal amount,
                                @RequestParam String currency, @RequestParam String details){

        try {
            Long orderId = paymentsService.createPayment(fromAccount, toAccount, amount, currency, details);
            return orderId != null ? "Your payment id : " + orderId : "Could not create payment. Please contact support.";
        } catch (PaymentsRuntimeException e){
            return e.getMessage();
        }

    }

    @PostMapping("/sign")
    public String signPayment(@RequestParam Long orderId){
        return paymentsService.signOrder(orderId);
    }
}
