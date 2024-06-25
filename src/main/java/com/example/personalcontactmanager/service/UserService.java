package com.example.personalcontactmanager.service;

import com.example.personalcontactmanager.dto.UserDTO;
import com.example.personalcontactmanager.entities.User;

import java.util.List;
import java.util.Optional;

/**
 * Service interface for managing User entities.
 */
public interface UserService {

    /**
     * Gets a user by username.
     *
     * @param username the username
     * @return the user entity
     */
    User getUserByUsername(String username);

    /**
     * Saves a user entity.
     *
     * @param user the user entity to save
     */
    void saveUser(User user);

    /**
     * Registers a new user.
     *
     * @param user the user entity to register
     * @return the registered user entity
     */
    User registerUser(User user);

    /**
     * Finds a user by email.
     *
     * @param email the email
     * @return the user entity
     */
    User findByEmail(String email);


    /**
     * Updates the password of a user.
     *
     * @param user the user entity
     */
    void updatePassword(User user);

    /**
     * Gets a user by ID.
     *
     * @param id the user ID
     * @return an optional containing the user entity if found, or empty otherwise
     */
    Optional<User> getUserById(int id);

    /**
     * Gets all users.
     *
     * @return a list of all user entities
     */
    List<User> getAllUsers();

    /**
     * Finds a user by email and secret question.
     *
     * @param email the email
     * @param secretQuestion the secret question
     * @return the user entity
     */
    User findByEmailAndSecretQuestion(String email, String secretQuestion);

    /**
     * Verifies the secret answer for a user.
     *
     * @param user the user entity
     * @param secretAnswer the secret answer
     * @return true if the secret answer is correct, false otherwise
     */
    boolean verifySecretAnswer(User user, String secretAnswer);

    /**
     * Gets a UserDTO by ID.
     *
     * @param id the user ID
     * @return the UserDTO
     */
    UserDTO getUserDTOById(int id);

    /**
     * Gets a UserDTO by username.
     *
     * @param username the username
     * @return the UserDTO
     */
    UserDTO getUserDTOByUsername(String username);

    /**
     * Registers a new user from a UserDTO.
     *
     * @param userDTO the UserDTO
     * @return the registered UserDTO
     */
    UserDTO registerUserDTO(UserDTO userDTO);

    /**
     * Updates the password of a user from a UserDTO.
     *
     * @param userDTO the UserDTO
     */
    void updatePassword(UserDTO userDTO);

    /**
     * Gets all UserDTOs.
     *
     * @return a list of all UserDTOs
     */
    List<UserDTO> getAllUserDTOs();
}
