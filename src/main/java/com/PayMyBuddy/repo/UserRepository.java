package com.PayMyBuddy.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.PayMyBuddy.models.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
	
	@Query (value = "Select u FROM User u WHERE u.username =?1 AND u.password =?2")
	public Optional<User> findUserByLogs (String username, String password);
	
	@Query(value = "Select email FROM User u WHERE u.email=?1")
	public Object findByEmail(String email); 
	
	@Query(value ="Select u FROM User u WHERE u.id =?1")
	public User findUserById (int id);
}
