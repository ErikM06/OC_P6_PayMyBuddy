package com.PayMyBuddy.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.PayMyBuddy.models.Balance;
import com.PayMyBuddy.models.Transfer;

@Repository
public interface TransferRepository extends JpaRepository<Transfer, Double> {
	
	 /* @Query (value ="UPDATE User u SET balance.u = ?1 "
			+ "INNER JOIN Balance b "
			+ "ON user.id = balance.user_id WHERE user.username = ?2 ") 
	public Transaction payment (double amount, String connectionUsername); */
} 
