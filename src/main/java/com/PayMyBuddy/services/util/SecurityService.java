package com.PayMyBuddy.services.util;

/*
 * interface for securityServiceImpl  used to auth User and user's token
 */
public interface SecurityService {
	boolean login(String username, String password);
}
