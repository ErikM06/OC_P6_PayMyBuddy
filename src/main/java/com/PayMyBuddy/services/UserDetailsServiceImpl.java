package com.PayMyBuddy.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.PayMyBuddy.models.User;
import com.PayMyBuddy.services.UserDetailsServiceImpl;

/*
 * implementation of UserDetailsService found on 
 * https://github.com/arshadalisoomro/secure-spring-demo/blob/master/src/main/java/pk/edu/suk/service/UserDetailsServiceImpl.java
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);
	
	private final UserService userService;
	

	@Autowired
    public UserDetailsServiceImpl(UserService userService) {
        this.userService = userService;
    }
        
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userService.findByUsername(username);
		logger.info("in UserDetailsServiceImpl");
		if (user == null) {
			throw new UsernameNotFoundException("user with "+username+" not found!");
		}
		
		return new org.springframework.security.core.userdetails.User(
                user.getEmail(), user.getPassword(), user.getRoles());
	}

}
