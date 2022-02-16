package com.PayMyBuddy.services;

import java.sql.Timestamp;
import java.util.List;

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
import com.PayMyBuddy.services.util.DebitAmount;
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

	public Transfer transferToConnection(TransferDTO transferDTO, GetCurrentUser currentUser)
			throws NotEnoughtBalanceException, NotAConnectionException {
		Transfer transfer = new Transfer();
		User userAccount = userService.findByEmail(currentUser.getCurrentUser());
		User connectionAccount = userService.findByEmail(transferDTO.getConnectionEmail());
		if (connectionAccount == null) {
			throw new NotAConnectionException();
		}

		float amountToDeduct = (float) (transferDTO.getAmount() * (DebitAmount.DEBIT_AMOUNT));
		float deductedAmount = transferDTO.getAmount() - amountToDeduct;
		Balance userBalance = balanceService.takeFromBalance(userAccount, transferDTO.getAmount());
		if (userBalance.getAmount() <= 0) {
			throw new NotEnoughtBalanceException();
		}
		Balance connectionBalance = balanceService.addToBalance(connectionAccount, deductedAmount);

		transfer.setAmount(deductedAmount);
		transfer.setDateTime(new Timestamp(System.currentTimeMillis()));
		if (transferDTO.getDescription() == null) {
			transfer.setDescription("No description !");
		} else {
			transfer.setDescription(transferDTO.getDescription());
		}
		transfer.setUserBalance(userBalance);
		transfer.setConnectionBalance(connectionBalance);

		companyAccountService.transferToCompanyAccount(amountToDeduct, transfer);
		transferRepository.save(transfer);

		logger.info("transaction is :", transfer.toString());
		logger.info("current User for transfer is : {}", userAccount.toString());
		System.out.println(userAccount.toString());
		logger.info("current connection for transfer is : {}", connectionAccount.toString());

		return transfer;
	}

	public List<Transfer> getAllUserTransfer(GetCurrentUser currentUser) throws NullPointerException {
		List<Transfer> transactionRecordLs = transferRepository
				.getTransactionRecordFromUser(userService.findByEmail(currentUser.getCurrentUser()));
		logger.info("transferDtoLs lenght is : {}", transactionRecordLs.size());
		if (transactionRecordLs.isEmpty()) {
			throw new NullPointerException("No transaction for current user");
		} else {

			return transactionRecordLs;
		}

	}

}
