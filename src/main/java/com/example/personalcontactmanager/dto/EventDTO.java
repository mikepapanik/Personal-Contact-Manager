package com.example.personalcontactmanager.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Date;

/**
 * Data Transfer Object for Event.
 */
@Schema(description = "Data Transfer Object for Event")
public class EventDTO {

    @Schema(description = "ID of the event", example = "1")
    private Long id;

    @Schema(description = "Title of the event", example = "Meeting with Michail Papanikolas")
    private String title;

    @Schema(description = "Start date and time of the event", example = "2023-12-31T23:59:59")
    private Date start;

    @Schema(description = "Note for the event", example = "Discuss project details")
    private String note;

    @Schema(description = "User ID of the event owner", example = "1")
    private int userId;

    @Schema(description = "Contact ID related to the event", example = "1")
    private int contactId;

    @Schema(description = "Contact name related to the event", example = "Michail Papanikolas")
    private String contactName;

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
     * Gets the user ID of the event owner.
     *
     * @return the user ID of the event owner
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Sets the user ID of the event owner.
     *
     * @param userId the user ID of the event owner
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Gets the contact ID related to the event.
     *
     * @return the contact ID related to the event
     */
    public int getContactId() {
        return contactId;
    }

    /**
     * Sets the contact ID related to the event.
     *
     * @param contactId the contact ID related to the event
     */
    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    /**
     * Gets the contact name related to the event.
     *
     * @return the contact name related to the event
     */
    public String getContactName() {
        return contactName;
    }

    /**
     * Sets the contact name related to the event.
     *
     * @param contactName the contact name related to the event
     */
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }
}
