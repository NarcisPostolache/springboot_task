package com.foodbooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodbooking.entity.Users;


public interface UsersRepository extends JpaRepository<Users, Long> {
}
