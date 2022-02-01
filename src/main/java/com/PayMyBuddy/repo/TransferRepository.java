package com.PayMyBuddy.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.PayMyBuddy.models.Transfer;
import com.PayMyBuddy.models.User;

@Repository
public interface TransferRepository extends JpaRepository<Transfer, Integer> {

	/*
	 * @Query (value= "Select new com.PayMyBuddy.dto.TransactionRecordDto " +
	 * "(b.user as connectionRecord, t.description as description, t.amount as amount) "
	 * + "FROM User u INNER JOIN Connections c ON u.connection = c" +
	 * "INNER JOIN Balance b ON c.connection = b.connection" +
	 * " INNER JOIN Transfer t ON b.transactionsConnection = t.connectionBalance WHERE u=?1 "
	 * ) public List<TransactionRecordDto> getTransactionRecordFromUser(User user);
	 */

	@Query(value = "Select new com.PayMyBuddy.dto.TransactionRecordDto"
			+ "(t as t) FROM Transfer t INNER JOIN User u ON t.userBalance = u.balance WHERE u=?1 ")
	public List<Transfer> getTransactionRecordFromUser(User user);
}
