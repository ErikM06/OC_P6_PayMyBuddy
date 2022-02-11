package com.PayMyBuddy.interfaces;

import com.PayMyBuddy.exceptions.NotEnoughtBalanceException;
import com.PayMyBuddy.models.Balance;
import com.PayMyBuddy.models.User;

public interface IBalanceService {
	
	public Balance initBalance (User user);
	
	public Balance addToBalance (User user, float amount) throws NotEnoughtBalanceException;
	
	public Balance takeFromBalance (User user, float amount) throws NotEnoughtBalanceException;

}
