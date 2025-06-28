package com.ameltaleb.users.infra.mapper;

import com.ameltaleb.users.domain.model.User;
import com.ameltaleb.users.infra.persistence.UserEntity;

public class UserMapper {

    public static User toDomain(UserEntity entity) {
        return new User(entity.getId(), entity.getName(), entity.getEmail());
    }

    public static UserEntity toEntity(User user) {
        return new UserEntity(user.getId(), user.getName(), user.getEmail());
    }
}
