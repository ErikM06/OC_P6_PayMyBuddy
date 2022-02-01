package com.PayMyBuddy.controllers;

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
import com.PayMyBuddy.dto.TransferDTO;
import com.PayMyBuddy.exceptions.NotAConnectionException;
import com.PayMyBuddy.exceptions.NotEnoughtBalanceException;
import com.PayMyBuddy.interfaces.IConnectionService;
import com.PayMyBuddy.interfaces.IPaymentService;
import com.PayMyBuddy.interfaces.ITransferService;
import com.PayMyBuddy.services.util.GetCurrentUser;

@Controller
public class TransactionController {

	Logger logger = LoggerFactory.getLogger(TransactionController.class);

	@Autowired
	private ITransferService transferService;

	@Autowired
	private IPaymentService paymentService;

	@Autowired
	private IConnectionService connectionService;

	@Autowired
	private GetCurrentUser currentUser;

	@GetMapping(value = "/user/operation")
	private String operationPage(Model model, @RequestParam(value = "error", required = false) String error)
			throws Exception, NullPointerException {
		try {
			model.addAttribute("transaction", new TransferDTO());
			model.addAttribute("connections", connectionService.getConnectionsAsUserLs(currentUser));
			model.addAttribute("transactionRecordLs", transferService.getAllUserTransfer(currentUser));
			if (null != error && error.equalsIgnoreCase("true")) {
				model.addAttribute("error", "Unable to launch /user/operation");
			}
		} catch (NullPointerException e) {
			model.addAttribute("error", error);
			logger.error(e.getMessage());
			return "home";

		} catch (Exception e) {
			model.addAttribute("error", error);
			logger.error(e.getMessage());
			return "home";
		}
		return "transferPage";
	}

	@PostMapping(value = "/user/operation/transfer")
	private ModelAndView transferToConnection(@ModelAttribute(value = "transaction") TransferDTO transferDTO,
			HttpServletRequest httpServletRequest, Errors errors)
			throws NotEnoughtBalanceException, NotAConnectionException, Exception {
		logger.info("entering transfer with : {}", transferDTO.toString());

		try {
			transferService.transferToConnection(transferDTO, currentUser);
		} catch (NotEnoughtBalanceException e) {
			e.getMessage();
			e.printStackTrace();
			return new ModelAndView("transferFailed");
		} catch (NotAConnectionException e) {
			e.getMessage();
			e.printStackTrace();
			errors.getGlobalErrors();
			return new ModelAndView("transferFailed");
		} catch (Exception e) {
			e.getMessage();
			e.printStackTrace();
			errors.getGlobalErrors();
		}
		return new ModelAndView("home", "transaction", transferDTO);
	}

	@GetMapping(value = "/user/operation/payment")
	private ModelAndView selfPayment(Model model, @RequestParam(value = "error", required = false) String error) {
		model.addAttribute("payment", new PaymentDTO());
		if (null != error && error.equalsIgnoreCase("true")) {
			model.addAttribute("error", "Unable to launch /user/operation/payment");
		}

		return new ModelAndView("paymentPage");

	}

	@PostMapping(value = "/user/operation/paymentToBankAccount")
	private ModelAndView paymentToBankAccount(@ModelAttribute(value = "payment") PaymentDTO paymentDTO,
			HttpServletRequest httpServletRequest, Errors errors) throws NotEnoughtBalanceException, Exception {
		logger.info("entering payment with : {}", paymentDTO.toString());

		try {
			paymentService.selfPaymentToAccount(paymentDTO, currentUser);
		} catch (NotEnoughtBalanceException e) {
			e.getMessage();
			e.printStackTrace();
			errors.getGlobalErrors();
			return new ModelAndView("paymentFailed");
		} catch (Exception e) {
			e.getMessage();
			e.printStackTrace();
			errors.getGlobalErrors();
		}
		return new ModelAndView("paymentSucess", "payment", paymentDTO);
	}

	@PostMapping(value = "/user/operation/paymentToAppAccount")
	private ModelAndView paymentToAppAccount(@ModelAttribute(value = "payment") PaymentDTO paymentDTO,
			HttpServletRequest httpServletRequest, Errors errors) throws NotEnoughtBalanceException, Exception {
		logger.info("entering payment with : {}", paymentDTO.toString());

		try {
			paymentService.selfPaymentToApp(paymentDTO, currentUser);
		} catch (NotEnoughtBalanceException e) {
			e.getMessage();
			e.printStackTrace();
			errors.getGlobalErrors();
			return new ModelAndView("paymentFailed");
		} catch (Exception e) {
			e.getMessage();
			e.printStackTrace();
			errors.getGlobalErrors();
		}
		return new ModelAndView("paymentSucess", "payment", paymentDTO);
	}

}
