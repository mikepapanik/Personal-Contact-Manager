package com.example.personalcontactmanager.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.personalcontactmanager.dao.UserRepository;
import com.example.personalcontactmanager.entities.User;

/**
 * Implementation of UserDetailsService to load user-specific data.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	/**
	 * Loads the user by username.
	 *
	 * @param username the username identifying the user whose data is required.
	 * @return UserDetails containing user information.
	 * @throws UsernameNotFoundException if the user could not be found or the user has no GrantedAuthority.
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.getUserByUserName(username);
		if (user == null) {
			throw new UsernameNotFoundException("Username not found !!!");
		}
		return new CustomUserDetails(user);
	}
}
