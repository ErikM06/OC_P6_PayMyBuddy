package com.PayMyBuddy.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.PayMyBuddy.services.util.SecurityService;

@Controller
public class LoginController {

	@Autowired
	private SecurityService securityService;

	
	@GetMapping(value = "/login")
	private String getLogin(Model model, @RequestParam(value = "error", required = false) String error) {
		if (null != error && error.equalsIgnoreCase("true")) {
			model.addAttribute("loginError", "Unable to Login");
		}
		return "login";

	}

	@PostMapping(value = "/login")
	public String postLogin(@RequestParam(value = "username") String username,
			@RequestParam(value = "password") String password) {

		boolean loginResult = securityService.login(username, password);

		return (loginResult ? "redirect:/users/home" : "redirect:/login?error=true");
	}

}
