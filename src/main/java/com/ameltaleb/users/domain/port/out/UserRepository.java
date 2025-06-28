package com.ameltaleb.users.domain.port.out;

import java.util.List;

import com.ameltaleb.users.domain.model.User;

public interface UserRepository {
    List<User> findAll();
}
