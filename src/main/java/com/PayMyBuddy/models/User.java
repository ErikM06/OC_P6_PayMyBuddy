package com.PayMyBuddy.models;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.JoinColumn;

@DynamicUpdate
@SelectBeforeUpdate
@Entity
@Table (name="user")

public class User implements UserDetails {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	@Column (name = "id")
	private int id;
	
	@Column (name = "username")
	private String username;
	
	@Column (name ="email")
	private String email;
	
	@Column (name ="password")
	protected String password;
	
	
	@ManyToOne (cascade = CascadeType.ALL)
	@JoinColumn(name="RoleId")
    private Role roles;
	
	@OneToMany( mappedBy = "user", cascade = CascadeType.ALL)
	private List<Connections> user;
	
	@OneToMany( mappedBy = "connection", cascade = CascadeType.ALL)
	private List<Connections> connection;
	
	@OneToOne (mappedBy = "user", cascade = CascadeType.MERGE)
	@PrimaryKeyJoinColumn
	private Balance balance;
	
	@OneToMany (mappedBy ="userId", cascade = CascadeType.ALL)
	private Collection<BankAccount> bankAccount;
	
	@Column (name ="create_time")
	private Timestamp createTime;
	
	
	@Column (name ="enable")
	private boolean enable;

	
	
	public User(String username, String email, String password, Role roles, Timestamp createTime,
			boolean enable, Balance balance) {
		super();
		this.username = username;
		this.email = email;
		this.password = password;
		this.roles = roles;
		this.createTime = createTime;
		this.enable = enable;
		this.balance = balance;
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

	public Role getRoles() {
		return roles;
	}

	public void setRoles(Role roles) {
		this.roles = roles;
	}

	public List<Connections> getConnection() {
		return connection;
	}

	public void setConnection(List<Connections> connection) {
		this.connection = connection;
	}

	public Balance getBalance() {
		return balance;
	}

	public void setBalance(Balance balance) {
		this.balance = balance;
	}

	public List<Connections> getUser() {
		return user;
	}

	public void setUser(List<Connections> user) {
		this.user = user;
	}


	public Collection<BankAccount> getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(Collection<BankAccount> bankAccount) {
		this.bankAccount = bankAccount;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
} 
