package com.ameltaleb.users.domain.port.in;

import java.util.List;

import com.ameltaleb.users.domain.model.User;

public interface UserService {
    List<User> getAllUsers();
}
