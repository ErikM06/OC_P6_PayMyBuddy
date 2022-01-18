package com.PayMyBuddy.services;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.PayMyBuddy.dto.ConnectionDTO;
import com.PayMyBuddy.models.Connections;

public interface IConnectionService {
	
	public Connections addConnections(ConnectionDTO connectionDTO) throws UsernameNotFoundException;
	
	public void deleteConnection (String username ) throws UsernameNotFoundException; 

}
