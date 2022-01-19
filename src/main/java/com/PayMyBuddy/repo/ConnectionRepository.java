package com.PayMyBuddy.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.PayMyBuddy.models.Connections;

@Repository
public interface ConnectionRepository extends JpaRepository<Connections, Integer> {
	
	@Query (value = "SELECT c FROM Connections c WHERE user_username = ?1 and user_id =?2")
	public Connections findByUsername (String username, int currentUserId);
	
	
	@Query (value ="SElECT c FROM Connections c WHERE user_id =?1")
	public List<Connections> getAllConnectionsFromCurrentUser(int id);

	@Query (value ="SELECT case when count(c) =1 then true else false end from Connections c "
			+ "WHERE c.user =?1 AND c.connection = ?2 ")
	public boolean existsWithIds(@Param("?1") int userId, @Param("?2") int connectionId);

}
