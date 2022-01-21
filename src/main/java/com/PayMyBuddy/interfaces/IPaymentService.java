package com.PayMyBuddy.interfaces;

import com.PayMyBuddy.exceptions.NotEnoughtBalanceException;
import com.PayMyBuddy.models.Payment;

public interface IPaymentService {
	
	public Payment selfPaymentToAccount(String bankAccountNumber, float amount) throws NotEnoughtBalanceException;
	
	public Payment selfPaymentToApp(String bankAccountNumber, float amount) throws NotEnoughtBalanceException;

}
