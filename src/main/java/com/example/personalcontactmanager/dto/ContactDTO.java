package com.example.personalcontactmanager.dto;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Data Transfer Object for Contact.
 */
@Schema(description = "Data Transfer Object for Contact")
public class ContactDTO {

    @Schema(description = "ID of the contact", example = "1")
    private int cid;

    @Schema(description = "Name of the contact", example = "Michail Papanikolas")
    private String name;

    @Schema(description = "Nickname of the contact", example = "Michail")
    private String nickName;

    @Schema(description = "Work of the contact", example = "Software Engineer")
    private String work;

    @Schema(description = "Email of the contact", example = "michail.papanikolas@example.com")
    private String email;

    @Schema(description = "Phone number of the contact", example = "+123456789")
    private String phone;

    @Schema(description = "Image URL of the contact", example = "profile.jpg")
    private String image;

    @Schema(description = "Description of the contact", example = "Friend from college")
    private String description;

    @Schema(description = "User ID of the contact owner", example = "1")
    private int userId;

    /**
     * Gets the contact ID.
     *
     * @return the contact ID
     */
    public int getCid() {
        return cid;
    }

    /**
     * Sets the contact ID.
     *
     * @param cid the contact ID
     */
    public void setCid(int cid) {
        this.cid = cid;
    }

    /**
     * Gets the name of the contact.
     *
     * @return the name of the contact
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the contact.
     *
     * @param name the name of the contact
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the nickname of the contact.
     *
     * @return the nickname of the contact
     */
    public String getNickName() {
        return nickName;
    }

    /**
     * Sets the nickname of the contact.
     *
     * @param nickName the nickname of the contact
     */
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    /**
     * Gets the work of the contact.
     *
     * @return the work of the contact
     */
    public String getWork() {
        return work;
    }

    /**
     * Sets the work of the contact.
     *
     * @param work the work of the contact
     */
    public void setWork(String work) {
        this.work = work;
    }

    /**
     * Gets the email of the contact.
     *
     * @return the email of the contact
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email of the contact.
     *
     * @param email the email of the contact
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the phone number of the contact.
     *
     * @return the phone number of the contact
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Sets the phone number of the contact.
     *
     * @param phone the phone number of the contact
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Gets the image URL of the contact.
     *
     * @return the image URL of the contact
     */
    public String getImage() {
        return image;
    }

    /**
     * Sets the image URL of the contact.
     *
     * @param image the image URL of the contact
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * Gets the description of the contact.
     *
     * @return the description of the contact
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the contact.
     *
     * @param description the description of the contact
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the user ID of the contact owner.
     *
     * @return the user ID of the contact owner
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Sets the user ID of the contact owner.
     *
     * @param userId the user ID of the contact owner
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }
}
