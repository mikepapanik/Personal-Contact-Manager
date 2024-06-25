package com.example.personalcontactmanager.dao;

import com.example.personalcontactmanager.entities.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class UserRepositoryTest {

    @Mock
    private UserRepository userRepository;

    private User testUser;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        testUser = new User();
        testUser.setName("Michail Papanikolas");
        testUser.setEmail("michail@example.com");
        testUser.setPassword("password");
        testUser.setSecretQuestion("What is your pet's name?");
        testUser.setSecretAnswer("Fluffy");
    }

    @Test
    public void testGetUserByUserName() {
        when(userRepository.getUserByUserName(anyString())).thenReturn(testUser);
        User user = userRepository.getUserByUserName("michail@example.com");
        assertThat(user).isNotNull();
        assertThat(user.getName()).isEqualTo("Michail Papanikolas");
    }

    @Test
    public void testFindByNameContaining() {
        when(userRepository.findByNameContaining(anyString())).thenReturn(Arrays.asList(testUser));
        List<User> users = userRepository.findByNameContaining("Michail");
        assertThat(users).isNotEmpty();
        assertThat(users.get(0).getName()).isEqualTo("Michail Papanikolas");
    }

    @Test
    public void testFindByEmail() {
        when(userRepository.findByEmail(anyString())).thenReturn(testUser);
        User user = userRepository.findByEmail("michail@example.com");
        assertThat(user).isNotNull();
        assertThat(user.getName()).isEqualTo("Michail Papanikolas");
    }

    @Test
    public void testFindByEmailAndSecretQuestion() {
        when(userRepository.findByEmailAndSecretQuestion(anyString(), anyString())).thenReturn(testUser);
        User user = userRepository.findByEmailAndSecretQuestion("michail@example.com", "What is your pet's name?");
        assertThat(user).isNotNull();
        assertThat(user.getSecretAnswer()).isEqualTo("Fluffy");
    }
}
