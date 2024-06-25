package com.example.personalcontactmanager.service.impl;

import com.example.personalcontactmanager.dao.ContactRepository;
import com.example.personalcontactmanager.dto.ContactDTO;
import com.example.personalcontactmanager.entities.Contact;
import com.example.personalcontactmanager.entities.Event;
import com.example.personalcontactmanager.mapper.ContactMapper;
import com.example.personalcontactmanager.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * Implementation of the ContactService interface for managing Contact entities.
 */
@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private ContactMapper contactMapper;

    /**
     * Saves a contact entity.
     *
     * @param contact the contact entity to save
     */
    @Override
    public void saveContact(Contact contact) {
        if (contact.getCid() != 0) {
            Contact existingContact = contactRepository.findById(contact.getCid()).orElse(null);
            if (existingContact != null) {
                existingContact.setName(contact.getName());
                existingContact.setNickName(contact.getNickName());
                existingContact.setWork(contact.getWork());
                existingContact.setEmail(contact.getEmail());
                existingContact.setPhone(contact.getPhone());
                existingContact.setImage(contact.getImage());
                existingContact.setDescription(contact.getDescription());

                // Preserve existing events without clearing them
                Set<Event> existingEvents = existingContact.getEvents();
                contact.setEvents(existingEvents);

                contact = existingContact;
            }
        }
        contactRepository.save(contact);
    }

    /**
     * Finds a page of contacts by user ID.
     *
     * @param userId the user ID
     * @param pageable the pagination information
     * @return a page of contacts
     */
    @Override
    public Page<Contact> findContactsByUserId(int userId, Pageable pageable) {
        return contactRepository.findContactsByUserId(userId, pageable);
    }

    /**
     * Finds a list of contacts by user ID.
     *
     * @param userId the user ID
     * @return a list of contacts
     */
    @Override
    public List<Contact> findContactsByUserId(int userId) {
        return contactRepository.findContactsByUserId(userId);
    }

    /**
     * Gets a contact by its ID.
     *
     * @param id the contact ID
     * @return the contact entity
     */
    @Override
    public Contact getContactById(int id) {
        return contactRepository.findById(id).orElse(null);
    }

    /**
     * Deletes a contact entity.
     *
     * @param contact the contact entity to delete
     */
    @Override
    public void deleteContact(Contact contact) {
        contactRepository.delete(contact);
    }

    /**
     * Gets a ContactDTO by its ID.
     *
     * @param id the contact ID
     * @return the ContactDTO
     */
    @Override
    public ContactDTO getContactDTOById(int id) {
        Contact contact = getContactById(id);
        return contact != null ? contactMapper.toDTO(contact) : null;
    }

    /**
     * Saves a ContactDTO and returns the saved contact entity.
     *
     * @param contactDTO the ContactDTO to save
     * @return the saved contact entity
     */
    @Override
    public Contact saveContactDTO(ContactDTO contactDTO) {
        Contact contact = contactMapper.toEntity(contactDTO);
        return contactRepository.save(contact);
    }
}
