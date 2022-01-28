package com.PayMyBuddy.services;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import com.PayMyBuddy.dto.BankAccountDTO;
import com.PayMyBuddy.interfaces.IBankAccountService;
import com.PayMyBuddy.interfaces.IUserService;
import com.PayMyBuddy.models.BankAccount;
import com.PayMyBuddy.models.User;
import com.PayMyBuddy.repo.BankAccountRepository;
import com.PayMyBuddy.services.util.GetCurrentUser;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;

@Service
public class BankAccountService implements IBankAccountService {

	private static Logger logger = LoggerFactory.getLogger(BankAccountService.class);

	@Autowired
	BankAccountRepository bankAccountRepository;

	@Autowired
	IUserService userService;

	public BankAccount addBankAccount(String bankAccountNumber, GetCurrentUser currentUser) throws InvalidFormatException {
		BankAccount bankAccount = new BankAccount();
		User user = userService.findByEmail(currentUser.getCurrentUser());
		bankAccount.setUserid(user);
		bankAccount.setBankAccountNumber(bankAccountNumber);
		bankAccountRepository.save(bankAccount);
		logger.info("Saved bank account is :{}", bankAccount.toString());
		return bankAccount;
	}
	
	@Transactional
	@Modifying
	public void deleteBankAccount(BankAccountDTO bankAccountDTO, GetCurrentUser currentUser) throws NullPointerException {
		if (bankAccountRepository.existsWithBankAccountNumber(bankAccountDTO.getBankAccountNumber()) == false) {
			throw new NullPointerException("Bank Account don't existe : {}");
		} else {
			bankAccountRepository.deleteByBankAccountNumber(bankAccountDTO.getBankAccountNumber());
		}
	}

	public List<BankAccount> getAllBankAccountFromUser(GetCurrentUser currentUser) {

		List<BankAccount> allBankAccountFromUser = bankAccountRepository
				.findAllForCurrentUser(currentUser.getCurrentUser());
		return allBankAccountFromUser;
	}

	public BankAccount getBankAccountByBankAccountNumber(String bankAccountNumber) {
		return bankAccountRepository.findByBankAccountNumber(bankAccountNumber);
	}

	public boolean assertBankAccountExist(String bankAccountNumber) {
		return bankAccountRepository.existsWithBankAccountNumber(bankAccountNumber);
	}

}
