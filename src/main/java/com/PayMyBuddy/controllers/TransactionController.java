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

import com.PayMyBuddy.dto.PaymentDTO;
import com.PayMyBuddy.exceptions.NotAConnectionException;
import com.PayMyBuddy.exceptions.NotEnoughtBalanceException;
import com.PayMyBuddy.models.Connections;
import com.PayMyBuddy.models.Transaction;
import com.PayMyBuddy.services.IConnectionService;
import com.PayMyBuddy.services.TransactionService;

@Controller
public class TransactionController {

	Logger logger = LoggerFactory.getLogger(TransactionController.class);

	@Autowired
	private TransactionService transactionService;
	
	@Autowired
	private IConnectionService iConnectionService;

	@GetMapping(value = "/user/operation/payment")
	private String operationPage(Model model, @RequestParam(value = "error", required = false) String error) {
		List<Connections> connections = iConnectionService.getAllConnections();
		model.addAttribute("transaction", new PaymentDTO());
		model.addAttribute("connections", connections);
		if (null != error && error.equalsIgnoreCase("true")) {
			model.addAttribute("Error", "Unable to launch /user/operation/payment");
		}
		return "paymentPage";
	}

	@PostMapping(value = "/user/operation/payment")
	private ModelAndView paymentToConnection(@ModelAttribute(value = "transaction") PaymentDTO paymentDTO,
			HttpServletRequest httpServletRequest, Errors errors) {
		logger.info("entering payment with : {}", paymentDTO.toString());
		try {
			
			Transaction transaction = transactionService.paymentToConnection(paymentDTO);
		} catch (NotEnoughtBalanceException e) {
			logger.error(e.getMessage());
			return new ModelAndView("paymentFailed");
		} catch (NotAConnectionException e) {
			logger.error(e.getMessage());
			return new ModelAndView("paymentFailed");
		}

		return new ModelAndView("paymentSucess", "transaction", paymentDTO);
	}

}
