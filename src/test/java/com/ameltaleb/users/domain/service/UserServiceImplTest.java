package com.ameltaleb.users.domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.ameltaleb.users.domain.exceptions.EmailAlreadyUsedException;
import com.ameltaleb.users.domain.model.User;
import com.ameltaleb.users.domain.port.out.UserRepository;

public class UserServiceImplTest {

    private UserRepository userRepository;
    private UserServiceImpl userService;

    @BeforeEach
    void setup() {
        /*
         * Preparing mock before testing
         */
        userRepository = mock(UserRepository.class);
        userService = new UserServiceImpl(userRepository);
    }

    @Test
    void testGetAllUsers() {
        /*
         * #RED : if the method is not yet implemented => it will fail
         */
        List<User> mockUsers = Arrays.asList(
            new User(1L, "Joan", "joan@mail.com"),
            new User(2L, "Victor", "victor@mail.com")
        );
        /*
         * Configure the mock
         */
        when(userRepository.findAll()).thenReturn(mockUsers);
        /*
         * #GREEN : execute the real method
         */
        List<User> result = userService.getAllUsers();
        /*
         * #CHECK the tests
         */
        assertEquals(2, result.size());
        assertEquals("Joan", result.get(0).getName());
        assertEquals("Victor", result.get(1).getName());
        /*
         * #Verify that the mock was called once
         */
        verify(userRepository, times(1)).findAll();
    }

    @Test
    void testCreateUser() {

        User user = new User(null, "Ana", "ana@mail.com");
        User savedUser = new User(3L, "Ana", "ana@mail.com");

        when(userRepository.findByEmail("ana@mail.com")).thenReturn(Optional.empty());
        when(userRepository.save(user)).thenReturn(savedUser);

        User result = userService.createUser(user);

        assertEquals(3L, result.getId());
        verify(userRepository, times(1)).findByEmail("ana@mail.com");
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void shouldThrowExceptionIfEmailAlreadyExists() {

        User user = new User(null, "Ana", "ana@mail.com");
        when(userRepository.findByEmail("ana@mail.com")).thenReturn(Optional.of(user));

        assertThrows(EmailAlreadyUsedException.class, () -> userService.createUser(user));

        verify(userRepository, times(1)).findByEmail("ana@mail.com");
        verify(userRepository, never()).save(any());
    }
}
