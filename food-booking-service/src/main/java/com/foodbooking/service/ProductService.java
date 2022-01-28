package com.foodbooking.service;

import com.foodbooking.entity.FoodItem;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductService {
    List<FoodItem> searchProducts(String searchInput);

    ResponseEntity buyProduct(Long productId, Integer quantity, Long accountNumber);
}
