package com.PayMyBuddy.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PayMyBuddy.models.Balance;
import com.PayMyBuddy.models.User;
import com.PayMyBuddy.repo.UserRepository;


@Service
public class BalanceService implements IBalanceService {
	
	Logger logger = LoggerFactory.getLogger(BalanceService.class);

	@Autowired
	UserRepository userRepository;


	public Balance getBalanceByUser(User user) {
		
		Balance userBalance = userRepository.getBalanceByUser(user);
		
		return userBalance;
		
	}

}
