package com.PayMyBuddy.models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table (name = "connections")
public class Connections {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "create_time")
	private Timestamp createTime;

	// can be null
	@Column(name = "note")
	private String note;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne
	@JoinColumn(name = "connection_id")
	private User connection;

	public Connections(Timestamp createTime, String note, User user, User connection) {
		super();
		this.createTime = createTime;
		this.note = note;
		this.user = user;
		this.connection = connection;
	}
	
	public Connections() {
		super();
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public User getConnection() {
		return connection;
	}

	public void setConnection(User connection) {
		this.connection = connection;
	}

}
