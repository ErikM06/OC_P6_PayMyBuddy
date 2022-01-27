package com.PayMyBuddy.dto;

import com.PayMyBuddy.models.User;


public class TransferDTO {

	float amount;

	User friend;
	
	String description;

	public TransferDTO(User friend, String description, float amount) {
		this.friend = friend;
		this.description = description;
		this.amount = amount;
	}
	
	public TransferDTO () {
		
	}

	public User getFriend() {
		return friend;
	}

	public void setFriend(User friend) {
		this.friend = friend;
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
