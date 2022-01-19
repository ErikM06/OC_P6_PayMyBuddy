package com.PayMyBuddy.services;

import java.util.List;

import com.PayMyBuddy.models.BankAccount;

public interface IBankAccountService {

	public BankAccount addBankAccount(String bankAccountNumber);

	public void deleteBankAccount(String bankAccountNumber);

	public List<BankAccount> getAllBankAccountFromUser();
}
