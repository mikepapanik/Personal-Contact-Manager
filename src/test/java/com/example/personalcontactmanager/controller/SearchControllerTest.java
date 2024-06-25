package com.example.personalcontactmanager.controller;

import com.example.personalcontactmanager.dao.ContactRepository;
import com.example.personalcontactmanager.dao.UserRepository;
import com.example.personalcontactmanager.dto.ContactDTO;
import com.example.personalcontactmanager.dto.UserDTO;
import com.example.personalcontactmanager.entities.Contact;
import com.example.personalcontactmanager.entities.User;
import com.example.personalcontactmanager.mapper.ContactMapper;
import com.example.personalcontactmanager.mapper.UserMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SearchController.class)
public class SearchControllerTest {

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private ContactRepository contactRepository;

    @MockBean
    private UserMapper userMapper;

    @MockBean
    private ContactMapper contactMapper;

    @InjectMocks
    private SearchController searchController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(searchController).build();
    }

    @Test
    @WithMockUser(username = "testuser")
    public void testSearchContacts() throws Exception {
        User user = new User();
        user.setUsername("testuser");

        Contact contact = new Contact();
        contact.setName("Michail Papanikolas");
        List<Contact> contacts = Arrays.asList(contact);

        ContactDTO contactDTO = new ContactDTO();
        contactDTO.setName("Michail Papanikolas");

        when(userRepository.getUserByUserName(anyString())).thenReturn(user);
        when(contactRepository.findByNameContainingAndUser(anyString(), eq(user))).thenReturn(contacts);
        when(contactMapper.toDTO(contact)).thenReturn(contactDTO);

        mockMvc.perform(get("/search/Michail")
                        .principal(() -> "testuser"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{'name':'Michail Papanikolas'}]"));
    }

    @Test
    @WithMockUser(username = "testuser")
    public void testSearchUsers() throws Exception {
        User user = new User();
        user.setUsername("testuser");

        User searchedUser = new User();
        searchedUser.setName("Michail Papanikolas");
        List<User> users = Arrays.asList(searchedUser);

        UserDTO userDTO = new UserDTO();
        userDTO.setName("Michail Papanikolas");

        when(userRepository.getUserByUserName(anyString())).thenReturn(user);
        when(userRepository.findByNameContaining(anyString())).thenReturn(users);
        when(userMapper.toDTO(searchedUser)).thenReturn(userDTO);

        mockMvc.perform(get("/search-user/Michail")
                        .principal(() -> "testuser"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{'name':'Michail Papanikolas'}]"));
    }
}
