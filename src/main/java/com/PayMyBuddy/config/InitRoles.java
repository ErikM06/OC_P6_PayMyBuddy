package com.PayMyBuddy.config;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import com.PayMyBuddy.models.Role;
import com.PayMyBuddy.repo.RoleRepository;

@Configuration
public class InitRoles {

	@Autowired
	RoleRepository roleRepository;

	@PostConstruct
	public void initRole() throws Exception {

		if (roleRepository.count() == 0) {
			Role user = new Role();
			Role admin = new Role();
			user.setRoleName("USER");
			admin.setRoleName("ADMIN");
			roleRepository.save(user);
			roleRepository.save(admin);

		}
	}

}
