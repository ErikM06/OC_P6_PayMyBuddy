package com.PayMyBuddy.services;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.PayMyBuddy.dto.ConnectionDTO;
import com.PayMyBuddy.interfaces.IConnectionService;
import com.PayMyBuddy.interfaces.IUserService;
import com.PayMyBuddy.models.Connections;
import com.PayMyBuddy.models.User;
import com.PayMyBuddy.repo.ConnectionRepository;
import com.PayMyBuddy.services.util.GetCurrentUser;

@Service
public class ConnectionService implements IConnectionService {

	Logger logger = LoggerFactory.getLogger(ConnectionService.class);

	@Autowired
	ConnectionRepository connectionRepository;

	@Autowired
	IUserService iUserService;

	@Autowired
	GetCurrentUser currentUser;

	public Connections addConnections(ConnectionDTO connectionDTO) throws UsernameNotFoundException {

		Connections connection = new Connections();
		if (iUserService.findByEmail(connectionDTO.getEmail()) == null) {
			throw new UsernameNotFoundException("User not found");
		} else {
			connection.setUser(iUserService.findByEmail(currentUser.getCurrentUser()));
			connection.setConnection(iUserService.findByEmail(connectionDTO.getEmail()));
			connection.setCreateTime(new Timestamp(System.currentTimeMillis()));
			connection.setNote(connectionDTO.getNote());

			connectionRepository.save(connection);
		}
		return connection;

	}

	@Transactional
	public void deleteConnection(String connectionUsername) throws Exception, NullPointerException{
		User user = new User();
		User connectionProfil = new User();
		List<Connections> connection = new ArrayList<>();
		try {
			user = iUserService.findByEmail(currentUser.getCurrentUser());
			connectionProfil = iUserService.findUserByUsername(connectionUsername);
			logger.info("connection to delete with buddy as : {}", connectionProfil.getUsername());
			logger.info("connection to delete with user as  : {}", user.getUsername());
		
			connection = connectionRepository.selectByUsers(user, connectionProfil);

			if (connection.isEmpty()) {
				throw new Exception("Not a buddy");
			} else {
				for (Connections connectionEntity : connection) {
					connectionRepository.deleteConnectionById(connectionEntity.getId());
					logger.info("connection to delete with connection id as  : {}", connectionEntity.getId());
				}
			}
		} catch (NullPointerException e) {
			throw new NullPointerException("this username don't exist");
		} catch (Exception e) {
			throw new Exception (e.getMessage());	
		}

	}

	public List<Connections> getAllConnections() {
		String currrentUsername = currentUser.getCurrentUser();
		User currentUser = iUserService.findByEmail(currrentUsername);
		List<Connections> allConnectionFromCurrentUser = connectionRepository
				.getAllConnectionsFromCurrentUser(currentUser.getId());
		return allConnectionFromCurrentUser;
	}

	public boolean assertConnection(int userId, int connectionId) {
		boolean assertConnection = connectionRepository.existsWithIds(userId, connectionId);
		return assertConnection;
	}

}
