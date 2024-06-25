package com.example.personalcontactmanager.helper;

/**
 * A class representing a message with content and type.
 */
public class Message {

	private String content;
	private String type;

	/**
	 * Constructs a new Message with the specified content and type.
	 *
	 * @param content the content of the message
	 * @param type the type of the message
	 */
	public Message(String content, String type) {
		super();
		this.content = content;
		this.type = type;
	}

	/**
	 * Default constructor.
	 */
	public Message() {
		super();
	}

	/**
	 * Gets the content of the message.
	 *
	 * @return the content of the message
	 */
	public String getContent() {
		return content;
	}

	/**
	 * Sets the content of the message.
	 *
	 * @param content the content of the message
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * Gets the type of the message.
	 *
	 * @return the type of the message
	 */
	public String getType() {
		return type;
	}

	/**
	 * Sets the type of the message.
	 *
	 * @param type the type of the message
	 */
	public void setType(String type) {
		this.type = type;
	}
}
