package com.foodbooking.service;

import com.foodbooking.entity.UserOrder;

import java.time.LocalDate;
import java.util.List;

public interface UserOrderService {
    void saveOrder(String userId, String paymentId, Long productId, Integer totalPrice, LocalDate orderDate, String address);

    List<UserOrder> getUserHistory(String userId);
}
