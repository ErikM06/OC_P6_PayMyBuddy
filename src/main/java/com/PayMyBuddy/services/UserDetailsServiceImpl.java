package com.PayMyBuddy.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.PayMyBuddy.models.User;

/*
 * implementation of UserDetailsService found on 
 * https://github.com/arshadalisoomro/secure-spring-demo/blob/master/src/main/java/pk/edu/suk/service/UserDetailsServiceImpl.java
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	
	private final UserService userService;
	
	@Autowired
    public UserDetailsServiceImpl(UserService userService) {
        this.userService = userService;
	}
        
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userService.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("user with "+username+" not found!");
		}
		// still need to impl roles services
		return new org.springframework.security.core.userdetails.User(
                user.getEmail(), user.getPassword(), null);
	}

}
