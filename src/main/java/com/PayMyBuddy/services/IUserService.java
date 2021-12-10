package com.PayMyBuddy.services;

import com.PayMyBuddy.dto.UserDTO;
import com.PayMyBuddy.models.User;

public interface IUserService {
	
	User registerNewUserAccount(UserDTO userDto);
	

}
