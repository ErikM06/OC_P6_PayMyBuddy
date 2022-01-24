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
import com.PayMyBuddy.dto.TransferDTO;
import com.PayMyBuddy.exceptions.NotAConnectionException;
import com.PayMyBuddy.exceptions.NotEnoughtBalanceException;
import com.PayMyBuddy.interfaces.IConnectionService;
import com.PayMyBuddy.interfaces.IPaymentService;
import com.PayMyBuddy.interfaces.ITransferService;
import com.PayMyBuddy.models.Connections;
import com.PayMyBuddy.models.Payment;
import com.PayMyBuddy.models.Transfer;
import com.PayMyBuddy.services.TransferService;

@Controller
public class TransactionController {

	Logger logger = LoggerFactory.getLogger(TransactionController.class);

	@Autowired
	private ITransferService transferService;

	@Autowired
	private IPaymentService paymentService;

	@Autowired
	private IConnectionService iConnectionService;

	@GetMapping(value = "/user/operation/transfer")
	private String operationPage(Model model, @RequestParam(value = "error", required = false) String error)
			throws Exception {
		try {
			List<Connections> connections = iConnectionService.getAllConnections();
			model.addAttribute("transaction", new TransferDTO());
			model.addAttribute(connections);
			if (null != error && error.equalsIgnoreCase("true")) {
				model.addAttribute("Error", "Unable to launch /user/operation/transfer");
			}
		} catch (Exception e) {
			e.getMessage();
			e.printStackTrace();
		}
		return "transferPage";
	}

	@PostMapping(value = "/user/operation/transfer")
	private ModelAndView transferToConnection(@ModelAttribute(value = "transaction") TransferDTO transferDTO,
			HttpServletRequest httpServletRequest, Errors errors)
			throws NotEnoughtBalanceException, NotAConnectionException {
		logger.info("entering transfer with : {}", transferDTO.toString());
		Transfer transfer = new Transfer();
		try {
			transfer = transferService.transferToConnection(transferDTO);
		} catch (NotEnoughtBalanceException e) {
			logger.error(e.getMessage(), errors);
			return new ModelAndView("transferFailed");
		} catch (NotAConnectionException e) {
			logger.error(e.getMessage(), errors);
			return new ModelAndView("transferFailed");
		}
		return new ModelAndView("transferSucess", "transaction", transfer);
	}

	@GetMapping(value = "/user/operation/payment")
	private String selfPayment(Model model, @RequestParam(value = "error", required = false) String error) {
		model.addAttribute("payment", new PaymentDTO());

		return "paymentPage";
	}

	@PostMapping(value = "/user/operation/paymentToBankAccount")
	private ModelAndView paymentToBankAccount(@ModelAttribute(value = "payment") PaymentDTO paymentDTO,
			HttpServletRequest httpServletRequest, Errors errors) throws NotEnoughtBalanceException {
		logger.info("entering payment with : {}", paymentDTO.toString());
		Payment payment = new Payment();
		try {
			payment = paymentService.selfPaymentToAccount(paymentDTO);
		} catch (NotEnoughtBalanceException e) {
			logger.error(e.getMessage(), errors);
			return new ModelAndView("paymentFailed");
		}
		return new ModelAndView("paymentSucess", "payment", payment);
	}

	@PostMapping(value = "/user/operation/paymentToAppAccount")
	private ModelAndView paymentToAppAccount(@ModelAttribute(value = "payment") PaymentDTO paymentDTO,
			HttpServletRequest httpServletRequest, Errors errors) throws NotEnoughtBalanceException {
		logger.info("entering payment with : {}", paymentDTO.toString());
		Payment payment = new Payment();
		try {
			payment = paymentService.selfPaymentToApp(paymentDTO);
		} catch (NotEnoughtBalanceException e) {
			logger.error(e.getMessage(), errors);
			return new ModelAndView("paymentFailed");
		}
		return new ModelAndView("paymentSucess", "payment", payment);
	}

}
