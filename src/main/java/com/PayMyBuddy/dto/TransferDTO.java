package com.PayMyBuddy.dto;

import org.springframework.stereotype.Component;

@Component
public class TransferDTO {

	float amount;

	String connectionEmail;

	public TransferDTO(String connectionEmail, float amount) {
		super();
		this.amount = amount;
		this.connectionEmail = connectionEmail;
	}
	public TransferDTO () {
		super();
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public String getConnectionEmail() {
		return connectionEmail;
	}

	public void setConnectionEmail (String connectionEmail) {
		this.connectionEmail = connectionEmail;
	}

}
