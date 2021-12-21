package com.PayMyBuddy.services;

import java.sql.Timestamp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PayMyBuddy.dto.ConnectionDTO;
import com.PayMyBuddy.models.Connections;
import com.PayMyBuddy.repo.ConnectionRepository;
import com.PayMyBuddy.repo.UserRepository;

@Service
public class ConnectionServices {
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired 
	ConnectionRepository connectionRepo;
	
	Logger logger = LoggerFactory.getLogger(ConnectionServices.class);
	

	//where userId is the logged user's id
	public Connections addNewConnection(ConnectionDTO connectionDTO) {
		
		Connections connection = new Connections();
		connection.setConnection(userRepo.findUserById(connectionDTO.getUserId()));
		connection.setCreateTime(new Timestamp(System.currentTimeMillis()));
		connection.setNote(null);
		/* this user
		connection.setUser();
		*/
		return connectionRepo.save(connection);
		
		
	}
	
	

}
