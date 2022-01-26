package com.PayMyBuddy.services;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.PayMyBuddy.dto.UserDTO;
import com.PayMyBuddy.exceptions.UserAlreadyExistException;
import com.PayMyBuddy.interfaces.IBalanceService;
import com.PayMyBuddy.interfaces.IConnectionService;
import com.PayMyBuddy.interfaces.IUserService;
import com.PayMyBuddy.models.Balance;
import com.PayMyBuddy.models.Connections;
import com.PayMyBuddy.models.Role;
import com.PayMyBuddy.models.User;
import com.PayMyBuddy.repo.RoleRepository;
import com.PayMyBuddy.repo.UserRepository;
import com.PayMyBuddy.services.util.GetCurrentUser;

@Service
public class UserService implements IUserService {

	Logger logger = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private RoleRepository roleRepo;

	@Autowired
	private IBalanceService iBalanceService;


	// method from Spring doc to avoid circular references error

	public User registerNewUserAccount(UserDTO userDto) throws UserAlreadyExistException {
		User user = new User();
		List<Role> userRoleLs = new ArrayList<>();
		List<Balance> userBalance = new ArrayList<>();
		if (emailExists(userDto.getEmail())) {
			logger.info("ok");
			throw new UserAlreadyExistException("user already exist " + userDto.getEmail());
		} else {
			user.setUsername(userDto.getUsername());
			user.setPassword(passwordEncoder().encode(userDto.getPassword()));
			user.setEmail(userDto.getEmail());
			user.setCreateTime(new Timestamp(System.currentTimeMillis()));
			user.setEnable(true);
			userRoleLs.add(roleRepo.findRoleByRoleName("ROLE_USER"));
			userBalance.add(iBalanceService.initBalance(user));
			user.setRoles(userRoleLs);
			user.setBalance(userBalance);
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

	@Bean
	public PasswordEncoder passwordEncoder() {
		logger.info("in passwordEncoder @Bean");
		return new BCryptPasswordEncoder();
	}

}
