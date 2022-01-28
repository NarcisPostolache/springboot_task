package com.spectre.app.service;

import com.spectre.app.entity.User;

import java.math.BigDecimal;

public interface UserService {
    boolean registerUser(User user);

    User getUser(String userId);
}
