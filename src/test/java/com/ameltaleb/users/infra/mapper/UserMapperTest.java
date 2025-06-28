package com.ameltaleb.users.infra.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.ameltaleb.users.domain.model.User;
import com.ameltaleb.users.infra.persistence.UserEntity;

public class UserMapperTest {
    
    @Test
    void testToDomain() {
        // Given
        UserEntity entity = new UserEntity(3L, "Ana", "ana@mail.com");

        // When
        User domain = UserMapper.toDomain(entity);

        // Then
        assertEquals(entity.getId(), domain.getId());
        assertEquals(entity.getName(), domain.getName());
        assertEquals(entity.getEmail(), domain.getEmail());
    }

    @Test
    void testToEntity() {
        // Given
        User domain = new User(2L, "Victor", "victor@mail.com");

        // When
        UserEntity entity = UserMapper.toEntity(domain);

        // Then
        assertEquals(domain.getId(), entity.getId());
        assertEquals(domain.getName(), entity.getName());
        assertEquals(domain.getEmail(), entity.getEmail());
    }
}
