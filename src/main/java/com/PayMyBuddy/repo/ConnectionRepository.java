package com.PayMyBuddy.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.PayMyBuddy.models.Connections;

@Repository
public interface ConnectionRepository extends JpaRepository<Connections, Integer> {
	
	@Query (value = "SELECT c FROM Connections c WHERE user_username = ?1 and user_id =?2")
	public Connections findByUsername (String username, int currentUserId);

}
