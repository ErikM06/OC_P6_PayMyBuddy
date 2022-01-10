package com.PayMyBuddy.models;

import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Balance")
public class Balance {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column (name ="amount")
	private double amount;
	
	@ManyToOne
	@JoinColumn (name = "user_id")
	private User user;
	
	@OneToMany (mappedBy = "balance", fetch = FetchType.LAZY)
	private Collection<Transaction> transactions;

	public Balance(double amount, User user) {
		super();
		this.amount = amount;
		this.user = user;
	}
	public Balance() {
		super();
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

	public User getUser() {
		return user;
	}

	public void setuser(User user) {
		this.user = user;
	}

}
