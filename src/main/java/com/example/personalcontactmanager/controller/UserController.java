package com.example.personalcontactmanager.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.personalcontactmanager.dto.UserDTO;
import com.example.personalcontactmanager.entities.Contact;
import com.example.personalcontactmanager.entities.User;
import com.example.personalcontactmanager.helper.Message;
import com.example.personalcontactmanager.mapper.UserMapper;
import com.example.personalcontactmanager.service.ContactService;
import com.example.personalcontactmanager.service.UserService;

import jakarta.servlet.http.HttpSession;

/**
 * Controller class for managing user-related operations.
 */
@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private ContactService contactService;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private UserMapper userMapper;

	/**
	 * Adds common data to the model.
	 *
	 * @param model the model to hold attributes
	 * @param principal the principal of the authenticated user
	 */
	@ModelAttribute
	public void addCommonData(Model model, Principal principal) {
		String username = principal.getName();
		User user = userService.getUserByUsername(username);
		model.addAttribute("user", userMapper.toDTO(user));
	}

	/**
	 * Displays the user dashboard.
	 *
	 * @param model the model to hold attributes
	 * @param principal the principal of the authenticated user
	 * @param session the HTTP session
	 * @return the user dashboard view
	 */
	@GetMapping("/dashboard")
	public String dashboard(Model model, Principal principal, HttpSession session) {
		String username = principal.getName();
		User user = userService.getUserByUsername(username);
		model.addAttribute("user", userMapper.toDTO(user));
		model.addAttribute("title", "User Dashboard");
		return "normal/user_dashboard";
	}

	/**
	 * Displays the add contact form.
	 *
	 * @param model the model to hold attributes
	 * @param principal the principal of the authenticated user
	 * @param session the HTTP session
	 * @return the add contact form view
	 */
	@GetMapping("/add-contact-form")
	public String addContactForm(Model model, Principal principal, HttpSession session) {
		String username = principal.getName();
		User user = userService.getUserByUsername(username);
		model.addAttribute("title", "Add Contact");
		model.addAttribute("contact", new Contact());
		return "normal/add_contact_form";
	}

	/**
	 * Processes the add contact form.
	 *
	 * @param contact the contact to be added
	 * @param file the profile image file
	 * @param principal the principal of the authenticated user
	 * @param session the HTTP session
	 * @param model the model to hold attributes
	 * @return the add contact form view
	 */
	@PostMapping("/process-contact")
	public String processContactForm(@ModelAttribute Contact contact, @RequestParam("profileImage") MultipartFile file,
									 Principal principal, HttpSession session, Model model) {
		try {
			String username = principal.getName();
			User user = userService.getUserByUsername(username);

			if (file.isEmpty()) {
				contact.setImage("default.webp");
			} else if (!file.getContentType().equals("image/jpeg") && !file.getContentType().equals("image/png")
					&& !file.getContentType().equals("image/jpg")) {
				contact.setImage("default.webp");
			} else {
				contact.setImage(file.getOriginalFilename());
				File saveFile = new ClassPathResource("static/img").getFile();
				Path path = Paths.get(saveFile.getAbsolutePath() + File.separator + file.getOriginalFilename());
				Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
			}

			contact.setUser(user);
			contactService.saveContact(contact);
			model.addAttribute("contact", new Contact());
			session.setAttribute("message", new Message("Contact added successfully ...", "alert-success"));
		} catch (Exception e) {
			model.addAttribute("contact", contact);
			session.setAttribute("message", new Message(e.getMessage(), "alert-danger"));
		}
		return "normal/add_contact_form";
	}

	/**
	 * Displays the contacts for the authenticated user.
	 *
	 * @param page the page number to display
	 * @param model the model to hold attributes
	 * @param principal the principal of the authenticated user
	 * @param session the HTTP session
	 * @return the contacts view
	 */
	@GetMapping("/show-contacts/{page}")
	public String showContactsHandler(@PathVariable("page") Integer page, Model model, Principal principal, HttpSession session) {
		String username = principal.getName();
		User user = userService.getUserByUsername(username);

		Pageable pageable = PageRequest.of(page, 4);
		Page<Contact> contacts = contactService.findContactsByUserId(user.getId(), pageable);

		model.addAttribute("contacts", contacts);
		model.addAttribute("title", "All Contacts");
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", contacts.getTotalPages());

		return "normal/show_contacts";
	}

	/**
	 * Displays the contact details.
	 *
	 * @param cId the contact ID
	 * @param model the model to hold attributes
	 * @param principal the principal of the authenticated user
	 * @param session the HTTP session
	 * @return the contact details view
	 */
	@GetMapping("/{cId}/contact")
	public String handleContactDetail(@PathVariable("cId") Integer cId, Model model, Principal principal, HttpSession session) {
		String username = principal.getName();
		User user = userService.getUserByUsername(username);
		Contact contact = contactService.getContactById(cId);
		if (user.getId() == contact.getUser().getId()) {
			model.addAttribute("contact", contact);
		}
		return "normal/contact_details";
	}

	/**
	 * Handles the deletion of a contact.
	 *
	 * @param cId the contact ID
	 * @param principal the principal of the authenticated user
	 * @param session the HTTP session
	 * @return the redirect to contacts view
	 */
	@GetMapping("/delete/{cId}")
	public String deleteContactHandler(@PathVariable("cId") Integer cId, Principal principal, HttpSession session) {
		String username = principal.getName();
		User user = userService.getUserByUsername(username);
		Contact contact = contactService.getContactById(cId);
		if (user.getId() == contact.getUser().getId()) {
			contactService.deleteContact(contact);
			session.setAttribute("message", new Message("Contact deleted successfully...", "alert-success"));
		} else {
			session.setAttribute("message", new Message("You don't have required permissions !!!", "alert-danger"));
		}
		return "redirect:/user/show-contacts/0";
	}

	/**
	 * Displays the update contact form.
	 *
	 * @param cId the contact ID
	 * @param model the model to hold attributes
	 * @return the update contact form view
	 */
	@PostMapping("/update-contact/{cId}")
	public String updateFormHandler(@PathVariable("cId") Integer cId, Model model) {
		model.addAttribute("title", "Update Contact");
		Contact contact = contactService.getContactById(cId);
		model.addAttribute("contact", contact);
		return "normal/update_contact_form";
	}

	/**
	 * Processes the update contact form.
	 *
	 * @param contact the contact to be updated
	 * @param file the profile image file
	 * @param model the model to hold attributes
	 * @param principal the principal of the authenticated user
	 * @param session the HTTP session
	 * @return the redirect to contacts view
	 */
	@PostMapping("/process-contact-update")
	public String processUpdateContactHandler(@ModelAttribute("contact") Contact contact,
											  @RequestParam("profileImage") MultipartFile file, Model model, Principal principal, HttpSession session) {
		try {
			Contact prevContact = contactService.getContactById(contact.getCid());
			String prevImage = prevContact.getImage();

			User user = userService.getUserByUsername(principal.getName());
			contact.setUser(user);

			if (file.isEmpty()) {
				contact.setImage(prevImage);
			} else {
				contact.setImage(file.getOriginalFilename());
				File saveFile = new ClassPathResource("static/img").getFile();
				Path path = Paths.get(saveFile.getAbsolutePath() + File.separator + file.getOriginalFilename());
				Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
			}

			contactService.saveContact(contact);
			session.setAttribute("message", new Message("Contact updated successfully ...", "alert-success"));

		} catch (Exception e) {
			session.setAttribute("message", new Message(e.getMessage(), "alert-danger"));
		}

		return "redirect:/user/show-contacts/0";
	}

	/**
	 * Displays the user profile.
	 *
	 * @param model the model to hold attributes
	 * @param principal the principal of the authenticated user
	 * @param session the HTTP session
	 * @return the user profile view
	 */
	@GetMapping("/profile")
	public String profileHandler(Model model, Principal principal, HttpSession session) {
		String username = principal.getName();
		User user = userService.getUserByUsername(username);
		model.addAttribute("title", "User Profile");
		return "normal/profile";
	}

	/**
	 * Displays the update profile form.
	 *
	 * @param model the model to hold attributes
	 * @return the update profile form view
	 */
	@GetMapping("/update-profile")
	public String updateProfileHandler(Model model) {
		model.addAttribute("title", "Edit Profile");
		return "normal/update_profile_form";
	}

	/**
	 * Processes the update profile form.
	 *
	 * @param name the new name
	 * @param about the new about information
	 * @param file the profile image file
	 * @param principal the principal of the authenticated user
	 * @param session the HTTP session
	 * @return the user profile view
	 * @throws IOException if an I/O error occurs
	 */
	@PostMapping("/process-update-profile")
	public String processEditProfileForm(@RequestParam("name") String name, @RequestParam("about") String about, @RequestParam("profileImage") MultipartFile file, Principal principal, HttpSession session) throws IOException {
		String username = principal.getName();
		User user = userService.getUserByUsername(username);
		user.setName(name);
		user.setAbout(about);

		if (!file.isEmpty()) {
			if (file.getContentType().equals("image/jpeg") || file.getContentType().equals("image/png")
					|| file.getContentType().equals("image/jpg")) {
				user.setImageUrl(file.getOriginalFilename());

				File saveFile = new ClassPathResource("static/img").getFile();
				Path path = Paths.get(saveFile.getAbsolutePath() + File.separator + file.getOriginalFilename());
				Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
			}
		}

		userService.saveUser(user);
		session.setAttribute("message", new Message("Profile updated successfully ...", "alert-success"));
		return "normal/profile";
	}

	/**
	 * Displays the settings page.
	 *
	 * @param model the model to hold attributes
	 * @param principal the principal of the authenticated user
	 * @param session the HTTP session
	 * @return the settings view
	 */
	@GetMapping("/settings")
	public String openSettings(Model model, Principal principal, HttpSession session) {
		String username = principal.getName();
		User user = userService.getUserByUsername(username);

		model.addAttribute("title", "Settings");
		return "normal/settings";
	}

	/**
	 * Handles password change.
	 *
	 * @param oldPassword the old password
	 * @param newPassword the new password
	 * @param principal the principal of the authenticated user
	 * @param session the HTTP session
	 * @return the redirect to the user dashboard
	 */
	@PostMapping("/change-password")
	public String changePassword(@RequestParam("oldPassword") String oldPassword,
								 @RequestParam("newPassword") String newPassword, Principal principal, HttpSession session) {
		User user = userService.getUserByUsername(principal.getName());
		String existingPassword = user.getPassword();
		if (passwordEncoder.matches(oldPassword, existingPassword)) {
			user.setPassword(passwordEncoder.encode(newPassword));
			userService.saveUser(user);
			session.setAttribute("message", new Message("Password is updated successfully...", "alert-success"));
		} else {
			session.setAttribute("message", new Message("Wrong old password !!!", "alert-danger"));
			return "redirect:/user/settings";
		}
		return "redirect:/user/dashboard";
	}

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
		model.addAttribute("email", email);
		model.addAttribute("secretQuestion", user.getSecretQuestion());
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
		if (user == null || !user.getSecretAnswer().equalsIgnoreCase(secretAnswer)) {
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
								@RequestParam("password") String password, Model model) {
		User user = userService.findByEmail(email);
		if (user == null) {
			model.addAttribute("error", "No account found with that email address.");
			return "forgot_password_form";
		}
		user.setPassword(passwordEncoder.encode(password));
		userService.saveUser(user);
		model.addAttribute("message", "Password successfully updated.");
		return "login";
	}
}
