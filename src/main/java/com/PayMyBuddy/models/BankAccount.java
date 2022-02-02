package com.PayMyBuddy.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table (name = "Bank_Account")
public class BankAccount {
	
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	private int id;
	
	private String bankAccountNumber;
	
	@ManyToOne 
	@JoinColumn (name = "user_id")
	private User userId;

	public BankAccount(String bankAccountNumber, User userId) {
		super();
		this.bankAccountNumber = bankAccountNumber;
		this.userId = userId;
	}
	public BankAccount() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBankAccountNumber() {
		return bankAccountNumber;
	}

	public void setBankAccountNumber(String bankAccountNumber) {
		this.bankAccountNumber = bankAccountNumber;
	}

	public User getUserid() {
		return userId;
	}

	public void setUserid(User userid) {
		this.userId = userid;
	}

}
