package com.PayMyBuddy.controllers;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.PayMyBuddy.dto.BankAccountDTO;
import com.PayMyBuddy.interfaces.IBankAccountService;
import com.PayMyBuddy.services.util.GetCurrentUser;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;

@Controller
public class BankAccountController {

	private static Logger logger = LoggerFactory.getLogger(BankAccountController.class);

	@Autowired
	private IBankAccountService bankAccountService;

	@Autowired
	private GetCurrentUser currentUser;

	@PostMapping(value = "/user/add_bank_account")
	private ModelAndView addBankAccount(@ModelAttribute("bankAccount") BankAccountDTO bankAccountDTO,
			HttpServletRequest request, Errors errors) throws InvalidFormatException {
		try {
			bankAccountService.addBankAccount(bankAccountDTO.getBankAccountNumber(), currentUser);
		} catch (InvalidFormatException e) {
			logger.info(e.getMessage());
			return new ModelAndView("redirect:/user/profil", "error", e.getMessage());
		}
		return new ModelAndView("redirect:/user/profil", "success", bankAccountDTO);
	}

	@GetMapping(value = "/user/delete_bank_account")
	private ModelAndView deleteBankAccount(@ModelAttribute("bankAccount") BankAccountDTO bankAccountDTO,
			HttpServletRequest request, Errors errors) throws NullPointerException {
		try {
			bankAccountService.deleteBankAccount(bankAccountDTO, currentUser);
		} catch (NullPointerException e) {
			logger.info(e.getMessage());
			return new ModelAndView("redirect:/user/profil", "error", e.getMessage());
		}

		return new ModelAndView("redirect:/user/profil", "success", bankAccountDTO);
	}

}
