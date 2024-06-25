package com.example.personalcontactmanager.controller;

import com.example.personalcontactmanager.dto.ContactDTO;
import com.example.personalcontactmanager.entities.Contact;
import com.example.personalcontactmanager.entities.User;
import com.example.personalcontactmanager.mapper.ContactMapper;
import com.example.personalcontactmanager.service.ContactService;
import com.example.personalcontactmanager.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Controller class for managing contacts related operations.
 */
@RestController
@RequestMapping("/api/contacts")
@Tag(name = "Contact", description = "The Contact API")
public class ContactController {

    @Autowired
    private ContactService contactService;

    @Autowired
    private UserService userService;

    @Autowired
    private ContactMapper contactMapper;

    /**
     * Retrieves a list of contacts for the authenticated user.
     *
     * @param principal the principal of the authenticated user
     * @return a ResponseEntity containing the list of ContactDTOs
     */
    @GetMapping("/user")
    @Operation(
            summary = "Get user contacts",
            description = "Retrieve a list of contacts for the authenticated user",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully retrieved list of contacts",
                            content = @Content(array = @ArraySchema(schema = @Schema(implementation = ContactDTO.class)))),
                    @ApiResponse(responseCode = "401", description = "Unauthorized"),
                    @ApiResponse(responseCode = "403", description = "Forbidden"),
                    @ApiResponse(responseCode = "404", description = "Not Found")
            }
    )
    public ResponseEntity<?> getUserContacts(Principal principal) {
        if (principal == null) {
            return ResponseEntity.status(401).body("Unauthorized");
        }
        User user = userService.getUserByUsername(principal.getName());
        List<ContactDTO> contacts = contactService.findContactsByUserId(user.getId()).stream()
                .map(contactMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(contacts);
    }

    /**
     * Updates a contact by its ID.
     *
     * @param id        the ID of the contact to update
     * @param contact   the updated contact information
     * @param principal the principal of the authenticated user
     * @return a ResponseEntity with the status of the update
     */
    @PutMapping("/{id}")
    @Operation(
            summary = "Update a contact",
            description = "Update a contact by ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully updated contact"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized"),
                    @ApiResponse(responseCode = "403", description = "Forbidden"),
                    @ApiResponse(responseCode = "404", description = "Not Found")
            }
    )
    public ResponseEntity<?> updateContact(@PathVariable int id, @RequestBody Contact contact, Principal principal) {
        if (principal == null) {
            return ResponseEntity.status(401).body("Unauthorized");
        }
        User user = userService.getUserByUsername(principal.getName());
        if (contactService.getContactById(id).getUser().getId() != user.getId()) {
            return ResponseEntity.status(403).body("Forbidden");
        }
        contactService.saveContact(contact);
        return ResponseEntity.ok("Contact updated successfully");
    }

    /**
     * Deletes a contact by its ID.
     *
     * @param id        the ID of the contact to delete
     * @param principal the principal of the authenticated user
     * @return a ResponseEntity with the status of the deletion
     */
    @DeleteMapping("/{id}")
    @Operation(
            summary = "Delete a contact",
            description = "Delete a contact by ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully deleted contact"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized"),
                    @ApiResponse(responseCode = "403", description = "Forbidden"),
                    @ApiResponse(responseCode = "404", description = "Not Found")
            }
    )
    public ResponseEntity<?> deleteContact(@PathVariable int id, Principal principal) {
        if (principal == null) {
            return ResponseEntity.status(401).body("Unauthorized");
        }
        User user = userService.getUserByUsername(principal.getName());
        Contact contact = contactService.getContactById(id);
        if (contact == null || contact.getUser().getId() != user.getId()) {
            return ResponseEntity.status(404).body("Contact not found or not authorized to delete this contact");
        }
        contactService.deleteContact(contact);
        return ResponseEntity.ok("Contact deleted successfully");
    }
}
