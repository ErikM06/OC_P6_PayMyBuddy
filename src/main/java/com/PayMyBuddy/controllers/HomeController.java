package com.PayMyBuddy.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.PayMyBuddy.interfaces.IBalanceService;
import com.PayMyBuddy.interfaces.IUserService;
import com.PayMyBuddy.services.util.GetCurrentUser;

@Controller
public class HomeController {
	
	private static Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private IUserService IUserService;
	
	@Autowired
	private IBalanceService balanceService;
	
	@Autowired
	private GetCurrentUser currentUser;

	@GetMapping(value = "/admin/delete_user")
	private void deleteUserByAdmin(@RequestParam(value = "user") String username) {
		logger.info("in /admin/delete_user?user=");
		IUserService.deleteUser(username);
	}
	
	@GetMapping(value = "/user/home")
	public String getUserHome(Model model,@RequestParam(value = "error", required = false) String error) {
		
		model.addAttribute("sold",IUserService.findByEmail(currentUser.getCurrentUser()).getBalance());
		if (null != error && error.equalsIgnoreCase("true")) {
			model.addAttribute("Error", "Unable to launch /user/get_bank_account");
		}

		return "home";
	}
	
	@GetMapping(value = "/")
	private String getIndex() {
		return "index";
	}
	
	@GetMapping(value = "/user/contact")
	private String getContact() {
		return "contactPage";
	}
	

}
