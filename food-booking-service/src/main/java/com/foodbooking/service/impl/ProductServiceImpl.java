package com.foodbooking.service.impl;

import com.foodbooking.entity.FoodItem;
import com.foodbooking.service.ProductService;
import com.foodbooking.service.UserOrderService;
import com.foodbooking.dto.ResponseBodyDto;
import com.foodbooking.feign.PaymentClient;
import com.foodbooking.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    PaymentClient paymentClient;

    @Autowired
    UserOrderService userOrderService;

    @Override
    public List<FoodItem> searchProducts(String searchInput) {
        return productRepository.findByNameContains(searchInput);
    }

    @Override
    public ResponseEntity buyProduct(Long productId, Integer quantity, Long accountNumber) {
        FoodItem foodItem = productRepository.getById(productId);

        long ecommerceAccount = 1234567890L;
        String paymentIdResponse = paymentClient.createPayment(accountNumber.toString(), Long.toString(ecommerceAccount), new BigDecimal(foodItem.getPrice()), "USD", "buy ecommerce foodItem");
        if (!paymentIdResponse.contains("Your payment id")) return new ResponseEntity<>(new ResponseBodyDto(paymentIdResponse), HttpStatus.BAD_REQUEST);
        paymentIdResponse = paymentIdResponse.split("Your payment id : ")[1];
        paymentClient.signPayment(Long.valueOf(paymentIdResponse));
        productRepository.save(foodItem);

        String userId = "Alex";
        String address = "TestAddress";
        Integer totalPrice = quantity * foodItem.getPrice();
        LocalDate orderDate = LocalDate.now();
        userOrderService.saveOrder(userId, paymentIdResponse, productId, totalPrice, orderDate,  address);

        return new ResponseEntity<>(new ResponseBodyDto("success"), HttpStatus.OK);
    }
}
