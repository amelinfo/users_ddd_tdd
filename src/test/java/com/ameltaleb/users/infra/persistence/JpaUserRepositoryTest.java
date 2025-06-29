package com.ameltaleb.users.infra.persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.ameltaleb.users.domain.model.User;

public class JpaUserRepositoryTest {

    private SpringDataUserRepository springDataUserRepository;
    private JpaUserRepository jpaUserRepository;

    @BeforeEach
    void setup() {
        springDataUserRepository = mock(SpringDataUserRepository.class);
        jpaUserRepository = new JpaUserRepository(springDataUserRepository);
    }

    @Test
    void testFindAll() {
        
        //prepare entities
        UserEntity entity1 = new UserEntity(1L, "Joan", "joan@mail.com", false);
        UserEntity entity2 = new UserEntity(2L, "Victor", "victor@mail.com", true);

        when(springDataUserRepository.findAll()).thenReturn(List.of(entity1, entity2));
        //call method
        List<User> users = jpaUserRepository.findAll();

        // Assert
        assertNotNull(users);
        assertEquals(2, users.size());

        assertEquals(entity1.getId(), users.get(0).getId());
        assertEquals(entity1.getName(), users.get(0).getName());
        assertEquals(entity1.getEmail(), users.get(0).getEmail());

        assertEquals(entity2.getId(), users.get(1).getId());
        assertEquals(entity2.getName(), users.get(1).getName());
        assertEquals(entity2.getEmail(), users.get(1).getEmail());

        //Verify the call of the mock
        verify(springDataUserRepository, times(1)).findAll();
    }
}
