package com.PayMyBuddy.config;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.PayMyBuddy.models.Role;
import com.PayMyBuddy.repo.RoleRepository;

@Configuration
public class InitRole {
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	initUsers initUsers;
	
	@PostConstruct
	public void initRole() throws Exception {

		if (roleRepository.count() == 0) {
			Role user = new Role();
			Role admin = new Role();
			user.setRoleName("ROLE_USER");
			admin.setRoleName("ROLE_ADMIN");
			roleRepository.save(user);
			roleRepository.save(admin);	
		}
	}

}
