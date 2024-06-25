package com.example.personalcontactmanager.service;

import com.example.personalcontactmanager.dto.EventDTO;
import com.example.personalcontactmanager.entities.Event;
import java.util.List;

/**
 * Service interface for managing Event entities.
 */
public interface EventService {

    /**
     * Saves an event entity.
     *
     * @param event the event entity to save
     */
    void saveEvent(Event event);

    /**
     * Finds a list of events by user ID.
     *
     * @param userId the user ID
     * @return a list of events
     */
    List<Event> findEventsByUserId(int userId);

    /**
     * Finds an event by its ID.
     *
     * @param eventId the event ID
     * @return the event entity
     */
    Event findEventById(Long eventId);

    /**
     * Deletes an event by its ID.
     *
     * @param eventId the event ID
     */
    void deleteEvent(Long eventId);

    /**
     * Gets an EventDTO by its ID.
     *
     * @param eventId the event ID
     * @return the EventDTO
     */
    EventDTO getEventDTOById(Long eventId);

    /**
     * Saves an EventDTO and returns the saved event entity.
     *
     * @param eventDTO the EventDTO to save
     * @return the saved event entity
     */
    Event saveEventDTO(EventDTO eventDTO);
}




