package com.PayMyBuddy.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.PayMyBuddy.models.Balance;
import com.PayMyBuddy.models.User;

public interface BalanceRepository extends JpaRepository<Balance, Double>{
	
	@Query(value ="Select b FROM Balance b INNER JOIN User u ON b.user = u.id WHERE u = ?1")
	public Balance getBalanceByUser(User user);
	

}
