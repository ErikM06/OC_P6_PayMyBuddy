package com.PayMyBuddy.services.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.PayMyBuddy.models.User;

@Component
public class GetCurrentUser {
	
	Logger logger = LoggerFactory.getLogger(GetCurrentUser.class);
	
	public String getCurrentUser() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		String email = ((User) principal).getEmail();
		logger.info("username is: {}",email);
		return email;
	}


}
