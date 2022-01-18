package com.PayMyBuddy.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.PayMyBuddy.models.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	
	public Role findRoleByRoleName (String roleName);

}
