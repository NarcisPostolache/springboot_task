package com.foodbooking.repository;

import com.foodbooking.entity.FoodItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<FoodItem, Long> {
    List<FoodItem> findByNameContains(String searchInput);
}
