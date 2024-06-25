package com.example.personalcontactmanager.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.example.personalcontactmanager.dao.ContactRepository;
import com.example.personalcontactmanager.dto.ContactDTO;
import com.example.personalcontactmanager.entities.Contact;
import com.example.personalcontactmanager.mapper.ContactMapper;
import com.example.personalcontactmanager.service.impl.ContactServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ContactServiceImplTest {

    @Mock
    private ContactRepository contactRepository;

    @Mock
    private ContactMapper contactMapper;

    @InjectMocks
    private ContactServiceImpl contactService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveContact() {
        Contact contact = new Contact();
        contactService.saveContact(contact);
        verify(contactRepository, times(1)).save(contact);
    }

    @Test
    public void testFindContactsByUserId() {
        List<Contact> contacts = Arrays.asList(new Contact(), new Contact());
        when(contactRepository.findContactsByUserId(1)).thenReturn(contacts);

        List<Contact> result = contactService.findContactsByUserId(1);
        assertEquals(2, result.size());
    }

    @Test
    public void testGetContactById() {
        Contact contact = new Contact();
        contact.setCid(1);

        when(contactRepository.findById(1)).thenReturn(Optional.of(contact));

        Contact result = contactService.getContactById(1);
        assertNotNull(result);
        assertEquals(1, result.getCid());
    }

    @Test
    public void testDeleteContact() {
        Contact contact = new Contact();
        contactService.deleteContact(contact);
        verify(contactRepository, times(1)).delete(contact);
    }

    @Test
    public void testGetContactDTOById() {
        Contact contact = new Contact();
        contact.setCid(1);

        when(contactRepository.findById(1)).thenReturn(Optional.of(contact));
        when(contactMapper.toDTO(contact)).thenReturn(new ContactDTO());

        ContactDTO result = contactService.getContactDTOById(1);
        assertNotNull(result);
    }

    @Test
    public void testSaveContactDTO() {
        ContactDTO contactDTO = new ContactDTO();
        Contact contact = new Contact();

        when(contactMapper.toEntity(contactDTO)).thenReturn(contact);
        when(contactRepository.save(contact)).thenReturn(contact);

        Contact result = contactService.saveContactDTO(contactDTO);
        assertNotNull(result);
    }
}

