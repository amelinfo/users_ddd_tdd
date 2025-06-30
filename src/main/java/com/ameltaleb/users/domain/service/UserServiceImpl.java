package com.ameltaleb.users.domain.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ameltaleb.users.domain.exceptions.EmailAlreadyUsedException;
import com.ameltaleb.users.domain.exceptions.UserNotFoundException;
import com.ameltaleb.users.domain.model.User;
import com.ameltaleb.users.domain.port.in.UserService;
import com.ameltaleb.users.domain.port.out.UserRepository;

import jakarta.transaction.Transactional;

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
    @Transactional
    public User createUser(User user) {
        System.out.println("📩 Creating user: " + user);

        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            System.out.println("❌ Email already exists: " + user.getEmail());
            throw new EmailAlreadyUsedException(user.getEmail());
        }

        User saved = userRepository.save(user);
        System.out.println("✅ User saved: " + saved);
        return saved;
    }

    @Override
    @Transactional
    public User activateUser(Long id) {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new UserNotFoundException(id));
        user.setActive(true);
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public User desactivateUser(Long userId) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new UserNotFoundException(userId));
        user.setActive(false);
        return userRepository.save(user);
    }

    
    
}
