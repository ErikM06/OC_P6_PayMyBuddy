package com.PayMyBuddy.services;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.PayMyBuddy.dto.UserDTO;
import com.PayMyBuddy.models.Role;
import com.PayMyBuddy.models.User;
import com.PayMyBuddy.repo.RoleRepository;
import com.PayMyBuddy.repo.UserRepository;

@Service
@Transactional
public class UserService implements IUserService {
	
	Logger logger = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private RoleRepository roleRepo;
	
	
	private PasswordEncoder passwordEncoder;
	
	// method from Spring doc to avoid circulare references error
	@Autowired
	private void passwordEncodage(PasswordEncoder passwordEncoder) {
		logger.debug("in PasswordEncoder setter ");
		this.passwordEncoder = passwordEncoder;
		
	} 
	

	public User registerNewUserAccount(UserDTO userDto) {
		if (emailExists(userDto.getEmail())) {
			// code for UserAlreadyExistException
		}
		
		List<Role> userRoleLs = new ArrayList<>();
		userRoleLs.add(roleRepo.findRoleByRoleName("USER"));
		

		User user = new User();
		user.setUsername(userDto.getUsername());
		user.setPassword(passwordEncoder.encode(userDto.getPassword()));
		user.setEmail(userDto.getEmail());
		user.setCreateTime(new Timestamp(System.currentTimeMillis()));
		user.setEnable(true);
		user.setRoles(userRoleLs);
		logger.info("UserService user is: "+user);
		return userRepo.save(user);
	}

	private boolean emailExists(String email) {
		return userRepo.findByEmail(email) != null;
	}
	
	public User findByUsername (String username) {
		return userRepo.findUserByUsername(username);
	}

}
