package com.PayMyBuddy.models;

import java.sql.Timestamp;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
	
	@Column (name = "description")
	private String description;
	
	@ManyToOne 
	@JoinColumn (name = "user_id")
	private User user;
	
	@ManyToOne 
	@JoinColumn (name = "user_balance_id")
	private Balance userBalance;
	
	@ManyToOne 
	@JoinColumn (name ="connection_balance_id")
	private Balance connectionBalance;
	
	@OneToMany (mappedBy = "transferId" , cascade = CascadeType.ALL)
	private Collection< CompanyAccount> compagnyAccount;

	public Transfer( Timestamp dateTime, float amount, String description, Balance userBalance, Balance connectionBalance ) {
		super();
		
		this.dateTime = dateTime;
		this.amount = amount;
		this.description = description;
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


	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public Collection<CompanyAccount> getCompagnyAccount() {
		return compagnyAccount;
	}

	public void setCompagnyAccount(Collection<CompanyAccount> compagnyAccount) {
		this.compagnyAccount = compagnyAccount;
	}

	
	
}
