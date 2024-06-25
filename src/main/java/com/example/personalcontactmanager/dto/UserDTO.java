package com.example.personalcontactmanager.dto;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Data Transfer Object for User.
 */
@Schema(description = "Data Transfer Object for User")
public class UserDTO {

    @Schema(description = "ID of the user", example = "1")
    private int id;

    @Schema(description = "Name of the user", example = "Michail Papanikolas")
    private String name;

    @Schema(description = "Email of the user", example = "michail.papanikolas@example.com")
    private String email;

    @Schema(description = "Password of the user", example = "P@ssw0rd!")
    private String password;

    @Schema(description = "Role of the user", example = "ROLE_USER")
    private String role;

    @Schema(description = "Whether the user is enabled", example = "true")
    private boolean enabled;

    @Schema(description = "Image URL of the user", example = "profile.jpg")
    private String imageUrl;

    @Schema(description = "About the user", example = "Software Engineer at XYZ company")
    private String about;

    @Schema(description = "Validation status of the user", example = "1")
    private int validated;

    @Schema(description = "Secret question for password recovery", example = "What is your pet's name?")
    private String secretQuestion;

    @Schema(description = "Answer to the secret question", example = "Fluffy")
    private String secretAnswer;

    /**
     * Gets the user ID.
     *
     * @return the user ID
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the user ID.
     *
     * @param id the user ID
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the name of the user.
     *
     * @return the name of the user
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the user.
     *
     * @param name the name of the user
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the email of the user.
     *
     * @return the email of the user
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email of the user.
     *
     * @param email the email of the user
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the password of the user.
     *
     * @return the password of the user
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password of the user.
     *
     * @param password the password of the user
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the role of the user.
     *
     * @return the role of the user
     */
    public String getRole() {
        return role;
    }

    /**
     * Sets the role of the user.
     *
     * @param role the role of the user
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * Checks if the user is enabled.
     *
     * @return true if the user is enabled, false otherwise
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * Sets whether the user is enabled.
     *
     * @param enabled true if the user is enabled, false otherwise
     */
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * Gets the image URL of the user.
     *
     * @return the image URL of the user
     */
    public String getImageUrl() {
        return imageUrl;
    }

    /**
     * Sets the image URL of the user.
     *
     * @param imageUrl the image URL of the user
     */
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    /**
     * Gets the about information of the user.
     *
     * @return the about information of the user
     */
    public String getAbout() {
        return about;
    }

    /**
     * Sets the about information of the user.
     *
     * @param about the about information of the user
     */
    public void setAbout(String about) {
        this.about = about;
    }

    /**
     * Gets the validation status of the user.
     *
     * @return the validation status of the user
     */
    public int getValidated() {
        return validated;
    }

    /**
     * Sets the validation status of the user.
     *
     * @param validated the validation status of the user
     */
    public void setValidated(int validated) {
        this.validated = validated;
    }

    /**
     * Gets the secret question for password recovery.
     *
     * @return the secret question for password recovery
     */
    public String getSecretQuestion() {
        return secretQuestion;
    }

    /**
     * Sets the secret question for password recovery.
     *
     * @param secretQuestion the secret question for password recovery
     */
    public void setSecretQuestion(String secretQuestion) {
        this.secretQuestion = secretQuestion;
    }

    /**
     * Gets the answer to the secret question.
     *
     * @return the answer to the secret question
     */
    public String getSecretAnswer() {
        return secretAnswer;
    }

    /**
     * Sets the answer to the secret question.
     *
     * @param secretAnswer the answer to the secret question
     */
    public void setSecretAnswer(String secretAnswer) {
        this.secretAnswer = secretAnswer;
    }
}
