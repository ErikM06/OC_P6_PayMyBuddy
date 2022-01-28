package com.PayMyBuddy.config;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.PayMyBuddy.models.Balance;
import com.PayMyBuddy.models.Role;
import com.PayMyBuddy.models.User;
import com.PayMyBuddy.repo.UserRepository;

@Configuration
public class initUsers {
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	PasswordEncoder encoder;
	
	@PostConstruct
	@DependsOn("initRoles")
	public void initSomeUsers() {
		if (userRepo.count() == 0) {
			Role user = new Role();
			Balance balance1000 = new Balance();

			List<Role> roleUser = new ArrayList<>();
			user.setRoleName("ROLE_USER");
			roleUser.add(user);

			List<Balance> userBalance = new ArrayList<>();
			balance1000.setAmount(1000);
			userBalance.add(balance1000);

			User userNathalie = new User("Nathalie", "nathalie@gmail.com", encoder.encode("nathMDP"), user, getTimestamp(), true,
					userBalance);
			User userBob = new User("Bob", "Bob@gmail.com", encoder.encode("BobMDP"), user, getTimestamp(), true, userBalance);
			User userYann = new User("Yann", "Yann@gmail.com", encoder.encode("YannMDP"), user, getTimestamp(), true, userBalance);
			User userMarine = new User("Marine", "Marine@gmail.com", encoder.encode("MarineMDP"), user, getTimestamp(), true,
					userBalance);

			Role admin = new Role();
			admin.setRoleName("ROLE_ADMIN");
			List<Role> roleAdmin = new ArrayList<>();
			roleAdmin.add(admin);
			User admin1 = new User("admin", "admin@gmail.com", encoder.encode("admin"), admin, getTimestamp(), true, null);
			
			List<User> userLs= new ArrayList<User>();
			userLs.add(userMarine);
			userLs.add(userYann);
			userLs.add(userBob);
			userLs.add(userNathalie);
			userLs.add(admin1);
			
			userRepo.saveAll(userLs);
		}
			
	}

	private Timestamp getTimestamp() {
		return new Timestamp(System.currentTimeMillis());
	}

}
