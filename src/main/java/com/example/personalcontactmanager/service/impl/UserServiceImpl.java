package com.example.personalcontactmanager.service.impl;

import com.example.personalcontactmanager.dao.UserRepository;
import com.example.personalcontactmanager.dto.UserDTO;
import com.example.personalcontactmanager.entities.User;
import com.example.personalcontactmanager.mapper.UserMapper;
import com.example.personalcontactmanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Implementation of the UserService interface for managing User entities.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserMapper userMapper;

    /**
     * Gets a user by username.
     *
     * @param username the username
     * @return the user entity
     */
    @Override
    public User getUserByUsername(String username) {
        return userRepository.getUserByUserName(username);
    }

    /**
     * Saves a user entity.
     *
     * @param user the user entity to save
     */
    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    /**
     * Registers a new user.
     *
     * @param user the user entity to register
     * @return the registered user entity
     */
    @Override
    public User registerUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setSecretAnswer(passwordEncoder.encode(user.getSecretAnswer())); // Encrypting the secretAnswer
        return userRepository.save(user);
    }

    /**
     * Finds a user by email.
     *
     * @param email the email
     * @return the user entity
     */
    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    /**
     * Updates the password of a user.
     *
     * @param user the user entity
     */
    @Override
    public void updatePassword(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    /**
     * Gets a user by ID.
     *
     * @param id the user ID
     * @return an optional containing the user entity if found, or empty otherwise
     */
    @Override
    public Optional<User> getUserById(int id) {
        return userRepository.findById(id);
    }

    /**
     * Gets all users.
     *
     * @return a list of all user entities
     */
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Finds a user by email and secret question.
     *
     * @param email the email
     * @param secretQuestion the secret question
     * @return the user entity
     */
    @Override
    public User findByEmailAndSecretQuestion(String email, String secretQuestion) {
        return userRepository.findByEmailAndSecretQuestion(email, secretQuestion);
    }

    /**
     * Verifies the secret answer for a user.
     *
     * @param user the user entity
     * @param secretAnswer the secret answer
     * @return true if the secret answer is correct, false otherwise
     */
    @Override
    public boolean verifySecretAnswer(User user, String secretAnswer) {
        return passwordEncoder.matches(secretAnswer, user.getSecretAnswer()); // Using BCryptPasswordEncoder
    }

    /**
     * Gets a UserDTO by ID.
     *
     * @param id the user ID
     * @return the UserDTO
     */
    @Override
    public UserDTO getUserDTOById(int id) {
        return userRepository.findById(id)
                .map(userMapper::toDTO)
                .orElse(null);
    }

    /**
     * Gets a UserDTO by username.
     *
     * @param username the username
     * @return the UserDTO
     */
    @Override
    public UserDTO getUserDTOByUsername(String username) {
        User user = getUserByUsername(username);
        return user != null ? userMapper.toDTO(user) : null;
    }

    /**
     * Registers a new user from a UserDTO.
     *
     * @param userDTO the UserDTO
     * @return the registered UserDTO
     */
    @Override
    public UserDTO registerUserDTO(UserDTO userDTO) {
        User user = userMapper.toEntity(userDTO);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setSecretAnswer(passwordEncoder.encode(user.getSecretAnswer())); // Encrypting the secretAnswer
        return userMapper.toDTO(userRepository.save(user));
    }

    /**
     * Updates the password of a user from a UserDTO.
     *
     * @param userDTO the UserDTO
     */
    @Override
    public void updatePassword(UserDTO userDTO) {
        User user = userMapper.toEntity(userDTO);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    /**
     * Gets all UserDTOs.
     *
     * @return a list of all UserDTOs
     */
    @Override
    public List<UserDTO> getAllUserDTOs() {
        return userRepository.findAll().stream()
                .map(userMapper::toDTO)
                .collect(Collectors.toList());
    }
}
