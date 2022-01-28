package com.foodbooking.service.impl;

import com.foodbooking.repository.UserOrderRepository;
import com.foodbooking.service.UserOrderService;
import com.foodbooking.entity.UserOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class UserOrderServiceImpl implements UserOrderService {

    @Autowired
    UserOrderRepository userOrderRepository;

    @Override
    public void saveOrder(String userId, String paymentId, Long productId, Integer totalPrice, LocalDate orderDate, String address) {
        UserOrder userOrder = new UserOrder();
        userOrder.setUserId(userId);
        userOrder.setPaymentId(paymentId);
        userOrder.setProductId(productId);
        userOrder.setOrderDate(orderDate);
        userOrder.setTotalPrice(totalPrice);
        userOrder.setAddress(address);

        userOrderRepository.save(userOrder);

    }

    @Override
    public List<UserOrder> getUserHistory(String userId) {
        return userOrderRepository.getByUserId(userId);
    }
}
