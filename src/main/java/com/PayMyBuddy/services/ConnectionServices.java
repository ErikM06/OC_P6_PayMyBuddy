package com.PayMyBuddy.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.PayMyBuddy.dto.ConnectionDTO;
import com.PayMyBuddy.models.Connections;

@Service
public class ConnectionServices {
	
	Logger logger = LoggerFactory.getLogger(ConnectionServices.class);
	

	//where userId is the logged user's id
	public Connections addNewConnection(ConnectionDTO connectionDTO) {
		
		// Connections connection = new Connections();
	//	connection.setConnection(connectionDTO.getUsername());
		
		return null;
		
	}
	
	

}
