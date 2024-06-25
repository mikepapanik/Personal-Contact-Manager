package com.example.personalcontactmanager.mapper;

import com.example.personalcontactmanager.dto.UserDTO;
import com.example.personalcontactmanager.entities.User;
import org.springframework.stereotype.Component;

/**
 * Mapper class for converting between User entities and UserDTO objects.
 */
@Component
public class UserMapper {

    /**
     * Converts a User entity to a UserDTO object.
     *
     * @param user the user entity to convert
     * @return the converted UserDTO object
     */
    public UserDTO toDTO(User user) {
        UserDTO dto = new UserDTO();
        mapCommonFields(user, dto);
        dto.setValidated(user.getValidated() != null ? user.getValidated() : 0);  // Check for null
        return dto;
    }

    /**
     * Converts a UserDTO object to a User entity.
     *
     * @param dto the UserDTO object to convert
     * @return the converted User entity
     */
    public User toEntity(UserDTO dto) {
        User user = new User();
        mapCommonFields(dto, user);
        user.setValidated(dto.getValidated());
        return user;
    }

    /**
     * Maps common fields from a User entity to a UserDTO object.
     *
     * @param user the user entity
     * @param dto the user DTO
     */
    private void mapCommonFields(User user, UserDTO dto) {
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setPassword(user.getPassword());
        dto.setRole(user.getRole());
        dto.setEnabled(user.isEnabled());
        dto.setImageUrl(user.getImageUrl());
        dto.setAbout(user.getAbout());
        dto.setSecretQuestion(user.getSecretQuestion());
        dto.setSecretAnswer(user.getSecretAnswer());
    }

    /**
     * Maps common fields from a UserDTO object to a User entity.
     *
     * @param dto the user DTO
     * @param user the user entity
     */
    private void mapCommonFields(UserDTO dto, User user) {
        user.setId(dto.getId());
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setRole(dto.getRole());
        user.setEnabled(dto.isEnabled());
        user.setImageUrl(dto.getImageUrl());
        user.setAbout(dto.getAbout());
        user.setSecretQuestion(dto.getSecretQuestion());
        user.setSecretAnswer(dto.getSecretAnswer());
    }
}
