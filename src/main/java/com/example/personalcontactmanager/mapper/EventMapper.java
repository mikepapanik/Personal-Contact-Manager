package com.example.personalcontactmanager.mapper;

import com.example.personalcontactmanager.dto.EventDTO;
import com.example.personalcontactmanager.entities.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Mapper class for converting between Event entities and EventDTO objects.
 */
@Component
public class EventMapper {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ContactMapper contactMapper;

    /**
     * Converts an Event entity to an EventDTO object.
     *
     * @param event the event entity to convert
     * @return the converted EventDTO object
     */
    public EventDTO toDTO(Event event) {
        EventDTO dto = new EventDTO();
        dto.setId(event.getId());
        dto.setTitle(event.getTitle());
        dto.setStart(event.getStart());
        dto.setNote(event.getNote());
        dto.setUserId(event.getUser().getId());
        dto.setContactId(event.getContact().getCid());
        return dto;
    }

    /**
     * Converts an EventDTO object to an Event entity.
     *
     * @param dto the EventDTO object to convert
     * @return the converted Event entity
     */
    public Event toEntity(EventDTO dto) {
        Event event = new Event();
        event.setId(dto.getId());
        event.setTitle(dto.getTitle());
        event.setStart(dto.getStart());
        event.setNote(dto.getNote());
        return event;
    }
}
