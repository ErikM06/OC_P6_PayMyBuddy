package com.PayMyBuddy.dto;


public class TransferDTO {

	float amount;

	String connectionEmail;
	
	String description;


	public TransferDTO () {
		
	}

	public TransferDTO(float f, String email, String string) {
		// TODO Auto-generated constructor stub
	}

	public String getConnectionEmail() {
		return connectionEmail;
	}

	public void setConnectionEmail(String connectionEmail) {
		this.connectionEmail = connectionEmail;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

}
