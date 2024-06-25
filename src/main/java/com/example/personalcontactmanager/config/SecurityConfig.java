package com.example.personalcontactmanager.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Configuration class for setting up security configurations for the application.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

	/**
	 * Bean for UserDetailsService.
	 *
	 * @return an instance of UserDetailsServiceImpl
	 */
	@Bean
	public UserDetailsService getUserDetailsService() {
		return new UserDetailsServiceImpl();
	}

	/**
	 * Bean for BCryptPasswordEncoder.
	 *
	 * @return an instance of BCryptPasswordEncoder
	 */
	@Bean
	public BCryptPasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	/**
	 * Bean for DaoAuthenticationProvider.
	 *
	 * @return an instance of DaoAuthenticationProvider
	 */
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setUserDetailsService(this.getUserDetailsService());
		daoAuthenticationProvider.setPasswordEncoder(this.getPasswordEncoder());
		return daoAuthenticationProvider;
	}

	/**
	 * Bean for SecurityFilterChain.
	 *
	 * @param http the HttpSecurity configuration
	 * @return the configured SecurityFilterChain
	 * @throws Exception if an error occurs while setting up the security filter chain
	 */
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.disable())
				.authorizeHttpRequests(auth -> auth
						.requestMatchers("/admin/**").hasRole("ADMIN")
						.requestMatchers("/user/**").hasAnyRole("USER", "ADMIN")
						.requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll()
						.requestMatchers("/**").permitAll()
				)
				.formLogin(formLogin -> formLogin
						.loginPage("/signin")
						.loginProcessingUrl("/do-login")
						.defaultSuccessUrl("/user/dashboard")
						.permitAll()
				)
				.httpBasic(withDefaults -> {})
				.authenticationProvider(authenticationProvider());

		return http.build();
	}
}
