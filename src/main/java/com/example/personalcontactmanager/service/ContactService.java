package com.example.personalcontactmanager.service;

import com.example.personalcontactmanager.dto.ContactDTO;
import com.example.personalcontactmanager.entities.Contact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Service interface for managing Contact entities.
 */
public interface ContactService {

    /**
     * Saves a contact entity.
     *
     * @param contact the contact entity to save
     */
    void saveContact(Contact contact);

    /**
     * Finds a page of contacts by user ID.
     *
     * @param userId the user ID
     * @param pageable the pagination information
     * @return a page of contacts
     */
    Page<Contact> findContactsByUserId(int userId, Pageable pageable);

    /**
     * Finds a list of contacts by user ID.
     *
     * @param userId the user ID
     * @return a list of contacts
     */
    List<Contact> findContactsByUserId(int userId);

    /**
     * Gets a contact by its ID.
     *
     * @param id the contact ID
     * @return the contact entity
     */
    Contact getContactById(int id);

    /**
     * Deletes a contact entity.
     *
     * @param contact the contact entity to delete
     */
    void deleteContact(Contact contact);

    /**
     * Gets a ContactDTO by its ID.
     *
     * @param id the contact ID
     * @return the ContactDTO
     */
    ContactDTO getContactDTOById(int id);

    /**
     * Saves a ContactDTO and returns the saved contact entity.
     *
     * @param contactDTO the ContactDTO to save
     * @return the saved contact entity
     */
    Contact saveContactDTO(ContactDTO contactDTO);
}
