package com.PayMyBuddy.interfaces;

import com.PayMyBuddy.dto.TransferDTO;
import com.PayMyBuddy.exceptions.NotAConnectionException;
import com.PayMyBuddy.exceptions.NotEnoughtBalanceException;
import com.PayMyBuddy.models.Transfer;

public interface ITransferService {
	
	public Transfer transferToConnection(TransferDTO transferDTO)
			throws NotEnoughtBalanceException, NotAConnectionException;
	
	 //public Payment selfpaymentToAccount (float amount)

}
