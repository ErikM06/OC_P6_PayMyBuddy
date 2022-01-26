package com.PayMyBuddy.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.PayMyBuddy.dto.TransactionRecordDto;
import com.PayMyBuddy.models.Transfer;
import com.PayMyBuddy.models.User;

@Repository
public interface TransferRepository extends JpaRepository<Transfer, Integer> {

	@Query (value= "Select distinct new com.PayMyBuddy.dto.TransactionRecordDto "
			+ "( t as description, t as amount) "
			+ "FROM Transfer t INNER JOIN Balance b ")
	public List<TransactionRecordDto> getTransactionRecordFromUser(User user);
	
	 
} 
