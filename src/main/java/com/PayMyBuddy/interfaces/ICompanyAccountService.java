package com.PayMyBuddy.interfaces;

import com.PayMyBuddy.models.Payment;
import com.PayMyBuddy.models.Transfer;

public interface ICompanyAccountService {
	
	public void initCompanyAccount();
	
	public void transferToCompanyAccount(float amount, Transfer transfer);
	
	public void paymentToCompanyAccount(float amount, Payment payment);

}
