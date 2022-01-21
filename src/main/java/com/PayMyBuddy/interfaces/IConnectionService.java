package com.PayMyBuddy.interfaces;

import java.util.List;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.PayMyBuddy.dto.ConnectionDTO;
import com.PayMyBuddy.models.Connections;

public interface IConnectionService {
	
	public boolean assertConnection (int userId, int connectionId);

	public Connections addConnections(ConnectionDTO connectionDTO) throws UsernameNotFoundException;
	
	public void deleteConnection (String username ) throws UsernameNotFoundException;
	
	public List<Connections> getAllConnections ();

}
