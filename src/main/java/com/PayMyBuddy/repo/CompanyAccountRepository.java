package com.PayMyBuddy.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.PayMyBuddy.models.CompanyAccount;

@Repository
public interface CompanyAccountRepository extends JpaRepository<CompanyAccount, Integer>{
	
		@Query (value ="SELECT c FROM CompanyAccount c WHERE id=(SELECT max(id) FROM CompanyAccount)")
		public CompanyAccount getLastCompanyAccountOperation ();
}
