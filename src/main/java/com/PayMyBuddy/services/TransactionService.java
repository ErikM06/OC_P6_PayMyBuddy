package com.PayMyBuddy.services;

import java.sql.Timestamp;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.PayMyBuddy.models.Transaction;
import com.PayMyBuddy.models.User;
import com.PayMyBuddy.repo.TransactionRepository;

@Service
@Transactional
public class TransactionService {
	
	Logger logger = LoggerFactory.getLogger(TransactionService.class);
	
	@Autowired
	TransactionRepository transactionRepository;
	
	public User getCurrentUser () {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		logger.info("current User for transaction is : {}", user);
		return user;
	}
	
	public Transaction paymentToConnection(double amount, String ConnectionName) {
	
	Transaction transaction = new Transaction();
	transaction.setAmount(amount);
	transaction.setDateTime(new Timestamp(System.currentTimeMillis()));
	transaction.setBalance(null);
	
		
		return null;
	}

}
