package com.foodbooking.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@FeignClient(name = "http://FUND-TRANSFER-SERVICE/fund-transfer/")
//@FeignClient(value = "fund-transfer-service", url = "http://localhost:8080/fund-transfer/")

public interface PaymentClient {

	@PostMapping("/create")
	String createPayment(@RequestParam String fromAccount, @RequestParam String toAccount, @RequestParam BigDecimal amount,
								@RequestParam String currency, @RequestParam String details);

	@PostMapping("/sign")
	String signPayment(@RequestParam Long orderId);

}
