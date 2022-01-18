package com.PayMyBuddy.services.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.PayMyBuddy.models.User;
import com.PayMyBuddy.services.TransactionService;

@Component
public class GetCurrentUser {
	
	Logger logger = LoggerFactory.getLogger(TransactionService.class);
	
	public String getCurrentUser() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		String username = ((User) principal).getUsername();
		logger.info("username is: {}",username);
		return username;
	}


}
