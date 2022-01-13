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

	public User getCurrentUser() {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		logger.info("current User for transaction is : {}", user);
		return user;
	}

	public Transaction paymentToConnection(PaymentDTO paymentDTO) throws NotEnoughtBalanceException {

		User user = getCurrentUser();
		User connectionAccout = userRepository.findUserByUsername(paymentDTO.getConnection());
		Balance userBalance = balanceRepository.getBalanceByUser(user);
		Balance connectionBalance = balanceRepository.getBalanceByUser(connectionAccout);
		Transaction transaction = new Transaction();

		double userNewBalanceAmount = userBalance.getAmount() - (paymentDTO.getAmount());
		if (userNewBalanceAmount < 0) {
			throw new NotEnoughtBalanceException();
		} else {
			double connectionNewBalanceAmount = connectionBalance.getAmount() + (paymentDTO.getAmount());

			transaction.setAmount(paymentDTO.getAmount());
			transaction.setDateTime(new Timestamp(System.currentTimeMillis()));

			userBalance.setAmount(userNewBalanceAmount);
			connectionBalance.setAmount(connectionNewBalanceAmount);
			transaction.setBalance(userBalance);
			balanceRepository.save(userBalance);
			balanceRepository.save(connectionBalance);
			transactionRepository.save(transaction);
			return transaction;
		}
	}

}
