package com.example.personalcontactmanager.mapper;

import com.example.personalcontactmanager.dto.ContactDTO;
import com.example.personalcontactmanager.entities.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Mapper class for converting between Contact entities and ContactDTO objects.
 */
@Component
public class ContactMapper {

    @Autowired
    private UserMapper userMapper;

    /**
     * Converts a Contact entity to a ContactDTO object.
     *
     * @param contact the contact entity to convert
     * @return the converted ContactDTO object
     */
    public ContactDTO toDTO(Contact contact) {
        ContactDTO dto = new ContactDTO();
        dto.setCid(contact.getCid());
        dto.setName(contact.getName());
        dto.setNickName(contact.getNickName());
        dto.setWork(contact.getWork());
        dto.setEmail(contact.getEmail());
        dto.setPhone(contact.getPhone());
        dto.setImage(contact.getImage());
        dto.setDescription(contact.getDescription());
        dto.setUserId(contact.getUser().getId());
        return dto;
    }

    /**
     * Converts a ContactDTO object to a Contact entity.
     *
     * @param dto the ContactDTO object to convert
     * @return the converted Contact entity
     */
    public Contact toEntity(ContactDTO dto) {
        Contact contact = new Contact();
        contact.setCid(dto.getCid());
        contact.setName(dto.getName());
        contact.setNickName(dto.getNickName());
        contact.setWork(dto.getWork());
        contact.setEmail(dto.getEmail());
        contact.setPhone(dto.getPhone());
        contact.setImage(dto.getImage());
        contact.setDescription(dto.getDescription());
        return contact;
    }
}
