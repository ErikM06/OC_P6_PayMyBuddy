package com.PayMyBuddy.dto;

public class PaymentDTO {
	
	double amount;
	
	String connection;

	public PaymentDTO(String connection, double amount) {
		super();
		this.amount = amount;
		this.connection = connection;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getConnection() {
		return connection;
	}

	public void setConnection(String connection) {
		this.connection = connection;
	}
	
	

}
