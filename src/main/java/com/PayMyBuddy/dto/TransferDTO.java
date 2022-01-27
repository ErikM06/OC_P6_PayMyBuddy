package com.PayMyBuddy.dto;

import com.PayMyBuddy.models.User;


public class TransferDTO {

	float amount;

	String connectionEmail;
	
	String description;


	public TransferDTO () {
		
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
