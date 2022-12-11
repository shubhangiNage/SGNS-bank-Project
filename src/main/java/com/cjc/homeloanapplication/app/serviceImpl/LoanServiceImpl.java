package com.cjc.homeloanapplication.app.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cjc.homeloanapplication.app.model.Customer;
import com.cjc.homeloanapplication.app.model.EnquiryDetails;
import com.cjc.homeloanapplication.app.repositoryEnq.CustRepository;
import com.cjc.homeloanapplication.app.repositoryEnq.EnqRepository;
import com.cjc.homeloanapplication.app.serviceI.LoanService;


@Service
public class LoanServiceImpl implements LoanService{
	
	@Autowired EnqRepository enqR;

	@Autowired CustRepository cr;
	
	@Override
	public Customer setCustomerData(Customer customer) {
		
		//cr.save(customer);
		
		//System.out.println(customer.getCustomerName());
		
		System.out.println(customer.getPersonalDoc().getId());
		customer.setStatus("Pending with Branch Manager to be Sanctioned");
		
		return cr.save(customer);
	}

	@Override
	public List<Customer> getAllCustomerData() {
		// TODO Auto-generated method stub
		return cr.findAll();
	}

	@Override
	public Customer getCustomerwithcustomerAccno(Integer customerAccno) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Customer> getCustomerwithcustomerName(String customerName) {
		List<Customer> findByCustomerName = cr.findByCustomerFirstName(customerName);
		return findByCustomerName;
	}

	@Override
	public List<Customer> deleteCustomerbycustomerAccno(Integer customerId) {
		
		cr.deleteById(customerId);
		return cr.findAll();
	}

	@Override
	public EnquiryDetails setEnqDetalis(EnquiryDetails eqd) {
		eqd.setRemark("Pending For CIBIL With Operational Exe");
		return enqR.save(eqd);
	}

	@Override
	public List<EnquiryDetails> getEnqDetails() {
		// TODO Auto-generated method stub
		return enqR.findAll();
	}

	@Override
	public void delData(Integer id) {
		
		enqR.deleteById(id);
		
	}

	@Override
	public EnquiryDetails getByIdEnq(Integer id) {
		
		Optional<EnquiryDetails> op = enqR.findById(id);
		if(op.isPresent()) {
		EnquiryDetails enquiryDetails = op.get();
		return enquiryDetails;
		}
		return null;
		
	}

	@Override
	public EnquiryDetails editForCibilEnq(EnquiryDetails enqDetail,Integer id) {
		
		
		
		EnquiryDetails enqDet = enqR.save(enqDetail);
		return enqDet;
	}

	@Override
	public Integer cibilStatus(Integer id) {
		
		Integer min=600;
		Integer max=900;
		Integer b=(int)(Math.random()*(max-min+1)+min);
		
		if(b>750)
		{
			Optional<EnquiryDetails> op = enqR.findById(id);
			if(op.isPresent())
			{
				EnquiryDetails enquiryDetails = op.get();
				enquiryDetails.setCibil(b);
				enquiryDetails.setStatus("approved");
				enquiryDetails.setRemark("Documentation Pending With Relational Exe");
				enqR.save(enquiryDetails);
			}
			return b;
		}
		
		else 
		{
			Optional<EnquiryDetails> op = enqR.findById(id);
			if(op.isPresent())
			{
				EnquiryDetails enquiryDetails = op.get();
				enquiryDetails.setCibil(b);
				enquiryDetails.setStatus("rejected");
				enqR.save(enquiryDetails);
			}
			return b;
		}
		
		
	}

	@Override
	public Customer getCustomerDetails(Integer id) {
		Optional<Customer> findById = cr.findById(id);
		if(findById.isPresent())
		{
			Customer customer = findById.get();
			return customer;
		}
		return null;
	}

	@Override
	public Customer updateSanctionedAmt(Integer id, Customer customer) {
		
		Optional<Customer> op = cr.findById(id);
			if(op.isPresent())
		{
			Customer oldCust = op.get();
			
			oldCust.setCustomerAmountSanctioned(customer.getCustomerAmountSanctioned());
			//oldCust.setAmtDisburse(customer.getAmtDisburse());
			oldCust.setRoi(customer.getRoi());
			oldCust.setLoanTenure(customer.getLoanTenure());
			oldCust.setEmiAmount(customer.getEmiAmount());
			
			oldCust.setStatus("Loan Sanctioned Ready to Disburse ");
			oldCust.setRemark("Sanctoned Letter is Pending With Relational Exe");
			return cr.save(oldCust);
		
			 
		}
			return null;
		
	}

	@Override
	public Customer disburseLoanAmt(Integer id, Customer customer) {
		
		Optional<Customer> op = cr.findById(id);
		
		if(op.isPresent())
		{
			Customer customer2 = op.get();
			customer2.setAmtDisburse(customer.getAmtDisburse());
			customer2.setStatus("Loan Disburse");
			
			
			return cr.save(customer2);
		}
		return null;
		
	}

	@Override
	public void SanctionLetterGenerated(Customer customer) {
		// for change remark to sanction letter generated
		Integer id = customer.getId();
		Optional<Customer> op = cr.findById(id);
		
		if(op.isPresent())
		{
			Customer oldCust = op.get();
			oldCust.setRemark("Sanction Letter Sent");
			
			
			 cr.save(oldCust);
			 System.out.println("Remark Set");
		}
		else {
			System.out.println("Not Set Remark");
		}
		
		
	}

	

	
	
	

}
