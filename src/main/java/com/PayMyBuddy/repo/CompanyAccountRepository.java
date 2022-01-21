package com.PayMyBuddy.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.PayMyBuddy.models.CompanyAccount;

@Repository
public interface CompanyAccountRepository extends JpaRepository<CompanyAccount, Integer>{

}
