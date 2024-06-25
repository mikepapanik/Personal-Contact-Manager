package com.example.personalcontactmanager.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

/**
 * Entity class for User.
 */
@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id")
	private int id;

	@NotBlank(message = "Name field is required")
	@Size(min = 2, max = 20, message = "minimum 2 and maximum 20 characters are allowed !!!")
	private String name;

	@Column(unique = true)
	@NotBlank(message = "Email field is required !!!")
	@Email(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$", message = "Invalid Email !!!")
	private String email;

	@NotBlank(message = "Password should not be empty !!!")
	@Size(min = 8, message = "Password should be atleast 8 characters long !!!")
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[^a-zA-Z\\d]).{8,}$", message = "Password should be strong !!!")
	private String password;

	private String role;

	private boolean enabled;

	@Column(name = "image_url")
	private String imageUrl;

	@Column(length = 500)
	private String about;

	private Integer validated;

	@Column(name = "secret_question")
	private String secretQuestion;

	@Column(name = "secret_answer")
	private String secretAnswer;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
	private List<Contact> contacts = new ArrayList<>();

	@Column(unique = true)
	private String username;

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
	public Integer getValidated() {
		return validated;
	}

	/**
	 * Sets the validation status of the user.
	 *
	 * @param validated the validation status of the user
	 */
	public void setValidated(Integer validated) {
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

	/**
	 * Gets the list of contacts associated with the user.
	 *
	 * @return the list of contacts
	 */
	public List<Contact> getContacts() {
		return contacts;
	}

	/**
	 * Sets the list of contacts associated with the user.
	 *
	 * @param contacts the list of contacts
	 */
	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}

	/**
	 * Gets the username of the user.
	 *
	 * @return the username of the user
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Sets the username of the user.
	 *
	 * @param username the username of the user
	 */
	public void setUsername(String username) {
		this.username = username;
	}
}
