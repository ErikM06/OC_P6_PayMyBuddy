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
import com.PayMyBuddy.interfaces.IBankAccountService;
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
	private IBankAccountService bankAccountService;

	@Autowired
	private GetCurrentUser currentUser;

	@GetMapping(value = "/user/operation")
	private String operationPage(Model model, @RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "success", required = false) String success) throws Exception, NullPointerException {
		try {
			model.addAttribute("transaction", new TransferDTO());
			model.addAttribute("connections", connectionService.getConnectionsAsUserLs(currentUser));
			model.addAttribute("transactionRecordLs", transferService.getAllUserTransfer(currentUser));
			if (null != error) {
				model.addAttribute("error", error);
			}
			if (null != success) {
				model.addAttribute("success", "transfer success!");
			}
		} catch (NullPointerException e) {
			logger.error(e.getMessage());
			model.addAttribute("error", e.getMessage());

		} catch (Exception e) {
			logger.error(e.getMessage());
			model.addAttribute("error", e.getMessage());
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
			logger.info(e.getMessage());
			return new ModelAndView("redirect:/user/operation", "error", e.getMessage());
		} catch (NotAConnectionException e) {
			logger.info(e.getMessage());
			return new ModelAndView("redirect:/user/operation", "error", e.getMessage());
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		return new ModelAndView("redirect:/user/operation", "success", "transfer_success");
	}

	@GetMapping(value = "/user/operation/payment")
	private ModelAndView selfPayment(Model model, @RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "success", required = false) String success) {
		model.addAttribute("payment", new PaymentDTO());
		model.addAttribute("bankAccountLs", bankAccountService.getAllBankAccountFromUser(currentUser));
		if (null != error) {
			model.addAttribute("error", error);
		}
		if (null != success) {
			model.addAttribute("success", "payment success!");
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
			logger.info(e.getMessage());
			return new ModelAndView("redirect:/user/operation/payment", "error", e.getMessage());
		} catch (Exception e) {
			logger.info(e.getMessage());
			return new ModelAndView("redirect:/user/operation/payment", "error", e.getMessage());
		}
		return new ModelAndView("redirect:/user/operation/payment");
	}

	@PostMapping(value = "/user/operation/paymentToAppAccount")
	private ModelAndView paymentToAppAccount(@ModelAttribute(value = "payment") PaymentDTO paymentDTO,
			HttpServletRequest httpServletRequest, Errors errors) throws NotEnoughtBalanceException, Exception {
		logger.info("entering payment with : {}", paymentDTO.toString());

		try {
			paymentService.selfPaymentToApp(paymentDTO, currentUser);
		} catch (NotEnoughtBalanceException e) {
			logger.info(e.getMessage());
			return new ModelAndView("redirect:/user/operation", "error", e.getMessage());
		} catch (Exception e) {
			logger.info(e.getMessage());
			return new ModelAndView("redirect:/user/operation", "error", e.getMessage());
		}
		return new ModelAndView("redirect:/user/operation/payment");
	}

}
