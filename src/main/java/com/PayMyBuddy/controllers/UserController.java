package com.PayMyBuddy.controllers;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.PayMyBuddy.dto.UserDTO;
import com.PayMyBuddy.exceptions.UserAlreadyExistException;
import com.PayMyBuddy.interfaces.IBankAccountService;
import com.PayMyBuddy.interfaces.IUserService;
import com.PayMyBuddy.models.User;
import com.PayMyBuddy.services.util.GetCurrentUser;

@Controller
public class UserController {

	private static Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private IUserService IUserService;
	
	@Autowired
	private IBankAccountService bankAccountService;
	
	@Autowired
	private GetCurrentUser currentUser;


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
			IUserService.registerNewUserAccount(userDto);
			logger.info("reach registration at /register : {}", userDto.getUsername());
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
	
	@PutMapping(value ="/user/profil/uptade")
	private ModelAndView uptadeUser (@ModelAttribute("userUpdate") UserDTO userDto, 
			HttpServletRequest request, BindingResult bindingResult) throws Exception{
		try {
		IUserService.uptadeUser(userDto);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return new ModelAndView("redirect:/user/profil", "userUpdate", userDto);
		
	}
	
	@GetMapping (value ="/user/profil")
	private String getUserProfil (Model model, @RequestParam(value = "error", required = false) String error) {
		model.addAttribute("user", IUserService.findByEmail(currentUser.getCurrentUser()));
		model.addAttribute("bankAccountls", bankAccountService.getAllBankAccountFromUser(currentUser));
		
		return "profilPage";	
	}



}
