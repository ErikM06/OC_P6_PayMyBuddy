package com.PayMyBuddy.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.PayMyBuddy.models.Connections;
import com.PayMyBuddy.models.User;

@Repository
public interface ConnectionRepository extends JpaRepository<Connections, Integer> {
	
	@Query (value = "SELECT c FROM Connections c WHERE c.user =?1 and c.connection = ?2")
	public List<Connections> selectByUsers (User user, User connection);
	
	@Modifying
	@Query (value ="DELETE FROM Connections c WHERE c.id = ?1")
	public void deleteConnectionById (int connectionId);
	
	@Query (value ="SElECT c FROM Connections c WHERE c.user =?1")
	public List<Connections> getAllConnectionsFromCurrentUser(User user);

	@Query (value ="SELECT case when count(c) =1 then true else false end from Connections c "
			+ "WHERE c.user =?1 AND c.connection = ?2 ")
	public boolean existsWithIds(@Param("?1") User user, @Param("?2") User connection);

}
