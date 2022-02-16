package com.PayMyBuddy.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

	@GetMapping(value = "/login")
	private String getLogin(Model model, @RequestParam(value = "error", required = false) String error) {
		if (null != error && error.equalsIgnoreCase("true")) {
			model.addAttribute("error", "Unable to Login");
		}
		return "login";

	}

}
