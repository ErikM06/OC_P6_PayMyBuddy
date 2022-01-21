package com.PayMyBuddy.models;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table (name = "Transfer")
public class Transfer {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column
	private Timestamp dateTime;
	
	@Column (name = "transfer_amount")
	private float amount;
	
	@ManyToOne (cascade = CascadeType.ALL)
	@JoinColumn (name = "user_balance_id")
	private Balance userBalance;
	
	@ManyToOne (cascade = CascadeType.ALL)
	@JoinColumn (name ="connection_balance_id")
	private Balance connectionBalance;

	public Transfer( Timestamp dateTime, float amount, Balance userBalance, Balance connectionBalance ) {
		super();
		
		this.dateTime = dateTime;
		this.amount = amount;
		this.userBalance = userBalance;
		this.connectionBalance = connectionBalance;
	}
	
	public Transfer() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public Timestamp getDateTime() {
		return dateTime;
	}

	public void setDateTime(Timestamp dateTime) {
		this.dateTime = dateTime;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public Balance getUserBalance() {
		return userBalance;
	}

	public void setUserBalance(Balance userBalance) {
		this.userBalance = userBalance;
	}

	public Balance getConnectionBalance() {
		return connectionBalance;
	}

	public void setConnectionBalance(Balance connectionBalance) {
		this.connectionBalance = connectionBalance;
	}
	
	
}
