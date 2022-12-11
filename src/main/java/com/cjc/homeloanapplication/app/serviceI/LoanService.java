package com.cjc.homeloanapplication.app.serviceI;

import java.util.List;
import java.util.Optional;

import com.cjc.homeloanapplication.app.model.Customer;
import com.cjc.homeloanapplication.app.model.EnquiryDetails;

public interface LoanService {

	Customer setCustomerData(Customer customer);

	List<Customer> getAllCustomerData();

	Customer getCustomerwithcustomerAccno(Integer customerAccno);

	List<Customer> getCustomerwithcustomerName(String customerName);

	List<Customer> deleteCustomerbycustomerAccno(Integer customerId);

	EnquiryDetails setEnqDetalis(EnquiryDetails eqd);

	List<EnquiryDetails> getEnqDetails();

	void delData(Integer id);

	EnquiryDetails getByIdEnq(Integer id);

	EnquiryDetails editForCibilEnq(EnquiryDetails enqDetail, Integer id);
	
	public Integer cibilStatus(Integer id);

	Customer getCustomerDetails(Integer id);

	Customer updateSanctionedAmt(Integer id, Customer customer);

	Customer disburseLoanAmt(Integer id, Customer customer);

	//for change remark to sanction letter generated
	void SanctionLetterGenerated(Customer customer);

}
