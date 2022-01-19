package com.PayMyBuddy.controllers;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.PayMyBuddy.dto.ConnectionDTO;
import com.PayMyBuddy.dto.UserDTO;
import com.PayMyBuddy.exceptions.UserAlreadyExistException;
import com.PayMyBuddy.models.User;
import com.PayMyBuddy.services.IUserService;
import com.PayMyBuddy.services.util.SecurityService;
import com.PayMyBuddy.services.IConnectionService;

@Controller
public class UserController {

	private static Logger logger = LoggerFactory.getLogger(UserController.class);


	@Autowired
	private IUserService IUserService;

	@Autowired
	private SecurityService securityService;

	@Autowired
	private IConnectionService IConnectionService;

	@GetMapping(value = "/admin/delete_user")
	private void deleteUserByAdmin(@RequestParam (value ="user") String username) {
		logger.info("in /admin/delete_user?user=");
		IUserService.deleteUser(username);
		
	}

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

	@GetMapping(value = "/")
	private String getIndex() {
		return "index";
	}

	@GetMapping(value = "/register")
	private String getRegister(Model model, @RequestParam(value = "error", required = false) String error) {
		model.addAttribute("user", new UserDTO());
		logger.info("reach /register");
		if (null != error && error.equalsIgnoreCase("true")) {
			model.addAttribute("registerError", "Unable to register");
		}
		return "signup";
	}

	@PostMapping(value = "/register")
	private ModelAndView userRegistration(@ModelAttribute("user") UserDTO userDto, HttpServletRequest request,
			Errors errors) {
		try {
			User registered = IUserService.registerNewUserAccount(userDto);
			logger.info("reach registration at /register : {}", registered);
		} catch (UserAlreadyExistException e) {
			logger.error(e.getMessage());
			return new ModelAndView("ErrorRegister", "error", e.getMessage());

		} catch (Exception e) {
			logger.error(e.getMessage());
			return new ModelAndView("ErrorRegister", "error", e.getMessage());
		}
		return new ModelAndView("successRegister", "user", userDto);
	}
	
	@GetMapping ("/user/add_connection")
	private ModelAndView getAddConnection (Model model, @RequestParam (value ="error", required = false) String error) {
		model.addAttribute("connection", new ConnectionDTO());
		return new ModelAndView("addConnection");
		
	}
	
	@PostMapping("/user/add_connection")
	private ModelAndView addConnection(@ModelAttribute("connection") ConnectionDTO connectionDto,
			HttpServletRequest request, Errors errors) {
		try {
			IConnectionService.addConnections(connectionDto);
		} catch (UsernameNotFoundException e) {
			logger.error(e.getMessage());
		}
		return new ModelAndView("home", "connection", connectionDto);

	}

	@DeleteMapping(value = "/user/delete_connection")
	private ModelAndView deleteConnection(@ModelAttribute("connection") String connection, HttpServletRequest request,
			Errors errors) {
		try {
		IConnectionService.deleteConnection(connection);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return new ModelAndView("connectionDeleted", "connection", connection);

	}

	@GetMapping(value = "/user/home")
	public String getUserHome() {

		return "home";
	}

}
