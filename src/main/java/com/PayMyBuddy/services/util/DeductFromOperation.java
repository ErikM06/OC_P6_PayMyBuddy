package com.PayMyBuddy.services.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.PayMyBuddy.models.CompanyAccount;
import com.PayMyBuddy.models.Payment;
import com.PayMyBuddy.models.Transfer;
import com.PayMyBuddy.repo.CompanyAccountRepository;

@Component
public class DeductFromOperation {

	@Autowired
	CompanyAccountRepository companyAccountRepository;

	public float deductedFromTransferOperation(float amount, Transfer transfer) {
		CompanyAccount companyAccount = companyAccountRepository.getCompanyAccount();
		float amountTodeduct = amount * (float) 0.005;
		float deductedAmount = amount - amountTodeduct;
		float compagnyAccountSold = companyAccount.getSold();
		companyAccount.setSold(compagnyAccountSold+amountTodeduct);
		companyAccount.setTransfer(transfer);
		companyAccountRepository.save(companyAccount);
		return deductedAmount;
	}

	public float deductedFromPaymentOperation(float amount, Payment payment) {
		CompanyAccount companyAccount = companyAccountRepository.getCompanyAccount();
		float amountTodeduct = amount * (float) 0.005;
		float deductedAmount = amount - amountTodeduct;
		float compagnyAccountSold = companyAccount.getSold();
		companyAccount.setSold(compagnyAccountSold+amountTodeduct);
		companyAccount.setPaymentId(payment);
		companyAccountRepository.save(companyAccount);
		
		return deductedAmount;
	}

}
