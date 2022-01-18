package com.PayMyBuddy.models;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table (name = "Bank_Account")
public class BankAccount {
	
	private int id;
	
	private String bankAccountNumber;
	
	private Collection<User> userid;

	public BankAccount(String bankAccountNumber, Collection<User> userid) {
		super();
		this.bankAccountNumber = bankAccountNumber;
		this.userid = userid;
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

	public Collection<User> getUserid() {
		return userid;
	}

	public void setUserid(Collection<User> userid) {
		this.userid = userid;
	}

}
