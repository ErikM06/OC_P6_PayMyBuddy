package com.PayMyBuddy.services;

import java.sql.Timestamp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PayMyBuddy.exceptions.NotEnoughtBalanceException;
import com.PayMyBuddy.interfaces.IBalanceService;
import com.PayMyBuddy.interfaces.IBankAccountService;
import com.PayMyBuddy.interfaces.IPaymentService;
import com.PayMyBuddy.interfaces.IUserService;
import com.PayMyBuddy.models.Balance;
import com.PayMyBuddy.models.Payment;
import com.PayMyBuddy.models.User;
import com.PayMyBuddy.repo.PaymentRepository;
import com.PayMyBuddy.services.util.DeductFromOperation;
import com.PayMyBuddy.services.util.GetCurrentUser;

@Service
public class PaymentService implements IPaymentService {

	private Logger logger = LoggerFactory.getLogger(PaymentService.class);
	
	@Autowired
	IUserService userService;

	@Autowired
	GetCurrentUser currentUser;

	@Autowired
	IBankAccountService bankAccountService;

	@Autowired
	PaymentRepository paymentRepository;
	
	@Autowired
	IBalanceService balanceService;
	
	@Autowired
	DeductFromOperation deductFromOperation;

	public Payment selfPaymentToAccount(String bankAccountNumber, float amount) throws NotEnoughtBalanceException {
		User user = userService.findByEmail(currentUser.getCurrentUser());
		Payment payment = new Payment();
		float deductedAmount = deductFromOperation.deductedFromPaymentOperation(amount, payment);
		Balance balance = balanceService.takeFromBalance(user, deductedAmount);
		logger.info("user is : ",user, "amount is : ", deductedAmount);
		payment.setAmount(deductedAmount);
		payment.setUserBalance(balance);
		payment.setDateTime(new Timestamp(System.currentTimeMillis()));
		payment.setPaymentDirection("To bank account");
		payment.setUserId(user);
		payment.setBankAccount(bankAccountService.getBankAccountByBankAccountNumber(bankAccountNumber));
		paymentRepository.save(payment);

		return payment;
	}

	public Payment selfPaymentToApp(String bankAccountNumber, float amount) throws NotEnoughtBalanceException {
		User user = userService.findByEmail(currentUser.getCurrentUser());
		Payment payment = new Payment();
		float deductedAmount = deductFromOperation.deductedFromPaymentOperation(amount, payment);
		Balance balance = balanceService.addToBalance(user, deductedAmount);
		payment.setAmount(deductedAmount);
		payment.setUserBalance(balance);
		payment.setDateTime(new Timestamp(System.currentTimeMillis()));
		payment.setPaymentDirection("To app account");
		payment.setUserId(user);
		payment.setBankAccount(bankAccountService.getBankAccountByBankAccountNumber(bankAccountNumber));
		paymentRepository.save(payment);

		return payment;
	}

}
