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
import com.PayMyBuddy.interfaces.ICompanyAccountService;
import com.PayMyBuddy.interfaces.IConnectionService;
import com.PayMyBuddy.interfaces.ITransferService;
import com.PayMyBuddy.interfaces.IUserService;
import com.PayMyBuddy.models.Balance;
import com.PayMyBuddy.models.Transfer;
import com.PayMyBuddy.models.User;
import com.PayMyBuddy.repo.TransferRepository;
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
	ICompanyAccountService companyAccountService;

	@Autowired
	GetCurrentUser currentUser;

	public Transfer transferToConnection(TransferDTO transferDTO)
			throws NotEnoughtBalanceException, NotAConnectionException {
		Transfer transfer = new Transfer();
		User userAccount = new User();
		User connectionAccount = new User();

		userAccount = userService.findByEmail(currentUser.getCurrentUser());
		connectionAccount = userService.findByEmail(transferDTO.getConnectionEmail());
		if (connectionAccount == null) {
			throw new NotAConnectionException();
		}

		float amountToDeduct = transferDTO.getAmount() * (float) 0.005;
		float deductedAmount = transferDTO.getAmount() - amountToDeduct;
		Balance userBalance = balanceService.takeFromBalance(userAccount, deductedAmount);
		if (userBalance.getAmount() <= 0) {
			throw new NotEnoughtBalanceException();
		}
		Balance connectionBalance = balanceService.addToBalance(connectionAccount, deductedAmount);

		logger.info("current User for transfer is : {}", userAccount.toString());
		System.out.println(userAccount.toString());
		logger.info("current connection for transfer is : {}", connectionAccount.toString());

		transfer.setAmount(deductedAmount);
		transfer.setDateTime(new Timestamp(System.currentTimeMillis()));
		transfer.setUserBalance(userBalance);
		transfer.setConnectionBalance(connectionBalance);

		companyAccountService.transferToCompanyAccount(amountToDeduct, transfer);
		transferRepository.save(transfer);
		logger.info("transaction is :", transfer.toString());

		return transfer;

	}

}
