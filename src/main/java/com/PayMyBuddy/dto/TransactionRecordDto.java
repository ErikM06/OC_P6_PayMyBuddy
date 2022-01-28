package com.PayMyBuddy.dto;

import com.PayMyBuddy.models.User;

public class TransactionRecordDto {

	private User connection;

	private String description;
	
	private float amount;

	public TransactionRecordDto(User connection, String description, float amount) {
		super();
		this.connection = connection;
		this.description = description;
		this.amount = amount;
	}
	
	public TransactionRecordDto () {
		
	}
	public User getConnection() {
		return connection;
	}

	public void setConnection(User connection) {
		this.connection = connection;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	
}
