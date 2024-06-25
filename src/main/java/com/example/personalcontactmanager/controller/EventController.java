package com.example.personalcontactmanager.controller;

import com.example.personalcontactmanager.dto.EventDTO;
import com.example.personalcontactmanager.entities.Contact;
import com.example.personalcontactmanager.entities.Event;
import com.example.personalcontactmanager.entities.User;
import com.example.personalcontactmanager.mapper.EventMapper;
import com.example.personalcontactmanager.service.ContactService;
import com.example.personalcontactmanager.service.EventService;
import com.example.personalcontactmanager.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Controller class for managing event-related operations.
 */
@RestController
@RequestMapping("/api/events")
@Tag(name = "Event", description = "The Event API")
public class EventController {

    @Autowired
    private EventService eventService;

    @Autowired
    private UserService userService;

    @Autowired
    private ContactService contactService;

    @Autowired
    private EventMapper eventMapper;

    /**
     * Adds a new event for a contact.
     *
     * @param eventDTO the DTO of the event to be added
     * @param contactId the ID of the contact associated with the event
     * @param principal the principal of the authenticated user
     * @return a message indicating the result of the operation
     */
    @PostMapping("/add")
    @Operation(
            summary = "Add an event",
            description = "Add a new event for a contact",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Event added successfully"),
                    @ApiResponse(responseCode = "400", description = "Invalid input"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized"),
                    @ApiResponse(responseCode = "403", description = "Forbidden")
            }
    )
    public String addEvent(@RequestBody EventDTO eventDTO, @RequestParam Long contactId, Principal principal) {
        if (principal == null) {
            return "Unauthorized";
        }
        User user = userService.getUserByUsername(principal.getName());
        Event event = eventMapper.toEntity(eventDTO);
        event.setUser(user);

        Contact contact = contactService.getContactById(contactId.intValue());
        event.setContact(contact);

        eventService.saveEvent(event);
        return "Event added successfully";
    }

    /**
     * Retrieves a list of events for the authenticated user.
     *
     * @param principal the principal of the authenticated user
     * @return a list of EventDTOs
     */
    @GetMapping("/user")
    @Operation(
            summary = "Get user events",
            description = "Retrieve a list of events for the authenticated user",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully retrieved list of events",
                            content = @Content(array = @ArraySchema(schema = @Schema(implementation = EventDTO.class)))),
                    @ApiResponse(responseCode = "401", description = "Unauthorized"),
                    @ApiResponse(responseCode = "403", description = "Forbidden"),
                    @ApiResponse(responseCode = "404", description = "Not Found")
            }
    )
    public List<EventDTO> getUserEvents(Principal principal) {
        if (principal == null) {
            return null; // Handle this case as needed
        }
        User user = userService.getUserByUsername(principal.getName());
        return eventService.findEventsByUserId(user.getId()).stream()
                .map(event -> {
                    EventDTO dto = eventMapper.toDTO(event);
                    dto.setContactName(event.getContact().getName());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    /**
     * Updates an existing event.
     *
     * @param id the ID of the event to be updated
     * @param eventDetails the details of the event to be updated
     * @param contactId the ID of the contact associated with the event
     * @param principal the principal of the authenticated user
     * @return a message indicating the result of the operation
     */
    @PutMapping("/update/{id}")
    @Operation(
            summary = "Update an event",
            description = "Update an existing event",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Event updated successfully"),
                    @ApiResponse(responseCode = "400", description = "Invalid input"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized"),
                    @ApiResponse(responseCode = "403", description = "Forbidden"),
                    @ApiResponse(responseCode = "404", description = "Event not found")
            }
    )
    public String updateEvent(@PathVariable Long id, @RequestBody EventDTO eventDetails, @RequestParam Long contactId, Principal principal) {
        if (principal == null) {
            return "Unauthorized";
        }
        Event event = eventService.findEventById(id);
        if (event == null || !event.getUser().getEmail().equals(principal.getName())) {
            return "Event not found or you are not authorized to update this event";
        }

        Contact contact = contactService.getContactById(contactId.intValue());

        event.setTitle(eventDetails.getTitle());
        event.setStart(eventDetails.getStart());
        event.setContact(contact);
        event.setNote(eventDetails.getNote());

        eventService.saveEvent(event);
        return "Event updated successfully";
    }

    /**
     * Deletes an existing event.
     *
     * @param id the ID of the event to be deleted
     * @param principal the principal of the authenticated user
     * @return a message indicating the result of the operation
     */
    @DeleteMapping("/delete/{id}")
    @Operation(
            summary = "Delete an event",
            description = "Delete an existing event",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Event deleted successfully"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized"),
                    @ApiResponse(responseCode = "403", description = "Forbidden"),
                    @ApiResponse(responseCode = "404", description = "Event not found")
            }
    )
    public String deleteEvent(@PathVariable Long id, Principal principal) {
        if (principal == null) {
            return "Unauthorized";
        }
        Event event = eventService.findEventById(id);
        if (event == null || !event.getUser().getEmail().equals(principal.getName())) {
            return "Event not found or you are not authorized to delete this event";
        }

        eventService.deleteEvent(id);
        return "Event deleted successfully";
    }
}
