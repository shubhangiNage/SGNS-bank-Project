package com.cjc.homeloanapplication.app.repositoryEnq;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cjc.homeloanapplication.app.model.Customer;
import com.cjc.homeloanapplication.app.model.EnquiryDetails;

@Repository
public interface CustRepository extends JpaRepository<Customer, Integer>  {
	
	public List<Customer>   findByCustomerFirstName(String customerFirstName);
}