package com.PayMyBuddy.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.PayMyBuddy.models.BankAccount;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, Integer> {

	BankAccount findByBankAccountNumber(String bankAccountNumber);
	
	@Query (value =" SELECT b FROM BankAccount b INNER JOIN User u ON u.id = b.userId WHERE u.email = ?1 ")
	List<BankAccount> findAllForCurrentUser(String currentUser);
	
	@Query (value ="SELECT case when count(b) =1 then true else false end from BankAccount b "
			+ "WHERE b.bankAccountNumber =?1 ")
	public boolean existsWithBankAccountNumber(String bankAccountNumber);

}
