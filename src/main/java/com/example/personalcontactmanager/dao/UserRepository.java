package com.example.personalcontactmanager.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.personalcontactmanager.entities.User;

/**
 * Repository interface for managing User entities.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	/**
	 * Finds a user by their email.
	 *
	 * @param email the email of the user
	 * @return the user with the specified email
	 */
	@Query("select u from User u where u.email = :email")
	User getUserByUserName(@Param("email") String email);

	/**
	 * Finds users whose name contains a specific string.
	 *
	 * @param name the string to search for in user names
	 * @return a list of users whose names contain the specified string
	 */
	 List<User> findByNameContaining(String name);

	/**
	 * Finds all users with pagination support.
	 *
	 * @param pageable the pagination information
	 * @return a page of users
	 */
	@Query("from User u")
	Page<User> findAllUsers(Pageable pageable);

	/**
	 * Finds a user by their email.
	 *
	 * @param email the email of the user
	 * @return the user with the specified email
	 */
	User findByEmail(String email);

	/**
	 * Finds all users with pagination support.
	 *
	 * @param pageable the pagination information
	 * @return a page of users
	 */
	@Query("from User u")
	Page<User> findAll(Pageable pageable);

	/**
	 * Finds a user by their email and secret question.
	 *
	 * @param email the email of the user
	 * @param secretQuestion the secret question of the user
	 * @return the user with the specified email and secret question
	 */
	User findByEmailAndSecretQuestion(String email, String secretQuestion);
}
