package com.example.personalcontactmanager.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Entity class for Contact.
 */
@Entity
@Table(name = "contacts")
public class Contact {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "contact_id")
	private int cid;

	private String name;

	@Column(name = "nick_name")
	private String nickName;

	private String work;

	private String email;

	private String phone;

	private String image;

	@Column(length = 50000)
	private String description;

	@ManyToOne
	@JoinColumn(name = "user_id")
	@JsonIgnore
	private User user;

	@OneToMany(mappedBy = "contact", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnore
	private Set<Event> events = new HashSet<>();

	/**
	 * Default constructor.
	 */
	public Contact() {
		super();
	}

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
	 * Gets the user who owns the contact.
	 *
	 * @return the user who owns the contact
	 */
	public User getUser() {
		return user;
	}

	/**
	 * Sets the user who owns the contact.
	 *
	 * @param user the user who owns the contact
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * Gets the events associated with the contact.
	 *
	 * @return the events associated with the contact
	 */
	public Set<Event> getEvents() {
		return events;
	}

	/**
	 * Sets the events associated with the contact.
	 *
	 * @param events the events associated with the contact
	 */
	public void setEvents(Set<Event> events) {
		this.events = events;
	}

	/**
	 * Returns a string representation of the contact.
	 *
	 * @return a string representation of the contact
	 */
	@Override
	public String toString() {
		return "Contact [cid=" + cid + ", name=" + name + ", nickName=" + nickName + ", work=" + work + ", email="
				+ email + ", phone=" + phone + ", image=" + image + ", description=" + description + "]";
	}
}
