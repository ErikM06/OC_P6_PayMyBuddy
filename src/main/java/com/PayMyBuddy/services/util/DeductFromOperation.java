package com.PayMyBuddy.services.util;

import org.springframework.stereotype.Component;

@Component
public class DeductFromOperation {



	public float deductedFromTransferOperation(float amount) {
		
		float amountTodeduct = amount * (float) 0.005;
		float deductedAmount = amount - amountTodeduct;
		
		return deductedAmount;
	}

}
