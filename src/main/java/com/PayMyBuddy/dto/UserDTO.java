package com.PayMyBuddy.dto;

import java.sql.Timestamp;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

import com.PayMyBuddy.services.util.PasswordMatches;

@PasswordMatches
@Component
public class UserDTO {
	
	@NotNull (message = "Username cannot be null")
	@NotEmpty
	private String username;
	
	@NotNull
	@NotEmpty
	private String password;
	@NotNull
	@NotEmpty
	private String matchingPassword;
	@NotNull
	@NotEmpty
	@Email (message = "email should be valid")
	private String email;

	private Timestamp createTime;

	private boolean enable;

	public UserDTO(String username, String password, String matchingPassword, String email) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.matchingPassword = matchingPassword;
		
	}
	public UserDTO () {
		super();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMatchingPassword() {
		return matchingPassword;
	}

	public void setMatchingPassword(String matchingPassword) {
		this.matchingPassword = matchingPassword;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
