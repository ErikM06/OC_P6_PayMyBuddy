package com.PayMyBuddy.services;

import java.sql.Timestamp;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PayMyBuddy.dto.TransferDTO;
import com.PayMyBuddy.exceptions.NotAConnectionException;
import com.PayMyBuddy.exceptions.NotEnoughtBalanceException;
import com.PayMyBuddy.interfaces.IBalanceService;
import com.PayMyBuddy.interfaces.IConnectionService;
import com.PayMyBuddy.interfaces.ITransferService;
import com.PayMyBuddy.interfaces.IUserService;
import com.PayMyBuddy.models.Balance;
import com.PayMyBuddy.models.Transfer;
import com.PayMyBuddy.models.User;
import com.PayMyBuddy.repo.TransferRepository;
import com.PayMyBuddy.services.util.DeductFromOperation;
import com.PayMyBuddy.services.util.GetCurrentUser;

@Service
@Transactional
public class TransferService implements ITransferService {

	Logger logger = LoggerFactory.getLogger(TransferService.class);

	@Autowired
	TransferRepository transferRepository;

	@Autowired
	IUserService userService;

	@Autowired
	IConnectionService connectionService;

	@Autowired
	IBalanceService balanceService;

	@Autowired
	GetCurrentUser currentUser;

	@Autowired
	DeductFromOperation deductFromOperation;

	public Transfer transferToConnection(TransferDTO transferDTO)
			throws NotEnoughtBalanceException, NotAConnectionException {
		User userAccount = new User();
		User connectionAccount = new User();
		
		userAccount = userService.findByEmail(currentUser.getCurrentUser());
		connectionAccount = userService.findByEmail(transferDTO.getConnectionEmail());
		
		Transfer transfer = new Transfer();
		/*
		 * if (connectionService.assertConnection(userAccount.getId(),
		 * connectionAccount.getId()) == false) { throw new NotAConnectionException(); }
		 * else {
		 */
		float deductedAmount = deductFromOperation.deductedFromTransferOperation(transferDTO.getAmount(), transfer);
		Balance userBalance = balanceService.takeFromBalance(userAccount, deductedAmount);
		Balance connectionBalance = balanceService.addToBalance(connectionAccount, deductedAmount);

		logger.info("current User for transfer is : {}", userAccount.toString());
		System.out.println(userAccount.toString());
		logger.info("current connection for transfer is : {}", connectionAccount.toString());

		transfer.setAmount(deductedAmount);
		transfer.setDateTime(new Timestamp(System.currentTimeMillis()));
		transfer.setUserBalance(userBalance);
		transfer.setConnectionBalance(connectionBalance);

		transferRepository.save(transfer);
		logger.info("transaction is :", transfer.toString());

		return transfer;

	}

}
