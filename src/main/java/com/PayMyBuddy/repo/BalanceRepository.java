package com.PayMyBuddy.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.PayMyBuddy.models.Balance;

@Repository
public interface BalanceRepository extends JpaRepository<Balance, Integer> {

}
