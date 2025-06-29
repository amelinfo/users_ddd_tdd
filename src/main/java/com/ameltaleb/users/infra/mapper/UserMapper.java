package com.ameltaleb.users.infra.mapper;

import com.ameltaleb.users.domain.model.User;
import com.ameltaleb.users.infra.persistence.UserEntity;

public class UserMapper {

    public static User toDomain(UserEntity entity) {
        User user = new User();
        user.setId(entity.getId());
        user.setName(entity.getName());
        user.setEmail(entity.getEmail());
        user.setActive(entity.isActive());
        return user;
    }

    public static UserEntity toEntity(User user) {
        UserEntity entity = new UserEntity();
        entity.setId(user.getId());
        entity.setName(user.getName());
        entity.setEmail(user.getEmail());
        entity.setActive(user.isActive());
        return entity;
    }
}