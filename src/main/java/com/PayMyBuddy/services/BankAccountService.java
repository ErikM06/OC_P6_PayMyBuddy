package com.PayMyBuddy.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PayMyBuddy.interfaces.IBankAccountService;
import com.PayMyBuddy.interfaces.IUserService;
import com.PayMyBuddy.models.BankAccount;
import com.PayMyBuddy.models.User;
import com.PayMyBuddy.repo.BankAccountRepository;
import com.PayMyBuddy.services.util.GetCurrentUser;

@Service
public class BankAccountService implements IBankAccountService {

	@Autowired
	BankAccountRepository bankAccountRepository;

	@Autowired
	GetCurrentUser currentUser;

	@Autowired
	IUserService userService;

	public BankAccount addBankAccount(String bankAccountNumber) {
		BankAccount bankAccount = new BankAccount();
		User user = userService.findByEmail(currentUser.getCurrentUser());
		bankAccount.setUserid(user);
		bankAccount.setBankAccountNumber(bankAccountNumber);
		bankAccountRepository.save(bankAccount);
		return bankAccount;
	}

	public void deleteBankAccount(String bankAccountNumber) {
		BankAccount bankAccount = bankAccountRepository.findByBankAccountNumber(bankAccountNumber);
		bankAccountRepository.deleteById(bankAccount.getId());
	}

	
	public List<BankAccount> getAllBankAccountFromUser() {
		
		List<BankAccount> allBankAccountFromUser = bankAccountRepository.findAllForCurrentUser(currentUser.getCurrentUser());
		return allBankAccountFromUser;
	}
	
	public BankAccount getBankAccountByBankAccountNumber (String bankAccountNumber) {
		return bankAccountRepository.findByBankAccountNumber(bankAccountNumber);
	}
	
	public boolean assertBankAccountExist (String bankAccountNumber) {
		return bankAccountRepository.existsWithBankAccountNumber(bankAccountNumber);
	}

}
