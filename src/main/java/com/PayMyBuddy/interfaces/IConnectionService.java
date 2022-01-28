package com.PayMyBuddy.interfaces;

import java.util.List;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.PayMyBuddy.dto.ConnectionDTO;
import com.PayMyBuddy.models.Connections;
import com.PayMyBuddy.models.User;
import com.PayMyBuddy.services.util.GetCurrentUser;

public interface IConnectionService {
	
	public boolean assertConnection (User user, User connection);

	public Connections addConnections(ConnectionDTO connectionDTO, GetCurrentUser currentUser) throws UsernameNotFoundException;
	
	public void deleteConnection (String username, GetCurrentUser currentUser ) throws Exception;
	
	public List<Connections> getAllConnections (GetCurrentUser currentUser) throws Exception;
	
	public List<User> getConnectionsAsUserLs(GetCurrentUser currentUser) throws Exception;

}
