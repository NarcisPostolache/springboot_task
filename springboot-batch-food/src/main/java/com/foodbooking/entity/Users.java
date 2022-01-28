package com.foodbooking.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Users {
	
	@Id
	private Long userId;
	private String name;
}
