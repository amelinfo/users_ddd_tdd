package com.ameltaleb.users.domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
}
