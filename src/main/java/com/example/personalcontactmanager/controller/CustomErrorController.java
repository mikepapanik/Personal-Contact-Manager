package com.example.personalcontactmanager.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller class for handling custom error pages.
 */
@Controller
public class CustomErrorController implements ErrorController {

	/**
	 * Handles the error page.
	 *
	 * @return the error view name
	 */
	@GetMapping("/error")
	public String handleError() {
		return "error";
	}
}
