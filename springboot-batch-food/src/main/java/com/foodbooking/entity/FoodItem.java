package com.foodbooking.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class FoodItem {

    @Id
    private Long id;
    private String name;
    private Long vendorId;
    private String description;
    private Integer price;
}
