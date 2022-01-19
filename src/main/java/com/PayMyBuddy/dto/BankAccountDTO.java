package com.PayMyBuddy.dto;



public class BankAccountDTO {
	
	private String bankAccountNumber;
	

	public BankAccountDTO(String bankAccountNumber) {
		super();
		this.bankAccountNumber = bankAccountNumber;
		
	}
	
	public BankAccountDTO() {
		
	}

	public String getBankAccountNumber() {
		return bankAccountNumber;
	}

	public void setBankAccountNumber(String bankAccountNumber) {
		this.bankAccountNumber = bankAccountNumber;
	}

}
