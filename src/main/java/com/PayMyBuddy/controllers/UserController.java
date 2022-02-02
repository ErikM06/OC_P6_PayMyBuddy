package com.PayMyBuddy.controllers;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.PayMyBuddy.dto.UserDTO;
import com.PayMyBuddy.exceptions.UserAlreadyExistException;
import com.PayMyBuddy.interfaces.IUserService;
import com.PayMyBuddy.models.User;
import com.PayMyBuddy.services.util.SecurityService;

@Controller
public class UserController {

	private static Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private IUserService IUserService;


	@GetMapping(value = "/register")
	private String getRegister(Model model, @RequestParam(value = "error", required = false) String error) {
		model.addAttribute("user", new UserDTO());
		logger.info("reach /register");
		if (null != error && error.equalsIgnoreCase("true")) {
			model.addAttribute("registerError", "Unable to get /register");
		}
		return "registerPage";
	}

	@PostMapping(value = "/register")
	private ModelAndView userRegistration(@ModelAttribute("user") UserDTO userDto, HttpServletRequest request,
			Errors errors) throws UserAlreadyExistException {
		try {
			User registered = IUserService.registerNewUserAccount(userDto);
			logger.info("reach registration at /register : {}", registered);
		} catch (UserAlreadyExistException e) {
			e.getMessage();
			e.printStackTrace();
			errors.getGlobalErrors();
			return new ModelAndView("ErrorRegister", "error", e.getMessage());

		} catch (Exception e) {
			e.getMessage();
			e.printStackTrace();
			errors.getGlobalErrors();
			return new ModelAndView("ErrorRegister", "error", e.getMessage());
		}
		return new ModelAndView("login", "user", userDto);
	}

	

	

	

}
