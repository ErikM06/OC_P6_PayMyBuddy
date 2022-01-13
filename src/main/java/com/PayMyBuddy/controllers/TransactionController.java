package com.PayMyBuddy.controllers;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.PayMyBuddy.dto.PaymentDTO;
import com.PayMyBuddy.exceptions.NotEnoughtBalanceException;
import com.PayMyBuddy.services.TransactionService;

@Controller
public class TransactionController {

	Logger logger = LoggerFactory.getLogger(TransactionController.class);

	@Autowired
	private TransactionService transactionService;

	@PostMapping(value = "/user/operation/payment")
	private ModelAndView paymentToConnection(@ModelAttribute(value = "PayABuddy") PaymentDTO paymentDTO,
			HttpServletRequest httpServletRequest, Errors errors) {
		logger.info("entering payment with : {}" ,paymentDTO.toString());
		try {
			transactionService.paymentToConnection(paymentDTO);
		} catch (NotEnoughtBalanceException e) {
			logger.error(e.getMessage());
			return new ModelAndView ("paymentFailed");
		}

		return new ModelAndView ( "paymentSucessfull");
	}

}
