package com.PayMyBuddy.interfaces;

import java.util.List;

import com.PayMyBuddy.models.BankAccount;

public interface IBankAccountService {

	public BankAccount addBankAccount(String bankAccountNumber);

	public void deleteBankAccount(String bankAccountNumber);

	public List<BankAccount> getAllBankAccountFromUser();
	
	public BankAccount getBankAccountByBankAccountNumber (String bankAccountNumber);
	
	public boolean assertBankAccountExist (String bankAccountNumber);
}
