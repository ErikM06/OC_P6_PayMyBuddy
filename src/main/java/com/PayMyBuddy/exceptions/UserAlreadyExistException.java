package com.PayMyBuddy.exceptions;

import javax.naming.AuthenticationException;

@SuppressWarnings("serial")
public class UserAlreadyExistException extends AuthenticationException{
	
	public UserAlreadyExistException (final String str) {
		super(str);
	}

}
