package com.foodbooking.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Vendor {

    @Id
    private Long id;
    private String name;
    private String description;
}
