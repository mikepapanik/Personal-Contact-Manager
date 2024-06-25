package com.example.personalcontactmanager.controller;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.personalcontactmanager.dao.ContactRepository;
import com.example.personalcontactmanager.dao.UserRepository;
import com.example.personalcontactmanager.dto.ContactDTO;
import com.example.personalcontactmanager.dto.UserDTO;
import com.example.personalcontactmanager.entities.Contact;
import com.example.personalcontactmanager.entities.User;
import com.example.personalcontactmanager.mapper.ContactMapper;
import com.example.personalcontactmanager.mapper.UserMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.ArraySchema;

/**
 * Controller class for managing search operations.
 */
@RestController
@Tag(name = "Search", description = "The Search API")
public class SearchController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ContactMapper contactMapper;

    /**
     * Searches contacts by name for the authenticated user.
     *
     * @param query the search query
     * @param principal the principal of the authenticated user
     * @return a ResponseEntity containing the list of ContactDTOs
     */
    @GetMapping("/search/{query}")
    @Operation(
            summary = "Search contacts",
            description = "Search contacts by name for the authenticated user",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully retrieved list of contacts",
                            content = @Content(array = @ArraySchema(schema = @Schema(implementation = ContactDTO.class)))),
                    @ApiResponse(responseCode = "401", description = "Unauthorized"),
                    @ApiResponse(responseCode = "403", description = "Forbidden"),
                    @ApiResponse(responseCode = "404", description = "Not Found")
            }
    )
    public ResponseEntity<?> search(@PathVariable("query") String query, Principal principal) {
        if (principal == null) {
            return ResponseEntity.status(401).body("Unauthorized");
        }
        User user = this.userRepository.getUserByUserName(principal.getName());
        List<Contact> contacts = this.contactRepository.findByNameContainingAndUser(query, user);
        List<ContactDTO> contactDTOs = contacts.stream()
                .map(contactMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(contactDTOs);
    }

    /**
     * Searches users by name.
     *
     * @param query the search query
     * @param principal the principal of the authenticated user
     * @return a ResponseEntity containing the list of UserDTOs
     */
    @GetMapping("/search-user/{query}")
    @Operation(
            summary = "Search users",
            description = "Search users by name",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully retrieved list of users",
                            content = @Content(array = @ArraySchema(schema = @Schema(implementation = UserDTO.class)))),
                    @ApiResponse(responseCode = "401", description = "Unauthorized"),
                    @ApiResponse(responseCode = "403", description = "Forbidden"),
                    @ApiResponse(responseCode = "404", description = "Not Found")
            }
    )
    public ResponseEntity<?> searchUserHandler(@PathVariable("query") String query, Principal principal) {
        if (principal == null) {
            return ResponseEntity.status(401).body("Unauthorized");
        }
        User user = this.userRepository.getUserByUserName(principal.getName());
        List<User> users = this.userRepository.findByNameContaining(query);
        List<UserDTO> userDTOs = users.stream()
                .map(userMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(userDTOs);
    }
}
