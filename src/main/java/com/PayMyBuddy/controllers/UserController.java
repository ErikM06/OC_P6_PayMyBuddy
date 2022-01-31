package com.PayMyBuddy.controllers;

import java.util.List;

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

import com.PayMyBuddy.dto.BankAccountDTO;
import com.PayMyBuddy.dto.ConnectionDTO;
import com.PayMyBuddy.dto.UserDTO;
import com.PayMyBuddy.exceptions.UserAlreadyExistException;
import com.PayMyBuddy.interfaces.IBalanceService;
import com.PayMyBuddy.interfaces.IBankAccountService;
import com.PayMyBuddy.interfaces.IConnectionService;
import com.PayMyBuddy.interfaces.IUserService;
import com.PayMyBuddy.models.BankAccount;
import com.PayMyBuddy.models.User;
import com.PayMyBuddy.services.util.GetCurrentUser;
import com.PayMyBuddy.services.util.SecurityService;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;

@Controller
public class UserController {

	private static Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private IUserService IUserService;

	@Autowired
	private SecurityService securityService;

	@Autowired
	private IConnectionService IConnectionService;

	@Autowired
	private IBankAccountService bankAccountService;
	
	@Autowired
	private IBalanceService balanceService;
	
	@Autowired
	private GetCurrentUser currentUser;

	@GetMapping(value = "/admin/delete_user")
	private void deleteUserByAdmin(@RequestParam(value = "user") String username) {
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

	@GetMapping("/user/connection")
	private ModelAndView getAddConnection(Model model, @RequestParam(value = "error", required = false) String error) {
		model.addAttribute("connection", new ConnectionDTO());
		if (null != error && error.equalsIgnoreCase("true")) {
			model.addAttribute("Error", "Unable to launch /connection");
		}
		return new ModelAndView("connectionPage");

	}

	@PostMapping("/user/add_connection")
	private ModelAndView addConnection(@ModelAttribute("connection") ConnectionDTO connectionDto,
			HttpServletRequest request, Errors errors) throws NullPointerException {
		try {
			IConnectionService.addConnections(connectionDto,currentUser);
		} catch (NullPointerException e) {
			e.getMessage();
			e.printStackTrace();
			errors.getGlobalErrors();
		}
		return new ModelAndView("home", "connection", connectionDto);

	}

	@GetMapping(value = "/user/delete_connection")
	private ModelAndView deleteConnection(@ModelAttribute("connection") ConnectionDTO connectionDto,
			HttpServletRequest request, Errors errors) throws Exception, NullPointerException {
		try {
			IConnectionService.deleteConnection(connectionDto.getConnectionUsername(),currentUser);
		} catch (NullPointerException e) {
			e.getMessage();
			e.printStackTrace();
			errors.getGlobalErrors();
		} catch (Exception e) {
			e.getMessage();
			e.printStackTrace();
			errors.getGlobalErrors();
		}
		return new ModelAndView("home");

	}

	@GetMapping(value = "/user/get_bank_account")
	private ModelAndView getAddBankAccount(Model model, @RequestParam(value = "error", required = false) String error) {
		List<BankAccount> bankAccountls = bankAccountService.getAllBankAccountFromUser(currentUser);
		model.addAttribute("bankAccountls", bankAccountls);
		model.addAttribute("bankAccount", new BankAccountDTO());
		model.addAttribute("bankAccountToDelete", new BankAccountDTO());
		if (null != error && error.equalsIgnoreCase("true")) {
			model.addAttribute("Error", "Unable to launch /user/get_bank_account");
		}
		return new ModelAndView("BankAccount");
	}

	@PostMapping(value = "/user/add_bank_account")
	private ModelAndView addBankAccount(@ModelAttribute("bankAccount") BankAccountDTO bankAccountDTO,
			HttpServletRequest request, Errors errors) throws InvalidFormatException {
		try {
			bankAccountService.addBankAccount(bankAccountDTO.getBankAccountNumber(),currentUser);
		} catch (InvalidFormatException e) {
			e.getMessage();
			e.printStackTrace();
			errors.getGlobalErrors();
		}
		return new ModelAndView("home", "bankaccount", bankAccountDTO);
	}

	@GetMapping(value = "/user/delete_bank_account")
	private ModelAndView deleteBankAccount(@ModelAttribute("bankAccountToDelete") BankAccountDTO bankAccountDTO,
			HttpServletRequest request, Errors errors) throws NullPointerException {
		try {
		bankAccountService.deleteBankAccount(bankAccountDTO,currentUser);
		} catch (NullPointerException e) {
			e.getMessage();
			e.printStackTrace();
			errors.getGlobalErrors();
		}

		return new ModelAndView("home", "bankAccount", bankAccountDTO);
	}

	@GetMapping(value = "/user/home")
	public String getUserHome(Model model,@RequestParam(value = "error", required = false) String error) {
		model.addAttribute("sold", balanceService.getBalanceByUser(IUserService.findByEmail(currentUser.getCurrentUser())));
		if (null != error && error.equalsIgnoreCase("true")) {
			model.addAttribute("Error", "Unable to launch /user/get_bank_account");
		}

		return "home";
	}

}
