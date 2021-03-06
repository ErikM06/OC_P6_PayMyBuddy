package com.PayMyBuddy.services;

import java.sql.Timestamp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.PayMyBuddy.dto.UserDTO;
import com.PayMyBuddy.exceptions.UserAlreadyExistException;
import com.PayMyBuddy.interfaces.IBalanceService;
import com.PayMyBuddy.interfaces.IUserService;
import com.PayMyBuddy.models.User;
import com.PayMyBuddy.repo.RoleRepository;
import com.PayMyBuddy.repo.UserRepository;

@Service
public class UserService implements IUserService {

	Logger logger = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private RoleRepository roleRepo;

	@Autowired
	private IBalanceService iBalanceService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	public User registerNewUserAccount(UserDTO userDto) throws UserAlreadyExistException {
		User user = new User();
		
		if (emailExists(userDto.getEmail())) {
			logger.info("ok");
			throw new UserAlreadyExistException("user already exist " + userDto.getEmail());
		} else {
			user.setUsername(userDto.getUsername());
			user.setPassword(passwordEncoder.encode(userDto.getPassword()));
			user.setEmail(userDto.getEmail());
			user.setCreateTime(new Timestamp(System.currentTimeMillis()));
			user.setEnable(true);
			
			user.setRoles(roleRepo.findRoleByRoleName("ROLE_USER"));
			user.setBalance(iBalanceService.initBalance(user));
			logger.info("UserService user is: " + user);

			return userRepo.save(user);
		}
	}

	// Check if email already exist
	private boolean emailExists(String email) {
		logger.info(email);
		return userRepo.findEmail(email) != null;
	}

	public User findByEmail(String email) {
		return userRepo.findUserByEmail(email);
	}

	public void deleteUser(String username) {
		User user = findByEmail(username);
		userRepo.deleteById(user.getId());
		logger.info(username, "deleted");
	}

	public User findUserByUsername(String username) {
		return userRepo.findByUsername(username);
	}
	
	 public void uptadeUser (User user, User cUser) {
		cUser.setUsername(user.getUsername());
		cUser.setEmail(user.getEmail());
		cUser.setPassword(passwordEncoder.encode(user.getPassword()));
		userRepo.save(cUser);
	} 

	
}
