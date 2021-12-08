package com.PayMyBuddy.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.PayMyBuddy.models.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

}
