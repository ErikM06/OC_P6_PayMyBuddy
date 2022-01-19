package com.PayMyBuddy.services;

import com.PayMyBuddy.dto.UserDTO;
import com.PayMyBuddy.exceptions.UserAlreadyExistException;
import com.PayMyBuddy.models.User;

public interface IUserService {
	
	public User registerNewUserAccount(UserDTO userDto) throws UserAlreadyExistException, Exception;
	
	public User findByEmail(String username);
	
	public User findUserByUsername (String username);
	
	public void deleteUser (String username);
	
	

}
