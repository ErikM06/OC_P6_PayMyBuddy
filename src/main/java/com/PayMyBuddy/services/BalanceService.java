package com.PayMyBuddy.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PayMyBuddy.exceptions.NotEnoughtBalanceException;
import com.PayMyBuddy.interfaces.IBalanceService;
import com.PayMyBuddy.models.Balance;
import com.PayMyBuddy.models.User;
import com.PayMyBuddy.repo.BalanceRepository;

@Service
public class BalanceService implements IBalanceService {

	Logger logger = LoggerFactory.getLogger(BalanceService.class);

	@Autowired
	BalanceRepository balanceRepository;

	public Balance initBalance(User user) {

		Balance initUserBalance = new Balance();
		initUserBalance.setuser(user);
		initUserBalance.setAmount(0);

		return initUserBalance;
	}

	public Balance addToBalance(User user, float amount) throws NotEnoughtBalanceException {
		Balance userBalance = user.getBalance();

		float userNewBalanceAmount = user.getBalance().getAmount() + (amount);
		userBalance.setAmount(userNewBalanceAmount);
		balanceRepository.save(userBalance);
		return userBalance;
	}

	public Balance takeFromBalance(User user, float amount) throws NotEnoughtBalanceException {
		Balance userBalance = user.getBalance();

		float userNewBalanceAmount = userBalance.getAmount() - (amount);
		if (userNewBalanceAmount < 0) {
			throw new NotEnoughtBalanceException();
		}
		userBalance.setAmount(userNewBalanceAmount);
		balanceRepository.save(userBalance);
		return userBalance;

	}

}
