package com.PayMyBuddy.interfaces;

import com.PayMyBuddy.dto.PaymentDTO;
import com.PayMyBuddy.exceptions.NotEnoughtBalanceException;
import com.PayMyBuddy.models.Payment;

public interface IPaymentService {
	
	public Payment selfPaymentToAccount(PaymentDTO paymentDTO) throws NotEnoughtBalanceException;
	
	public Payment selfPaymentToApp(PaymentDTO paymentDTO) throws NotEnoughtBalanceException;

}
