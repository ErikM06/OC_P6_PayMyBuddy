package com.PayMyBuddy.controllers;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.PayMyBuddy.models.User;
import com.PayMyBuddy.repo.UserRepository;

@RestController
@RequestMapping (value = "/users")
public class UserController {
	

	private static Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	UserRepository userRepo;
	
	
	@GetMapping (value ="/getUsers")
	private ResponseEntity<List<User>> getAllUsers (){
		
		List<User> users = new ArrayList<User>();
		try {
			userRepo.findAll().forEach(users::add);
			logger.info("get /User" + users);
			return new ResponseEntity<List<User>>(users, HttpStatus.OK) ;
		} catch (NullPointerException e) {
			logger.error("Unable to set List<User> ", e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}	
	}
	
	

}
