package com.PayMyBuddy.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PayMyBuddy.exceptions.NotEnoughtBalanceException;
import com.PayMyBuddy.interfaces.IBalanceService;
import com.PayMyBuddy.interfaces.IUserService;
import com.PayMyBuddy.models.Balance;
import com.PayMyBuddy.models.User;
import com.PayMyBuddy.repo.BalanceRepository;
import com.PayMyBuddy.repo.UserRepository;


@Service
public class BalanceService implements IBalanceService {
	
	Logger logger = LoggerFactory.getLogger(BalanceService.class);

	@Autowired
	BalanceRepository balanceRepository;
	


	public Balance getBalanceByUser(User user) {
		
		Balance userBalance = balanceRepository.getBalanceByUser(user);
		
		return userBalance;
		
	}
	
	public Balance initBalance (User user) {
		
		Balance initUserBalance = new Balance();
		initUserBalance.setuser(user);
		initUserBalance.setAmount(0);
		
		return initUserBalance;
	}
	/*
	 * userBalance the imaginary bank account api
	 * in this method user can add as many as he want.
	 * balance to newBalance is just an example of payment comming from an other api
	 */
	public Balance addToBalance (User user, float amount) throws NotEnoughtBalanceException {
		Balance userBalance = getBalanceByUser(user);
		Balance userNewBalance = new Balance();
		
		float userNewBalanceAmount = userBalance.getAmount() + (amount);
		if (userBalance.getAmount() < 0) {
			throw new NotEnoughtBalanceException();
		} 
		userNewBalance.setAmount(userNewBalanceAmount);
		balanceRepository.save(userNewBalance);
		return userNewBalance;
	}
	/*
	 * userBalance the imaginary bank account api
	 */
	public Balance takeFromBalance (User user, float amount) throws NotEnoughtBalanceException {
		Balance userBalance = getBalanceByUser(user);
		Balance userNewBalance = new Balance();
		float userNewBalanceAmount = userBalance.getAmount() - (amount);
		if (userNewBalanceAmount < 0) {
			throw new NotEnoughtBalanceException();
		} 
		userNewBalance.setAmount(userNewBalanceAmount);
		balanceRepository.save(userNewBalance);
		return userNewBalance;
		
	}

}
