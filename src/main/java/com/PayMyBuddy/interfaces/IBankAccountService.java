package com.PayMyBuddy.interfaces;

import java.util.List;

import com.PayMyBuddy.dto.BankAccountDTO;
import com.PayMyBuddy.models.BankAccount;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;

public interface IBankAccountService {

	public BankAccount addBankAccount(String bankAccountNumber) throws InvalidFormatException;

	public void deleteBankAccount(BankAccountDTO bankAccountDto);

	public List<BankAccount> getAllBankAccountFromUser();
	
	public BankAccount getBankAccountByBankAccountNumber (String bankAccountNumber);
	
	public boolean assertBankAccountExist (String bankAccountNumber);
}
