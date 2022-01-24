package com.PayMyBuddy.dto;


public class ConnectionDTO {
	
	private String email;
	
	private String note;
	
	private String connectionUsername;
	
	private int connectionId;

	public ConnectionDTO(String email, String note) {
		super();
		this.email = email;
		this.note = note;
	}

	public ConnectionDTO() {
		// TODO Auto-generated constructor stub
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getConnectionUsername() {
		return connectionUsername;
	}

	public void setConnectionUsername(String connectionUsername) {
		this.connectionUsername = connectionUsername;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}


	public int getConnectionId() {
		return connectionId;
	}

	public void setConnectionId(int connectionId) {
		this.connectionId = connectionId;
	}

}
