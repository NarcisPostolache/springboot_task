package com.spectre.app.service;

import com.spectre.app.entity.User;
import com.spectre.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    @Override
    public boolean registerUser(User user) {
        userRepository.save(user);
        return true;
    }

    @Override
    public User getUser(String userId) {
        return userRepository.findByUserId(userId);
    }
}
