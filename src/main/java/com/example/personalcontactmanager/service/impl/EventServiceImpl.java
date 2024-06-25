package com.example.personalcontactmanager.service.impl;

import com.example.personalcontactmanager.dao.EventRepository;
import com.example.personalcontactmanager.dto.EventDTO;
import com.example.personalcontactmanager.entities.Event;
import com.example.personalcontactmanager.mapper.EventMapper;
import com.example.personalcontactmanager.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementation of the EventService interface for managing Event entities.
 */
@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private EventMapper eventMapper;

    /**
     * Saves an event entity.
     *
     * @param event the event entity to save
     */
    @Override
    public void saveEvent(Event event) {
        eventRepository.save(event);
    }

    /**
     * Finds a list of events by user ID.
     *
     * @param userId the user ID
     * @return a list of events
     */
    @Override
    public List<Event> findEventsByUserId(int userId) {
        return eventRepository.findByUserId(userId);
    }

    /**
     * Finds an event by its ID.
     *
     * @param eventId the event ID
     * @return the event entity
     */
    @Override
    public Event findEventById(Long eventId) {
        return eventRepository.findById(eventId).orElse(null);
    }

    /**
     * Deletes an event by its ID.
     *
     * @param eventId the event ID
     */
    @Override
    public void deleteEvent(Long eventId) {
        eventRepository.deleteById(eventId);
    }

    /**
     * Gets an EventDTO by its ID.
     *
     * @param eventId the event ID
     * @return the EventDTO
     */
    @Override
    public EventDTO getEventDTOById(Long eventId) {
        Event event = findEventById(eventId);
        return event != null ? eventMapper.toDTO(event) : null;
    }

    /**
     * Saves an EventDTO and returns the saved event entity.
     *
     * @param eventDTO the EventDTO to save
     * @return the saved event entity
     */
    @Override
    public Event saveEventDTO(EventDTO eventDTO) {
        Event event = eventMapper.toEntity(eventDTO);
        return eventRepository.save(event);
    }
}
