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

import com.PayMyBuddy.dto.TransferDTO;
import com.PayMyBuddy.exceptions.NotAConnectionException;
import com.PayMyBuddy.exceptions.NotEnoughtBalanceException;
import com.PayMyBuddy.interfaces.IConnectionService;
import com.PayMyBuddy.models.Connections;
import com.PayMyBuddy.models.Transfer;
import com.PayMyBuddy.services.TransferService;

@Controller
public class TransactionController {

	Logger logger = LoggerFactory.getLogger(TransactionController.class);

	@Autowired
	private TransferService transferService;
	
	@Autowired
	private IConnectionService iConnectionService;

	@GetMapping(value = "/user/operation/payment")
	private String operationPage(Model model, @RequestParam(value = "error", required = false) String error) {
		List<Connections> connections = iConnectionService.getAllConnections();
		model.addAttribute("transaction", new TransferDTO());
		model.addAttribute("connections", connections);
		if (null != error && error.equalsIgnoreCase("true")) {
			model.addAttribute("Error", "Unable to launch /user/operation/payment");
		}
		return "paymentPage";
	}

	@PostMapping(value = "/user/operation/payment")
	private ModelAndView paymentToConnection(@ModelAttribute(value = "transaction") TransferDTO transferDTO,
			HttpServletRequest httpServletRequest, Errors errors) {
		logger.info("entering payment with : {}", transferDTO.toString());
		try {
			
			Transfer transfer = transferService.transferToConnection(transferDTO);
		} catch (NotEnoughtBalanceException e) {
			logger.error(e.getMessage(), errors);
			return new ModelAndView("paymentFailed");
		} catch (NotAConnectionException e) {
			logger.error(e.getMessage(), errors);
			return new ModelAndView("paymentFailed");
		}

		return new ModelAndView("paymentSucess", "transaction", transferDTO);
	}
	

	@GetMapping(value ="/user/operation/self_payment")
	private String selfPayment (Model model,@RequestParam (value ="error", required = false) String error) {
		model.addAttribute("selfPayment", new TransferDTO());
	
		return "selfPayment";
	}

}
