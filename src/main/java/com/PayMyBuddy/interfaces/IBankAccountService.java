package com.PayMyBuddy.interfaces;

import java.util.List;

import com.PayMyBuddy.dto.BankAccountDTO;
import com.PayMyBuddy.models.BankAccount;
import com.PayMyBuddy.services.util.GetCurrentUser;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;

public interface IBankAccountService {

	public BankAccount addBankAccount(String bankAccountNumber, GetCurrentUser currentUser) throws InvalidFormatException;

	public void deleteBankAccount(BankAccountDTO bankAccountDto, GetCurrentUser currentUser);

	public List<BankAccount> getAllBankAccountFromUser(GetCurrentUser currentUser);
	
	public BankAccount getBankAccountByBankAccountNumber (String bankAccountNumber);
	
	public boolean assertBankAccountExist (String bankAccountNumber);
}
