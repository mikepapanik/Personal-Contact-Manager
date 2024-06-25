package com.example.personalcontactmanager.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.example.personalcontactmanager.dao.UserRepository;
import com.example.personalcontactmanager.dto.UserDTO;
import com.example.personalcontactmanager.entities.User;
import com.example.personalcontactmanager.mapper.UserMapper;
import com.example.personalcontactmanager.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetUserByUsername() {
        User user = new User();
        user.setUsername("admin");
        when(userRepository.getUserByUserName("admin")).thenReturn(user);

        User result = userService.getUserByUsername("admin");
        assertEquals("admin", result.getUsername());
    }

    @Test
    public void testRegisterUser() {
        User user = new User();
        user.setPassword("plainPassword");
        user.setSecretAnswer("plainAnswer");

        when(passwordEncoder.encode("plainPassword")).thenReturn("encodedPassword");
        when(passwordEncoder.encode("plainAnswer")).thenReturn("encodedAnswer");
        when(userRepository.save(any(User.class))).thenReturn(user);

        User result = userService.registerUser(user);
        assertEquals("encodedPassword", result.getPassword());
        assertEquals("encodedAnswer", result.getSecretAnswer());
    }

    @Test
    public void testFindByEmail() {
        User user = new User();
        user.setEmail("admin@example.com");

        when(userRepository.findByEmail("admin@example.com")).thenReturn(user);

        User result = userService.findByEmail("admin@example.com");
        assertEquals("admin@example.com", result.getEmail());
    }

    @Test
    public void testUpdatePassword() {
        User user = new User();
        user.setPassword("plainPassword");

        when(passwordEncoder.encode("plainPassword")).thenReturn("encodedPassword");

        userService.updatePassword(user);
        verify(userRepository, times(1)).save(user);
        assertEquals("encodedPassword", user.getPassword());
    }

    @Test
    public void testGetUserById() {
        User user = new User();
        user.setId(1);

        when(userRepository.findById(1)).thenReturn(Optional.of(user));

        Optional<User> result = userService.getUserById(1);
        assertTrue(result.isPresent());
        assertEquals(1, result.get().getId());
    }

    @Test
    public void testVerifySecretAnswer() {
        User user = new User();
        user.setSecretAnswer("encodedAnswer");

        when(passwordEncoder.matches("plainAnswer", "encodedAnswer")).thenReturn(true);

        boolean result = userService.verifySecretAnswer(user, "plainAnswer");
        assertTrue(result);
    }
}
