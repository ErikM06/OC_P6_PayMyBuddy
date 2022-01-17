package com.PayMyBuddy.dto;

import org.springframework.stereotype.Component;

@Component
public class PaymentDTO {

	float amount;

	String connectionUsername;

	public PaymentDTO(String connectionUsername, float amount) {
		super();
		this.amount = amount;
		this.connectionUsername = connectionUsername;
	}
	public PaymentDTO () {
		super();
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public String getConnectionUsername() {
		return connectionUsername;
	}

	public void setConnectionUsername (String connectionUsername) {
		this.connectionUsername = connectionUsername;
	}

}
