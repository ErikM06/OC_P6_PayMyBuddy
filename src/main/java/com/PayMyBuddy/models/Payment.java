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
@Table (name = "Payment")
public class Payment {
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	private int id;
	
	@Column
	private float amount;
	
	@Column
	private Timestamp dateTime;
	
	@ManyToOne (cascade = CascadeType.ALL)
	@JoinColumn (name ="bank_Account_id")
	private BankAccount bankAccount;
	
	@ManyToOne (cascade = CascadeType.ALL)
	@JoinColumn (name = "user_balance_id")
	private Balance userBalance; 
	
	@ManyToOne (cascade = CascadeType.ALL)
	@JoinColumn (name ="user_id")
	private User userId;
	
	@Column (name = "payment_direction")
	private String paymentDirection;

	public Payment(float amount, Timestamp dateTime, BankAccount bankAccount, User userId, String paymentDirection) {
		super();
		this.amount = amount;
		this.dateTime = dateTime;
		this.bankAccount = bankAccount;
		this.userId = userId;
		this.paymentDirection = paymentDirection;
	}
	public Payment () {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public Timestamp getDateTime() {
		return dateTime;
	}
	public void setDateTime(Timestamp dateTime) {
		this.dateTime = dateTime;
	}
	public Balance getUserBalance() {
		return userBalance;
	}
	public void setUserBalance(Balance userBalance) {
		this.userBalance = userBalance;
	}
	public BankAccount getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(BankAccount bankAccount) {
		this.bankAccount = bankAccount;
	}

	public User getUserId() {
		return userId;
	}

	public void setUserId(User userId) {
		this.userId = userId;
	}

	public String getPaymentDirection() {
		return paymentDirection;
	}

	public void setPaymentDirection(String paymentDirection) {
		this.paymentDirection = paymentDirection;
	}
	
	
	
	
}
