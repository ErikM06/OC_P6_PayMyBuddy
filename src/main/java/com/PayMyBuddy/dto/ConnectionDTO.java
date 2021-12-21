package com.PayMyBuddy.dto;

import java.sql.Timestamp;

import org.springframework.stereotype.Component;

@Component
public class ConnectionDTO {
	
	private String username;
	
	private String note;
	
	private Timestamp createTime;
	
	private int userId;
	
	private int connectionId;

	public ConnectionDTO(String username, String note) {
		super();
		this.username = username;
		this.note = note;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getConnectionId() {
		return connectionId;
	}

	public void setConnectionId(int connectionId) {
		this.connectionId = connectionId;
	}

}
