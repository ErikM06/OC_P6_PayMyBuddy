package com.PayMyBuddy.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String roleName;
	
	@OneToMany (mappedBy = "roles", cascade = CascadeType.ALL )
	@Column(name="userId")
	private List<User> user;

	public Role(String roleName) {
		this.roleName = roleName;
	}
	
	public Role () {
		
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public int getRoleId() {
		return id;
	}

	public void setRoleId(int id) {
		this.id = id;
	}


}
