package com.PayMyBuddy.services;

import java.sql.Timestamp;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.PayMyBuddy.dto.PaymentDTO;
import com.PayMyBuddy.exceptions.NotEnoughtBalanceException;
import com.PayMyBuddy.models.Balance;
import com.PayMyBuddy.models.Transaction;
import com.PayMyBuddy.models.User;
import com.PayMyBuddy.repo.BalanceRepository;
import com.PayMyBuddy.repo.TransactionRepository;
import com.PayMyBuddy.repo.UserRepository;

@Service
@Transactional
public class TransactionService {

	Logger logger = LoggerFactory.getLogger(TransactionService.class);

	@Autowired
	TransactionRepository transactionRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	BalanceRepository balanceRepository;

	public String getCurrentUser() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		String username = ((User) principal).getUsername();
		logger.info("username is: {}",username);
		return username;
	}

	public Transaction paymentToConnection(PaymentDTO paymentDTO) throws NotEnoughtBalanceException {

		User userAccount = userRepository.findUserByUsername(getCurrentUser());
		User connectionAccout = userRepository.findUserByUsername(paymentDTO.getConnectionUsername());
		Balance userBalance = balanceRepository.getBalanceByUser(userAccount);
		Balance connectionBalance = balanceRepository.getBalanceByUser(connectionAccout);
		Transaction transaction = new Transaction();
		logger.info("current User for transaction is : {}", userAccount.toString());
		System.out.println(userAccount.toString());
		logger.info("current connection for transaction is : {}", connectionAccout.toString());

		float userNewBalanceAmount = userBalance.getAmount() - (paymentDTO.getAmount());
		if (userNewBalanceAmount < 0) {
			throw new NotEnoughtBalanceException();
		} else {
			float connectionNewBalanceAmount = connectionBalance.getAmount() + (paymentDTO.getAmount());

			transaction.setAmount(paymentDTO.getAmount());
			transaction.setDateTime(new Timestamp(System.currentTimeMillis()));

			userBalance.setAmount(userNewBalanceAmount);
			connectionBalance.setAmount(connectionNewBalanceAmount);
			transaction.setUserBalance(userBalance);
			transaction.setConnectionBalance(connectionBalance);
			balanceRepository.save(userBalance);
			balanceRepository.save(connectionBalance);
			transactionRepository.save(transaction);
			logger.info("transaction is :", transaction.toString());
			return transaction;
		}
	}

}
