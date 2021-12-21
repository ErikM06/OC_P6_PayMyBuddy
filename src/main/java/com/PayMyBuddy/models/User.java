package com.PayMyBuddy.models;

import java.sql.Timestamp;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table (name="user")

public class User {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column (name = "username")
	private String username;
	
	@Column (name ="email")
	private String email;
	
	@Column (name ="password")
	protected String password;
	
	@OneToMany( mappedBy = "user")
	private Collection<Connections> user;
	
	@OneToMany( mappedBy = "connection")
	private Collection<Connections> connection;
	
	@Column (name ="create_time")
	private Timestamp createTime;
	
	@Column (name ="enable")
	private boolean enable;

	public User(String username, String email, String password, Timestamp createTime) {
		super();
		this.username = username;
		this.email = email;
		this.password = password;
		this.createTime = createTime;
	}
	
	public User () {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}
	
} 
