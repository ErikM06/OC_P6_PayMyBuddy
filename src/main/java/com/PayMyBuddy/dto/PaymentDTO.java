package com.PayMyBuddy.dto;

public class PaymentDTO {

	private float amount;
	
	private String bankAccountNumber;

	private String description;

	public PaymentDTO(float amount, String description) {
		super();
		this.amount = amount;
		this.description = description;
	}

	public PaymentDTO() {
		// TODO Auto-generated constructor stub
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public String getBankAccountNumber() {
		return bankAccountNumber;
	}

	public void setBankAccountNumber(String bankAccountNumber) {
		this.bankAccountNumber = bankAccountNumber;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
