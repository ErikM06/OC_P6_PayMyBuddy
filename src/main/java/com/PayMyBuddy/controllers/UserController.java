package com.PayMyBuddy.controllers;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import com.PayMyBuddy.dto.ConnectionDTO;
import com.PayMyBuddy.dto.UserDTO;
import com.PayMyBuddy.models.Balance;
import com.PayMyBuddy.models.User;
import com.PayMyBuddy.repo.UserRepository;
import com.PayMyBuddy.services.BalanceService;
import com.PayMyBuddy.services.UserService;
import com.PayMyBuddy.services.util.SecurityService;

@Controller
public class UserController {

	private static Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private UserService userService;

	@Autowired
	private BalanceService balanceService;

	@Autowired
	private SecurityService securityService;

	/*
	 * @Autowired PasswordEncoder passwordEncoder;
	 */

	@GetMapping(value = "/getUsers")
	private ResponseEntity<List<User>> getAllUsers() {

		List<User> users = new ArrayList<User>();
		try {
			userRepo.findAll().forEach(users::add);
			logger.info("get /getUsers" + users);
			return new ResponseEntity<List<User>>(users, HttpStatus.OK);
		} catch (NullPointerException e) {
			logger.error("Unable to set List<User> ", e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
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
		logger.debug(username + " and " + password);
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
			User registered = userService.registerNewUserAccount(userDto);
			Balance setNewBalance = balanceService.setBalanceAtRegistration(registered);
			logger.info("reach registration at /register" + " balance is " + setNewBalance);
		} catch (Exception e) {

		}
		return new ModelAndView("successRegister", "user", userDto);
	}

	@PostMapping("/user/addConnection")
	private ModelAndView addConnection(@ModelAttribute("connections") ConnectionDTO connectionDto,
			HttpServletRequest request, Errors errors) {
		return new ModelAndView("successRegister", "connections", connectionDto);

	}

	@GetMapping(value = "/user/home")
	public String getUserHome() {
		return "home";
	}

}
