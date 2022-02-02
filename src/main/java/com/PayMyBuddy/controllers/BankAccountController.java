package com.PayMyBuddy.controllers;

import javax.servlet.http.HttpServletRequest;

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
import com.PayMyBuddy.interfaces.IBankAccountService;
import com.PayMyBuddy.models.BankAccount;
import com.PayMyBuddy.services.util.GetCurrentUser;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;

@Controller
public class BankAccountController {
	
	@Autowired
	private IBankAccountService bankAccountService;
	
	@Autowired
	private GetCurrentUser currentUser;
	
	
	@PostMapping(value = "/user/get_bank_account")
	private ModelAndView getAddBankAccount(Model model, @RequestParam(value = "error", required = false) String error) {
		model.addAttribute("bankAccount", new BankAccount());
		if (null != error && error.equalsIgnoreCase("true")) {
			model.addAttribute("Error", "Unable to launch /user/get_bank_account");
		}
		return new ModelAndView("profilPage");
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
		return new ModelAndView("redirect:/user/profil", "bankaccount", bankAccountDTO);
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

		return new ModelAndView("redirect:/user/profil", "bankAccount", bankAccountDTO);
	}

}
