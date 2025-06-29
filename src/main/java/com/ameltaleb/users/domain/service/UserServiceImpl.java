package com.ameltaleb.users.domain.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ameltaleb.users.domain.exceptions.EmailAlreadyUsedException;
import com.ameltaleb.users.domain.model.User;
import com.ameltaleb.users.domain.port.in.UserService;
import com.ameltaleb.users.domain.port.out.UserRepository;

/**
 * to save the bean
 */
@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User createUser(User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new EmailAlreadyUsedException(user.getEmail());
        }
        return userRepository.save(user);
    }

}
