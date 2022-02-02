package com.PayMyBuddy.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.PayMyBuddy.models.Transfer;
import com.PayMyBuddy.models.User;

@Repository
public interface TransferRepository extends JpaRepository<Transfer, Integer> {

	@Query(value = "Select t FROM Transfer t INNER JOIN User u ON t.userBalance = u.balance WHERE u=?1 ")
	public List<Transfer> getTransactionRecordFromUser(User user);
}
