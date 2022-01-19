package com.PayMyBuddy.services;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.PayMyBuddy.dto.ConnectionDTO;
import com.PayMyBuddy.models.Connections;
import com.PayMyBuddy.models.User;
import com.PayMyBuddy.repo.ConnectionRepository;
import com.PayMyBuddy.services.util.GetCurrentUser;

@Service
public class ConnectionService  implements IConnectionService{

	@Autowired
	ConnectionRepository connectionRepository;

	@Autowired
	IUserService iUserService;

	@Autowired
	GetCurrentUser currentUser;

	public Connections addConnections(ConnectionDTO connectionDTO) throws UsernameNotFoundException {

		Connections connection = new Connections();

		connection.setUser(iUserService.findByUsername(currentUser.getCurrentUser()));
		connection.setConnection(iUserService.findByUsername(connectionDTO.getUsername()));
		connection.setCreateTime(new Timestamp(System.currentTimeMillis()));
		connection.setNote(connectionDTO.getNote());
		
		connectionRepository.save(connection);
		return connection;

	}
	
	public void deleteConnection (String connectionUsername) throws UsernameNotFoundException {
		String currrentUsername = currentUser.getCurrentUser();
		User currentUser = iUserService.findByUsername(currrentUsername);
		Connections connection = connectionRepository.findByUsername(connectionUsername, currentUser.getId());
		connectionRepository.delete(connection);
	}
	
	public List<Connections> getAllConnections () {
		String currrentUsername = currentUser.getCurrentUser();
		User currentUser = iUserService.findByUsername(currrentUsername);
		List <Connections> allConnectionFromCurrentUser = connectionRepository
				.getAllConnectionsFromCurrentUser(currentUser.getId());
		return allConnectionFromCurrentUser;
	}
	
	public boolean assertConnection (int userId, int connectionId) {
		boolean assertConnection = connectionRepository.existsWithIds(userId, connectionId);
		return assertConnection;
	}

}
