package com.PayMyBuddy.models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table (name="user")
@Data
public class User {

	@Id
	@GeneratedValue
	private int id;
	
	@Column (name = "username")
	private String username;
	
	@Column (name ="email")
	private String email;
	
	@Column (name ="password")
	protected String password;
	
	@Column (name ="create_time")
	private Timestamp createTime;

	public User(String username, String email, String password, Timestamp createTime) {
		super();
		this.username = username;
		this.email = email;
		this.password = password;
		this.createTime = createTime;
	}
	
	public User () {
		
	}
	
} 
