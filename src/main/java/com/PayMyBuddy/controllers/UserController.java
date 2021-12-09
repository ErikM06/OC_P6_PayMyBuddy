package com.PayMyBuddy.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.security.RolesAllowed;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.PayMyBuddy.models.User;
import com.PayMyBuddy.repo.UserRepository;

@RestController
@RequestMapping(value = "/users")
public class UserController {

	private static Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserRepository userRepo;

	 /* @Autowired
	PasswordEncoder passwordEncoder; */

	@GetMapping(value = "/getUsers")
	private ResponseEntity<List<User>> getAllUsers() {

		List<User> users = new ArrayList<User>();
		try {
			userRepo.findAll().forEach(users::add);
			logger.info("get /users/getUsers" + users);
			return new ResponseEntity<List<User>>(users, HttpStatus.OK);
		} catch (NullPointerException e) {
			logger.error("Unable to set List<User> ", e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(value = "/getUsers/loggingWith")
	private ResponseEntity<User> getUserByLog(@RequestParam(value = "username") String username,
			@RequestParam(value = "password") String password) {
		Optional<User> user = userRepo.findUserByLogs(username, password);
		try {
			if (user.isPresent()) {
				logger.info("get /users/getUsers/loggingWith " + user.get());
				return new ResponseEntity<User>(user.get(), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			}

		} catch (NullPointerException e) {
			logger.error("Unable to set List<User> ", e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping(value = "/postUser")
	@RolesAllowed(value ="User")
	private ResponseEntity<User> postUser(@RequestBody User user) {
		try {
			User saveUser = userRepo
					.save(new User(user.getUsername(), user.getEmail(), user.getPassword(), user.getCreateTime()));
			logger.info("post /users/postUser" + saveUser);
			return new ResponseEntity<User>(saveUser, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	 /* @PostMapping(value = "/postUser/register")
	@RolesAllowed("USER")
	public ResponseEntity<?> userRegistration(@RequestBody User user) {

		try {
			User saveUser = userRepo
					.save(new User(user.getUsername(), user.getEmail(), user.getPassword(), user.getCreateTime()));
			String encodedPassword = passwordEncoder.encode(user.getPassword());
			logger.info("post /users/postUser" + saveUser);
			return new ResponseEntity<User>(saveUser, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	} */

}
