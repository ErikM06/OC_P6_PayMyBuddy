package com.PayMyBuddy.interfaces;

import com.PayMyBuddy.dto.PaymentDTO;
import com.PayMyBuddy.exceptions.NotEnoughtBalanceException;
import com.PayMyBuddy.models.Payment;
import com.PayMyBuddy.services.util.GetCurrentUser;

public interface IPaymentService {
	
	public Payment selfPaymentToAccount(PaymentDTO paymentDTO, GetCurrentUser currentUser) throws NotEnoughtBalanceException;
	
	public Payment selfPaymentToApp(PaymentDTO paymentDTO, GetCurrentUser currentUser) throws NotEnoughtBalanceException;

}
