package com.PayMyBuddy.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table (name = "CompanyAccount")
public class CompanyAccount {
 
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	private int id;
	
	@Column (name="sold")
	private float sold;
	
	@Column (name="bank_account_number")
	private String bankAccountNumber;
	
	@ManyToOne 
	@JoinColumn (name ="payment_id")
	private Payment paymentId;
	
	@ManyToOne  
	@JoinColumn (name ="transfer_id")
	private Transfer transferId;

	public CompanyAccount(float sold, Payment paymentId, Transfer transferId) {
		super();
		this.sold = sold;
		this.paymentId = paymentId;
		this.transferId = transferId;
	}
	public CompanyAccount() {
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
	public float getSold() {
		return sold;
	}

	public void setSold(float sold) {
		this.sold = sold;
	}

	public Payment getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(Payment paymentId) {
		this.paymentId = paymentId;
	}

	public Transfer getTransferId() {
		return transferId;
	}

	public void setTransferId(Transfer transferId) {
		this.transferId = transferId;
	}
}
