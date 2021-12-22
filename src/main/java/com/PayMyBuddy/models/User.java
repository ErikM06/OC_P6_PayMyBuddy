package com.PayMyBuddy.models;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;


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
	
	@ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "Id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "roleId"))
    private List<Role> roles;
	
	@OneToMany( mappedBy = "user")
	private Collection<Connections> user;
	
	@OneToMany( mappedBy = "connection")
	private Collection<Connections> connection;
	
	@Column (name ="create_time")
	private Timestamp createTime;
	
	@Column (name ="enable")
	private boolean enable;

	
	
	public User(String username, String email, String password, List<Role> roles, Timestamp createTime,
			boolean enable) {
		super();
		this.username = username;
		this.email = email;
		this.password = password;
		this.roles = roles;
		this.createTime = createTime;
		this.enable = enable;
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

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	
} 
