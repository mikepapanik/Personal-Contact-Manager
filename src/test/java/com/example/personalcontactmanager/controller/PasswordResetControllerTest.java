package com.example.personalcontactmanager.controller;

import com.example.personalcontactmanager.dto.UserDTO;
import com.example.personalcontactmanager.entities.User;
import com.example.personalcontactmanager.mapper.UserMapper;
import com.example.personalcontactmanager.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class PasswordResetControllerTest {

    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @Mock
    private UserMapper userMapper;

    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    @InjectMocks
    private PasswordResetController passwordResetController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(passwordResetController).build();
    }

    @Test
    public void testShowForgotPasswordForm() throws Exception {
        mockMvc.perform(get("/forgot-password"))
                .andExpect(status().isOk())
                .andExpect(view().name("forgot_password_form"));
    }

    @Test
    public void testProcessForgotPasswordForm_UserExists() throws Exception {
        User user = new User();
        user.setEmail("testuser@example.com");
        user.setSecretQuestion("What is your pet's name?");
        UserDTO userDTO = new UserDTO();
        userDTO.setSecretQuestion("What is your pet's name?");

        when(userService.findByEmail("testuser@example.com")).thenReturn(user);
        when(userMapper.toDTO(user)).thenReturn(userDTO);

        mockMvc.perform(post("/forgot-password")
                        .param("email", "testuser@example.com"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("email", "testuser@example.com"))
                .andExpect(model().attribute("secretQuestion", "What is your pet's name?"))
                .andExpect(view().name("verify_secret_question"));
    }

    @Test
    public void testProcessForgotPasswordForm_UserNotExists() throws Exception {
        when(userService.findByEmail("testuser@example.com")).thenReturn(null);

        mockMvc.perform(post("/forgot-password")
                        .param("email", "testuser@example.com"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("error", "No account found with that email address."))
                .andExpect(view().name("forgot_password_form"));
    }

    @Test
    public void testVerifySecretQuestion_Success() throws Exception {
        User user = new User();
        user.setEmail("testuser@example.com");
        user.setSecretAnswer("encodedAnswer");

        when(userService.findByEmail("testuser@example.com")).thenReturn(user);
        when(passwordEncoder.matches("Fluffy", "encodedAnswer")).thenReturn(true);

        mockMvc.perform(post("/verify-secret-question")
                        .param("email", "testuser@example.com")
                        .param("secretAnswer", "Fluffy"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("email", "testuser@example.com"))
                .andExpect(view().name("reset_password_form"));
    }

    @Test
    public void testVerifySecretQuestion_Failure() throws Exception {
        User user = new User();
        user.setEmail("testuser@example.com");
        user.setSecretQuestion("What is your pet's name?");
        user.setSecretAnswer("encodedAnswer");

        when(userService.findByEmail("testuser@example.com")).thenReturn(user);
        when(passwordEncoder.matches("WrongAnswer", "encodedAnswer")).thenReturn(false);

        mockMvc.perform(post("/verify-secret-question")
                        .param("email", "testuser@example.com")
                        .param("secretAnswer", "WrongAnswer"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("error", "Invalid answer to the secret question."))
                .andExpect(model().attribute("email", "testuser@example.com"))
                .andExpect(model().attribute("secretQuestion", "What is your pet's name?"))
                .andExpect(view().name("verify_secret_question"));
    }

    @Test
    public void testResetPassword_Success() throws Exception {
        User user = new User();
        user.setEmail("testuser@example.com");
        user.setPassword("OldPassword");

        when(userService.findByEmail("testuser@example.com")).thenReturn(user);

        mockMvc.perform(post("/reset-password")
                        .param("email", "testuser@example.com")
                        .param("password", "NewPassword1!"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"));
    }

    @Test
    public void testResetPassword_InvalidPassword() throws Exception {
        mockMvc.perform(post("/reset-password")
                        .param("email", "testuser@example.com")
                        .param("password", "short"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("error", "Password should be at least 8 characters long, contain one uppercase letter, one lowercase letter, one digit, and one special character."))
                .andExpect(model().attribute("email", "testuser@example.com"))
                .andExpect(view().name("reset_password_form"));
    }
}
