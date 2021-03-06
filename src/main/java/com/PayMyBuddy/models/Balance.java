package com.PayMyBuddy.models;

import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Balance")
public class Balance {

	@Id
	@Column(name="user_id")
	private int id;
	
	@Column (name ="amount")
	private float amount;
	
	@OneToOne 
	@MapsId
	@JoinColumn (name = "user_id")
	private User user;
	
	@OneToMany (mappedBy = "userBalance", cascade = CascadeType.ALL)
	private Collection<Transfer> transactionsUser;
	
	@OneToMany (mappedBy = "connectionBalance", cascade = CascadeType.ALL)
	private Collection<Transfer> transactionsConnection;

	public Balance(float amount, User user) {
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

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public User getUser() {
		return user;
	}

	public void setuser(User user) {
		this.user = user;
	}

}
