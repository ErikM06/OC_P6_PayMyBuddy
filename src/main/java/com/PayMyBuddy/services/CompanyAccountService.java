package com.PayMyBuddy.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PayMyBuddy.interfaces.ICompanyAccountService;
import com.PayMyBuddy.models.CompanyAccount;
import com.PayMyBuddy.models.Payment;
import com.PayMyBuddy.models.Transfer;
import com.PayMyBuddy.repo.CompanyAccountRepository;

@Transactional
@Service
public class CompanyAccountService implements ICompanyAccountService {

	@Autowired
	private CompanyAccountRepository companyAccountRepository;

	

	public void transferToCompanyAccount(float amount, Transfer transfer) {
		CompanyAccount companyAccount = companyAccountRepository.getLastCompanyAccountOperation();
		CompanyAccount newTransfertOnCompanyAccount = new CompanyAccount();
		newTransfertOnCompanyAccount.setSold(companyAccount.getSold() + amount);
		newTransfertOnCompanyAccount.setBankAccountNumber(companyAccount.getBankAccountNumber());
		newTransfertOnCompanyAccount.setTransferId(transfer);
		companyAccountRepository.save(newTransfertOnCompanyAccount);

	}

	public void paymentToCompanyAccount(float amount, Payment payment) {
		CompanyAccount companyAccount = companyAccountRepository.getLastCompanyAccountOperation();
		CompanyAccount newPaymentOnCompanyAccount = new CompanyAccount();
		newPaymentOnCompanyAccount.setSold(companyAccount.getSold() + amount);
		newPaymentOnCompanyAccount.setBankAccountNumber(companyAccount.getBankAccountNumber());
		newPaymentOnCompanyAccount.setPaymentId(payment);
		companyAccountRepository.save(newPaymentOnCompanyAccount);

	}

}
