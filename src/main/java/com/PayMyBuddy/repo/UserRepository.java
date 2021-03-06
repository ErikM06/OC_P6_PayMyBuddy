package com.PayMyBuddy.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.PayMyBuddy.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	
	public User findUserByEmail (String email);
	
	@Query (value = "Select u FROM User u WHERE u.username =?1 AND u.password =?2")
	public Optional<User> findUserByLogs (String username, String password);
	
	@Query(value = "Select email FROM User u WHERE u.email=?1")
	public String findEmail(String email); 
	
	@Query(value ="Select u FROM User u WHERE u.id =?1")
	public User findUserById (int id);
	
	@Query (value ="Select u FROM User u WHERE u.username =?1")
	public User findByUsername(String username);
	
	@Query(value ="SELECT * FROM User  WHERE id != ?1 AND username != 'admin' ORDER BY RAND() limit 1", nativeQuery = true)
	public User getRandomUser(int id);
	 
}
