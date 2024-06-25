package com.example.personalcontactmanager.controller;

import com.example.personalcontactmanager.dto.UserDTO;
import com.example.personalcontactmanager.entities.User;
import com.example.personalcontactmanager.helper.Message;
import com.example.personalcontactmanager.mapper.UserMapper;
import com.example.personalcontactmanager.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.regex.Pattern;

/**
 * Controller class for managing password reset operations.
 */
@Controller
public class PasswordResetController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    /**
     * Displays the forgot password form.
     *
     * @return the forgot password form view
     */
    @GetMapping("/forgot-password")
    public String showForgotPasswordForm() {
        return "forgot_password_form";
    }

    /**
     * Processes the forgot password form.
     *
     * @param email the email of the user
     * @param model the model to hold attributes
     * @return the view to verify the secret question
     */
    @PostMapping("/forgot-password")
    public String processForgotPasswordForm(@RequestParam("email") String email, Model model) {
        User user = userService.findByEmail(email);
        if (user == null) {
            model.addAttribute("error", "No account found with that email address.");
            return "forgot_password_form";
        }
        UserDTO userDTO = userMapper.toDTO(user);
        model.addAttribute("email", email);
        model.addAttribute("secretQuestion", userDTO.getSecretQuestion());
        return "verify_secret_question";
    }

    /**
     * Verifies the secret question answer.
     *
     * @param email the email of the user
     * @param secretAnswer the answer to the secret question
     * @param model the model to hold attributes
     * @return the view to reset the password
     */
    @PostMapping("/verify-secret-question")
    public String verifySecretQuestion(@RequestParam("email") String email,
                                       @RequestParam("secretAnswer") String secretAnswer, Model model) {
        User user = userService.findByEmail(email);
        if (user == null || !passwordEncoder.matches(secretAnswer, user.getSecretAnswer())) {
            model.addAttribute("error", "Invalid answer to the secret question.");
            model.addAttribute("email", email);
            model.addAttribute("secretQuestion", user.getSecretQuestion());
            return "verify_secret_question";
        }
        model.addAttribute("email", email);
        return "reset_password_form";
    }

    /**
     * Resets the password.
     *
     * @param email the email of the user
     * @param password the new password
     * @param model the model to hold attributes
     * @return the login view
     */
    @PostMapping("/reset-password")
    public String resetPassword(@RequestParam("email") String email,
                                @RequestParam("password") String password, Model model, HttpSession session) {
        String passwordPattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[^a-zA-Z\\d]).{8,}$";
        if (!Pattern.matches(passwordPattern, password)) {
            model.addAttribute("error", "Password should be at least 8 characters long, contain one uppercase letter, one lowercase letter, one digit, and one special character.");
            model.addAttribute("email", email);
            return "reset_password_form";
        }

        User user = userService.findByEmail(email);
        if (user == null) {
            model.addAttribute("error", "No account found with that email address.");
            return "forgot_password_form";
        }

        user.setPassword(passwordEncoder.encode(password));
        userService.saveUser(user);

        session.setAttribute("message", new Message("Password successfully updated.", "alert-success"));

        return "login";
    }
}
