package com.PayMyBuddy.interfaces;

import java.util.List;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.PayMyBuddy.dto.ConnectionDTO;
import com.PayMyBuddy.models.Connections;
import com.PayMyBuddy.models.User;

public interface IConnectionService {
	
	public boolean assertConnection (User user, User connection);

	public Connections addConnections(ConnectionDTO connectionDTO) throws UsernameNotFoundException;
	
	public void deleteConnection (String username ) throws Exception;
	
	public List<Connections> getAllConnections () throws Exception;
	
	public List<User> getConnectionsAsUserLs() throws Exception;

}
