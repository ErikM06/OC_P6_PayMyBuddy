package com.PayMyBuddy.interfaces;

import com.PayMyBuddy.dto.UserDTO;
import com.PayMyBuddy.exceptions.UserAlreadyExistException;
import com.PayMyBuddy.models.User;

public interface IUserService {

	public User registerNewUserAccount(UserDTO userDto) throws UserAlreadyExistException;

	public User findByEmail(String username);

	public User findUserByUsername(String username);

	public void deleteUser(String username);
	
	public void uptadeUser (User user, User cUser);

}
