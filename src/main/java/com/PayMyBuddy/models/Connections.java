package com.PayMyBuddy.models;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import javax.persistence.JoinColumn;

@Entity
@Table(name = "connections")
public class Connections implements Serializable {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "create_time")
	private Timestamp createTime;

	// can be null
	@Column(name = "note")
	private String note;

	@Column (name = "user_id")
	private int userId;

	@ManyToMany
	private Collection<User> connections = new ArrayList<>();

	public Connections(Timestamp createTime, String note, int userId, List<User> connection) {
		super();
		this.createTime = createTime;
		this.note = note;
		this.userId = userId;
		this.connections = connection;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Collection<User> getConnection() {
		return connections;
	}

	public void setConnection(Collection<User> connection) {
		this.connections = connection;
	}

}
