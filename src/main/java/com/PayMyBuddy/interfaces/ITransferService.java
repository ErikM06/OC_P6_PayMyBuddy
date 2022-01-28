package com.PayMyBuddy.interfaces;

import java.util.List;

import com.PayMyBuddy.dto.TransactionRecordDto;
import com.PayMyBuddy.dto.TransferDTO;
import com.PayMyBuddy.exceptions.NotAConnectionException;
import com.PayMyBuddy.exceptions.NotEnoughtBalanceException;
import com.PayMyBuddy.models.Transfer;
import com.PayMyBuddy.services.util.GetCurrentUser;

public interface ITransferService {
	
	public Transfer transferToConnection(TransferDTO transferDTO, GetCurrentUser currentUser)
			throws NotEnoughtBalanceException, NotAConnectionException;
	
	public List<TransactionRecordDto> getAllUserTransfer(GetCurrentUser currentUser) throws NullPointerException;
	
}
