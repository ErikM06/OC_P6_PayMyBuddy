package com.PayMyBuddy.dto;

import org.springframework.stereotype.Component;

@Component
public class PaymentDTO {

	float amount;

	String connectionEmail;

	public PaymentDTO(String connectionEmail, float amount) {
		super();
		this.amount = amount;
		this.connectionEmail = connectionEmail;
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

	public String getConnectionEmail() {
		return connectionEmail;
	}

	public void setConnectionUsername (String connectionEmail) {
		this.connectionEmail = connectionEmail;
	}

}
