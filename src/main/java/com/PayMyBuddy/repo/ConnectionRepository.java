package com.PayMyBuddy.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.PayMyBuddy.models.Connections;

@Repository
public interface ConnectionRepository extends CrudRepository<Connections, Integer> {

}
