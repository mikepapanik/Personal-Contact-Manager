package com.example.personalcontactmanager.dao;

import com.example.personalcontactmanager.entities.Contact;
import com.example.personalcontactmanager.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

/**
 * Repository interface for managing Contact entities.
 */
public interface ContactRepository extends JpaRepository<Contact, Integer> {

	/**
	 * Finds a page of contacts for a specific user by user ID.
	 *
	 * @param userId the ID of the user
	 * @param pageable the pagination information
	 * @return a page of contacts
	 */
	@Query("from Contact c where c.user.id = :userId")
	Page<Contact> findContactsByUserId(@Param("userId") int userId, Pageable pageable);

	/**
	 * Finds a list of contacts for a specific user by user ID.
	 *
	 * @param userId the ID of the user
	 * @return a list of contacts
	 */
	@Query("from Contact c where c.user.id = :userId")
	List<Contact> findContactsByUserId(@Param("userId") int userId);

	/**
	 * Finds contacts by name containing a specific string for a specific user.
	 *
	 * @param name the name to search for
	 * @param user the user to search contacts for
	 * @return a list of contacts
	 */
	List<Contact> findByNameContainingAndUser(String name, User user);

	/**
	 * Finds contacts for a specific user.
	 *
	 * @param user the user to search contacts for
	 * @return a list of contacts
	 */
	List<Contact> findByUser(User user);
}
