package com.PayMyBuddy.services;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PayMyBuddy.models.Balance;
import com.PayMyBuddy.models.User;
import com.PayMyBuddy.repo.BalanceRepository;

/*
 * problème de sécurité, la classe est public et set la balance.
 */
@Service
@Transactional
public class BalanceService implements IBalanceService {
	
	Logger logger = LoggerFactory.getLogger(BalanceService.class);

	@Autowired
	BalanceRepository balanceRepo;

	public Balance setBalanceAtRegistration(User user) {

		Balance newBalance = new Balance(0, user.getId());
		logger.info("Balance for new user: "+user+ "is : "+newBalance);

		return balanceRepo.save(newBalance);
	}

}
