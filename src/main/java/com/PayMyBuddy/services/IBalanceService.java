package com.PayMyBuddy.services;

import com.PayMyBuddy.models.Balance;
import com.PayMyBuddy.models.User;

public interface IBalanceService {
	
	public Balance getBalanceByUser(User user);
	
	public Balance initBalance (User user);

}
