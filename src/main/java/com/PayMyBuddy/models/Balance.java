package com.PayMyBuddy.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Balance")
public class Balance {

	@Id
	@GeneratedValue
	private int id;
	
	@Column (name ="amount")
	private double amount;
	
	@Column (name = "user_id")
	private int userId;

	public Balance(double amount, int userId) {
		super();
		this.amount = amount;
		this.userId = userId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getBalance() {
		return amount;
	}

	public void setBalance(double amount) {
		this.amount = amount;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

}
