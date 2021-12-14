package com.PayMyBuddy.services;

import com.PayMyBuddy.models.Balance;
import com.PayMyBuddy.models.User;

public interface IBalanceService {
	
	Balance setBalanceAtRegistration (User user);

}
