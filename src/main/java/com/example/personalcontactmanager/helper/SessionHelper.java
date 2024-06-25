package com.example.personalcontactmanager.helper;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.servlet.http.HttpSession;

/**
 * Helper class for managing session attributes.
 */
@Component
public class SessionHelper {

	/**
	 * Removes the "message" attribute from the current session.
	 */
	public void removeMessageFromSession() {
		try {
			HttpSession session = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession();
			session.removeAttribute("message");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Removes the "validation" attribute from the current session.
	 */
	public void removeValidationFromSession() {
		try {
			HttpSession session = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession();
			session.removeAttribute("validation");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}

