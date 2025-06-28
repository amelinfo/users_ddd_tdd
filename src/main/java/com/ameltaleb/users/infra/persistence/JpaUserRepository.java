package com.ameltaleb.users.infra.persistence;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ameltaleb.users.domain.model.User;
import com.ameltaleb.users.domain.port.out.UserRepository;
import com.ameltaleb.users.infra.mapper.UserMapper;

/**
 * JpaRepository is annotated by @Repository:
 * 1-to detect the class as a bean
 * 2-to manage exceptions by Spring
 */
@Repository
public class JpaUserRepository implements UserRepository {

    private final SpringDataUserRepository springRepo;

    public JpaUserRepository(SpringDataUserRepository springRepo) {
        this.springRepo = springRepo;
    }
    
    @Override
    public List<User> findAll() {
        return springRepo.findAll().stream().map(UserMapper::toDomain).toList();
    }
    
}
