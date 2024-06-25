package com.example.personalcontactmanager.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.personalcontactmanager.dao.UserRepository;
import com.example.personalcontactmanager.dto.UserDTO;
import com.example.personalcontactmanager.entities.User;
import com.example.personalcontactmanager.helper.Message;
import com.example.personalcontactmanager.mapper.UserMapper;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

/**
 * Controller class for managing home-related operations.
 */
@Controller
public class HomeController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private UserMapper userMapper;

	/**
	 * Displays the home page.
	 *
	 * @param model the model to hold attributes
	 * @param principal the principal of the authenticated user
	 * @return the home view
	 */
	@GetMapping("/")
	public String home(Model model, Principal principal) {
		if (principal == null) {
			model.addAttribute("user", null);
			return "home";
		}
		String userName = principal.getName();
		User user = userRepository.getUserByUserName(userName);
		model.addAttribute("user", userMapper.toDTO(user));
		model.addAttribute("title", "Home - Personal Contact Manager");
		return "home";
	}

	/**
	 * Displays the about page.
	 *
	 * @param model the model to hold attributes
	 * @param principal the principal of the authenticated user
	 * @return the about view
	 */
	@GetMapping("/about")
	public String about(Model model, Principal principal) {
		if (principal == null) {
			model.addAttribute("user", null);
			return "about";
		}
		String userName = principal.getName();
		User user = userRepository.getUserByUserName(userName);
		model.addAttribute("user", userMapper.toDTO(user));
		model.addAttribute("title", "About - Personal Contact Manager");
		return "about";
	}

	/**
	 * Displays the signup page.
	 *
	 * @param model the model to hold attributes
	 * @return the signup view
	 */
	@GetMapping("/signup")
	public String signup(Model model) {
		model.addAttribute("user", new UserDTO());
		model.addAttribute("title", "Register - Personal Contact Manager");
		model.addAttribute("message", null);
		return "signup";
	}

	/**
	 * Displays the login page.
	 *
	 * @param model the model to hold attributes
	 * @param session the HTTP session
	 * @return the login view
	 */
	@GetMapping("/signin")
	public String login(Model model, HttpSession session) {
		model.addAttribute("userLogin", true);
		model.addAttribute("title", "Login - Personal Contact Manager");
		if (session.getAttribute("validation") != null) {
			String validationStatus = (String) session.getAttribute("validation");
			System.out.println(validationStatus);
		}
		return "login";
	}

	/**
	 * Handles user registration.
	 *
	 * @param userDTO the DTO of the user to be registered
	 * @param result1 the binding result for validation
	 * @param agreement the agreement to terms and conditions
	 * @param model the model to hold attributes
	 * @param session the HTTP session
	 * @return the signup view
	 */
	@PostMapping("/do-register")
	public String handleRegistration(@Valid @ModelAttribute("user") UserDTO userDTO, BindingResult result1,
									 @RequestParam(value = "agreement", defaultValue = "false") boolean agreement,
									 Model model, HttpSession session) {
		try {
			if (!agreement) {
				throw new Exception("You haven't agreed to the terms and conditions.");
			}
			if (result1.hasErrors()) {
				model.addAttribute("user", userDTO);
				return "signup";
			}
			User user = userMapper.toEntity(userDTO);
			user.setEnabled(true);
			user.setRole("ROLE_USER");
			user.setImageUrl("default.webp");
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			user.setSecretAnswer(passwordEncoder.encode(user.getSecretAnswer()));  // Κρυπτογράφηση του secretAnswer
			user.setValidated(1);  // Set as 1 to bypass validation
			User result = userRepository.save(user);
			model.addAttribute("user", new UserDTO());
			model.addAttribute("message", new Message("Successfully registered...", "alert-primary"));
		} catch (Exception e) {
			model.addAttribute("user", userDTO);
			model.addAttribute("message", new Message("Something went wrong: " + e.getMessage(), "alert-danger"));
		}
		return "signup";
	}

	/**
	 * Handles password change.
	 *
	 * @param newPassword the new password
	 * @param session the HTTP session
	 * @return the login view
	 */
	@PostMapping("/change-password")
	public String changePassword(@RequestParam("newPassword") String newPassword, HttpSession session) {
		String email = (String) session.getAttribute("email");
		User user = this.userRepository.getUserByUserName(email);
		user.setPassword(this.passwordEncoder.encode(newPassword));
		this.userRepository.save(user);
		session.setAttribute("message", new Message("Password changed successfully...", "alert-success"));
		return "login";
	}
}
