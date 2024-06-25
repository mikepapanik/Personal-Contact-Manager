package com.example.personalcontactmanager.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.Date;

/**
 * Entity class for Event.
 */
@Entity
@Table(name = "events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    private Date start;

    private String note;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    @ManyToOne
    @JoinColumn(name = "contact_id")
    private Contact contact;

    /**
     * Gets the event ID.
     *
     * @return the event ID
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the event ID.
     *
     * @param id the event ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the title of the event.
     *
     * @return the title of the event
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the event.
     *
     * @param title the title of the event
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the start date and time of the event.
     *
     * @return the start date and time of the event
     */
    public Date getStart() {
        return start;
    }

    /**
     * Sets the start date and time of the event.
     *
     * @param start the start date and time of the event
     */
    public void setStart(Date start) {
        this.start = start;
    }

    /**
     * Gets the contact name related to the event.
     *
     * @return the contact name related to the event
     */
    public String getContactName() {
        return contact != null ? contact.getName() : "";
    }

    /**
     * Gets the note for the event.
     *
     * @return the note for the event
     */
    public String getNote() {
        return note;
    }

    /**
     * Sets the note for the event.
     *
     * @param note the note for the event
     */
    public void setNote(String note) {
        this.note = note;
    }

    /**
     * Gets the user who owns the event.
     *
     * @return the user who owns the event
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets the user who owns the event.
     *
     * @param user the user who owns the event
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Gets the contact related to the event.
     *
     * @return the contact related to the event
     */
    public Contact getContact() {
        return contact;
    }

    /**
     * Sets the contact related to the event.
     *
     * @param contact the contact related to the event
     */
    public void setContact(Contact contact) {
        this.contact = contact;
    }
}
