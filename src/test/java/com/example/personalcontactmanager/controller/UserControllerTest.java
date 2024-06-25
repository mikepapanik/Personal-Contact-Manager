package com.example.personalcontactmanager.controller;

import com.example.personalcontactmanager.dto.UserDTO;
import com.example.personalcontactmanager.entities.Contact;
import com.example.personalcontactmanager.entities.User;
import com.example.personalcontactmanager.mapper.UserMapper;
import com.example.personalcontactmanager.service.ContactService;
import com.example.personalcontactmanager.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Mock
    private UserService userService;

    @Mock
    private ContactService contactService;

    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserController userController;

    private MockMvc mockMvc;
    private User user;
    private UserDTO userDTO;
    private Contact contact;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();

        user = new User();
        user.setId(1);
        user.setName("Michail Papanikolas");
        user.setEmail("michail@example.com");

        userDTO = new UserDTO();
        userDTO.setId(1);
        userDTO.setName("Michail Papanikolas");
        userDTO.setEmail("michail@example.com");

        contact = new Contact();
        contact.setCid(1);
        contact.setName("Test Contact");
        contact.setUser(user);

        when(userService.getUserByUsername(anyString())).thenReturn(user);
        when(userMapper.toDTO(any(User.class))).thenReturn(userDTO);
    }

    @Test
    void testDashboard() throws Exception {
        mockMvc.perform(get("/user/dashboard")
                        .principal(() -> "michail@example.com"))
                .andExpect(status().isOk())
                .andExpect(view().name("normal/user_dashboard"))
                .andExpect(model().attributeExists("user"))
                .andExpect(model().attribute("user", userDTO));
    }

    @Test
    void testAddContactForm() throws Exception {
        mockMvc.perform(get("/user/add-contact-form")
                        .principal(() -> "michail@example.com"))
                .andExpect(status().isOk())
                .andExpect(view().name("normal/add_contact_form"))
                .andExpect(model().attributeExists("contact"))
                .andExpect(model().attributeExists("title"))
                .andExpect(model().attribute("title", "Add Contact"));
    }

    @Test
    void testShowContactsHandler() throws Exception {
        List<Contact> contacts = new ArrayList<>();
        contacts.add(contact);
        Page<Contact> contactsPage = new PageImpl<>(contacts);

        when(contactService.findContactsByUserId(anyInt(), any(Pageable.class))).thenReturn(contactsPage);

        mockMvc.perform(get("/user/show-contacts/0")
                        .principal(() -> "michail@example.com"))
                .andExpect(status().isOk())
                .andExpect(view().name("normal/show_contacts"))
                .andExpect(model().attributeExists("contacts"))
                .andExpect(model().attributeExists("currentPage"))
                .andExpect(model().attributeExists("totalPages"))
                .andExpect(model().attribute("contacts", contactsPage));
    }

    @Test
    void testHandleContactDetail() throws Exception {
        when(contactService.getContactById(anyInt())).thenReturn(contact);

        mockMvc.perform(get("/user/1/contact")
                        .principal(() -> "michail@example.com"))
                .andExpect(status().isOk())
                .andExpect(view().name("normal/contact_details"))
                .andExpect(model().attributeExists("contact"))
                .andExpect(model().attribute("contact", contact));
    }

    @Test
    void testProfileHandler() throws Exception {
        mockMvc.perform(get("/user/profile")
                        .principal(() -> "michail@example.com"))
                .andExpect(status().isOk())
                .andExpect(view().name("normal/profile"))
                .andExpect(model().attributeExists("user"))
                .andExpect(model().attribute("title", "User Profile"));
    }
}
