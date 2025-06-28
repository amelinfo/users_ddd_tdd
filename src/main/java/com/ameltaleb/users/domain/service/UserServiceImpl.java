package com.ameltaleb.users.domain.service;

import java.util.List;

import com.ameltaleb.users.domain.model.User;
import com.ameltaleb.users.domain.port.in.UserService;
import com.ameltaleb.users.domain.port.out.UserRepository;

public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

}
