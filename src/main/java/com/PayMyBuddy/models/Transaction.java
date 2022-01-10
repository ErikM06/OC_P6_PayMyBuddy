package com.PayMyBuddy.models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table (name = "Transaction")
public class Transaction {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private double id;
	
	@Column
	private Timestamp dateTime;
	
	@Column (name = "transaction_amount")
	private double amount;
	
	@ManyToOne
	@JoinColumn (name = "balance_id")
	private Balance balance;

	public Transaction( Timestamp dateTime, double amount, Balance balance) {
		super();
		
		this.dateTime = dateTime;
		this.amount = amount;
		this.balance = balance;
	}
	
	public Transaction() {
		super();
	}

	public double getId() {
		return id;
	}

	public void setId(double id) {
		this.id = id;
	}


	public Timestamp getDateTime() {
		return dateTime;
	}

	public void setDateTime(Timestamp dateTime) {
		this.dateTime = dateTime;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Balance getBalance() {
		return balance;
	}

	public void setBalance(Balance balance) {
		this.balance = balance;
	}
	
	
}
