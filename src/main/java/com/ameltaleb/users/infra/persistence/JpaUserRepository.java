package com.ameltaleb.users.infra.persistence;

import java.util.List;
import java.util.Optional;
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

    private final SpringDataUserRepository springDataUserRepository;

    public JpaUserRepository(SpringDataUserRepository springDataUserRepository) {
        this.springDataUserRepository = springDataUserRepository;
    }
    
    @Override
    public List<User> findAll() {
        return springDataUserRepository.findAll().stream().map(UserMapper::toDomain).toList();
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return springDataUserRepository.findByEmail(email).map(UserMapper::toDomain);
    }

    @Override
    public User save(User user) {
        UserEntity entity = UserMapper.toEntity(user);
        UserEntity saved = springDataUserRepository.save(entity);
        return UserMapper.toDomain(saved);
    }
    
}
