package com.PayMyBuddy.dto;

import org.springframework.stereotype.Component;

import com.PayMyBuddy.models.User;

@Component
public class TransferDTO {

	float amount;

	User connection;
	
	String description;

	public TransferDTO(User connection, String description, float amount) {
		this.connection = connection;
		this.description = description;
		this.amount = amount;
	}
	
	public TransferDTO () {
		
	}

	public User getConnection() {
		return connection;
	}

	public void setConnection(User connection) {
		this.connection = connection;
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
