package com.PayMyBuddy.config;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.PayMyBuddy.models.Balance;
import com.PayMyBuddy.models.Role;
import com.PayMyBuddy.models.User;
import com.PayMyBuddy.repo.BalanceRepository;
import com.PayMyBuddy.repo.RoleRepository;
import com.PayMyBuddy.repo.UserRepository;

@Configuration
@ComponentScan("com.PayMyBuddy.config")
public class InitDbvalues {

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	UserRepository userRepo;

	@Autowired
	BalanceRepository balanceRepo;

	@Autowired
	PasswordEncoder encoder;

	@Bean("initRoles")
	public void initRoles() throws Exception {

		if (roleRepository.count() == 0) {
			Role user = new Role();
			Role admin = new Role();
			user.setRoleName("ROLE_USER");
			admin.setRoleName("ROLE_ADMIN");
			roleRepository.save(user);
			roleRepository.save(admin);
		}
	}

	@Bean("initSomeUsers")
	public void initSomeUsers() {
		List<User> userLs = new ArrayList<User>();
		if (userRepo.count() == 0) {
			User userNathalie = new User("Nathalie", "nathalie@gmail.com", encoder.encode("nathMDP"), null,
					getTimestamp(), true, null);
			User userBob = new User("Bob", "Bob@gmail.com", encoder.encode("BobMDP"), null, getTimestamp(), true, null);
			User userYann = new User("Yann", "Yann@gmail.com", encoder.encode("YannMDP"), null, getTimestamp(), true,
					null);
			User userMarine = new User("Marine", "Marine@gmail.com", encoder.encode("MarineMDP"), null, getTimestamp(),
					true, null);
			User admin1 = new User("admin", "admin@gmail.com", encoder.encode("admin"), null, getTimestamp(), true,
					null);

			userLs.add(userMarine);
			userLs.add(userYann);
			userLs.add(userBob);
			userLs.add(userNathalie);
			userLs.add(admin1);
			userRepo.saveAll(userLs);
		}

	}

	@Bean("initBalances")

	public void setUsersBalance() {
		if (balanceRepo.count() == 0) {
			List<User> userLs = userRepo.findAll();
			List<User> userBalanceSet = new ArrayList<User>();
			String adminStr = "admin";
			for (User user : userLs) {
				user.setBalance(new Balance((float) 1000, user));
				if (user.getUsername().equals(adminStr)) {
					user.setRoles(roleRepository.findRoleByRoleName("ROLE_ADMIN"));
				} else {
					user.setRoles(roleRepository.findRoleByRoleName("ROLE_USER"));
				}
				userBalanceSet.add(user);
			}
			userRepo.saveAll(userBalanceSet);
		}
	}

	private Timestamp getTimestamp() {
		return new Timestamp(System.currentTimeMillis());
	}

}
