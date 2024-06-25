package com.example.personalcontactmanager.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.personalcontactmanager.dao.UserRepository;
import com.example.personalcontactmanager.entities.User;

/**
 * Configuration class for setting up the admin user.
 */
@Configuration
public class AdminUserSetup {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    /**
     * Creates the admin user if it does not already exist in the database.
     *
     * @return CommandLineRunner to execute the user creation logic at application startup.
     */
    @Bean
    public CommandLineRunner createAdminUser() {
        return args -> {
            if (userRepository.findByEmail("admin@example.com") == null) {
                User admin = new User();
                admin.setUsername("admin");
                admin.setEmail("admin@example.com");
                admin.setName("Administrator");
                admin.setPassword(passwordEncoder.encode("Mp_741258963"));
                admin.setRole("ROLE_ADMIN");
                admin.setEnabled(true);
                userRepository.save(admin);
            }
        };
    }
}
