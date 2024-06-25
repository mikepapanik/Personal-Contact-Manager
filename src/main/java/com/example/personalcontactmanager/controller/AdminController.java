package com.example.personalcontactmanager.controller;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

import com.example.personalcontactmanager.dto.UserDTO;
import com.example.personalcontactmanager.mapper.UserMapper;
import com.example.personalcontactmanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.personalcontactmanager.dao.ContactRepository;
import com.example.personalcontactmanager.dao.UserRepository;
import com.example.personalcontactmanager.entities.Contact;
import com.example.personalcontactmanager.entities.User;
import com.example.personalcontactmanager.helper.Message;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Controller class for managing admin-related operations.
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    /**
     * Displays the admin dashboard with paginated users.
     *
     * @param page the page number to display
     * @param principal the principal of the logged-in user
     * @param model the model to hold attributes
     * @return the admin dashboard view
     */
    @GetMapping("/dashboard/{page}")
    public String getDashboard(@PathVariable("page") Integer page, Principal principal, Model model) {
        String userName = principal.getName();
        User adminUser = this.userRepository.findByEmail(userName);

        Pageable pageable = PageRequest.of(page, 9);
        Page<User> users = this.userRepository.findAll(pageable);

        model.addAttribute("user", userMapper.toDTO(adminUser));
        model.addAttribute("users", users.getContent().stream().map(userMapper::toDTO).collect(Collectors.toList()));
        model.addAttribute("title", "Admin Dashboard");
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", users.getTotalPages());
        return "admin/admin_dashboard";
    }

    /**
     * Displays the user profile page for a specific user.
     *
     * @param uid the user ID
     * @param model the model to hold attributes
     * @return the user profile view
     */
    @GetMapping("/user-profile/{uid}")
    public String getUserProfile(@PathVariable("uid") Integer uid, Model model) {
        User user = this.userRepository.findById(uid).orElse(null);
        model.addAttribute("title", "User Profile");
        model.addAttribute("user", userMapper.toDTO(user));
        return "admin/user_profile";
    }

    /**
     * Handles the deletion of a user.
     *
     * @param uid the user ID to delete
     * @param session the HTTP session
     * @param principal the principal of the logged-in admin
     * @return the redirection to the admin dashboard
     */
    @GetMapping("/user-delete/{uid}")
    public String deleteUserHandler(@PathVariable("uid") Integer uid, HttpSession session, Principal principal) {
        // Fetch the currently logged-in admin
        String adminUsername = principal.getName();
        User adminUser = userService.getUserByUsername(adminUsername);

        // Ensure that the admin user is actually an admin
        if (!"ROLE_ADMIN".equals(adminUser.getRole())) {
            session.setAttribute("message", new Message("You are not authorized to perform this action.", "alert-danger"));
            return "redirect:/admin/dashboard/0";
        }

        // Find the user to be deleted
        User deletedUser = this.userRepository.findById(uid).orElse(null);
        if (deletedUser != null) {
            // Delete all contacts associated with the user
            List<Contact> contacts = this.contactRepository.findByUser(deletedUser);
            for (Contact contact : contacts) {
                this.contactRepository.delete(contact);
            }
            // Delete the user
            this.userRepository.delete(deletedUser);
            session.setAttribute("message", new Message("User has been deleted successfully.", "alert-success"));
        } else {
            session.setAttribute("message", new Message("User not found.", "alert-danger"));
        }
        return "redirect:/admin/dashboard/0";
    }

    /**
     * Displays the admin dashboard.
     *
     * @param model the model to hold attributes
     * @param page the page number to display
     * @return the admin dashboard view
     */
    @GetMapping("/dashboard")
    public String adminDashboard(Model model, @RequestParam(defaultValue = "0") int page) {
        Pageable pageable = PageRequest.of(page, 9);
        Page<User> usersPage = userRepository.findAll(pageable);
        model.addAttribute("users", usersPage.getContent().stream().map(userMapper::toDTO).collect(Collectors.toList()));
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", usersPage.getTotalPages());
        return "admin_dashboard";
    }
}
