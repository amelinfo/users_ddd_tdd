package com.ameltaleb.users.domain.port.out;

import java.util.List;
import java.util.Optional;

import com.ameltaleb.users.domain.model.User;

public interface UserRepository {
    List<User> findAll();
    Optional<User> findByEmail(String email);
    User save(User user);
    Optional<User> findById(Long id);
}
