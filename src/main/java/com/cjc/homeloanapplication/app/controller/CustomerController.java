package com.cjc.homeloanapplication.app.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cjc.homeloanapplication.app.model.Customer;
import com.cjc.homeloanapplication.app.model.CustomerAddress;
import com.cjc.homeloanapplication.app.model.Guaranter;
import com.cjc.homeloanapplication.app.model.PersonalDoc;
import com.cjc.homeloanapplication.app.serviceI.LoanService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@CrossOrigin("*")
@RestController
public class CustomerController {
	
	@Autowired LoanService ls;
	
	@PostMapping(value = "/setCustomer",consumes = MediaType.ALL_VALUE)
	public ResponseEntity<Customer> setCustomerData(@RequestPart(value = "custjson",required = true) String customerJson,
			@RequestPart(value = "addressProof",required = true) MultipartFile doc1,
			@RequestPart(value = "panCard",required = true) MultipartFile doc2,
			@RequestPart(value = "IncomeTax",required = true) MultipartFile doc3,
			@RequestPart(value = "addharCard",required = true) MultipartFile doc4,
			@RequestPart(value = "photo",required = true) MultipartFile doc5,
			@RequestPart(value = "signature",required = true) MultipartFile doc6,
			@RequestPart(value = "thumb",required = true) MultipartFile doc7,
			@RequestPart(value = "bankCheque",required = true) MultipartFile doc8,
			@RequestPart(value = "salarySlips",required = true) MultipartFile doc9,
			@RequestPart(value = "guaranranterPan",required = true) MultipartFile doc10,
			@RequestPart(value = "guaranranterAadhar",required = true) MultipartFile doc11,
			@RequestPart(value = "guaranranterSignature",required = true) MultipartFile doc12
			)
	{
		
		ObjectMapper om=new ObjectMapper();
		try {
			Customer cust=om.readValue(customerJson, Customer.class);
			
			//PersonalDoc personal = om.readValue(pdJson, PersonalDoc.class);
			
			
			String json=om.writeValueAsString(cust);
			System.out.println(json);
			
			Customer customer=new Customer();
			customer.setCustomerAccno(cust.getCustomerAccno());
			customer.setCustomerFirstName(cust.getCustomerFirstName());
			customer.setCustomerLastName(cust.getCustomerLastName());
			customer.setCustomerDateOfBirth(cust.getCustomerDateOfBirth());
			customer.setCustomerAge(cust.getCustomerAge());
			customer.setCustomerGender(cust.getCustomerGender());
			customer.setCustomerEmail(cust.getCustomerEmail());
			customer.setCustomerMobileNumber(cust.getCustomerMobileNumber());
			customer.setCustomerAdditionalMobileNumber(cust.getCustomerAdditionalMobileNumber());
			customer.setCustomerAmountSanctioned(cust.getCustomerAmountSanctioned());
			customer.setCustomerTotalLoanRequired(cust.getCustomerTotalLoanRequired());
			customer.setAmtDisburse(cust.getAmtDisburse());
			customer.setLoanTenure(cust.getLoanTenure());
			customer.setRoi(cust.getRoi());
			customer.setEducationalInfo(cust.getEducationalInfo());
			customer.setOccupation(cust.getOccupation());
			
			CustomerAddress custad=new CustomerAddress();
			custad.setId(cust.getCustomerAddress().getId());
			custad.setCityName(cust.getCustomerAddress().getCityName());
			custad.setAreaName(cust.getCustomerAddress().getAreaName());
			custad.setPincode(cust.getCustomerAddress().getPincode());
			custad.setState(cust.getCustomerAddress().getState());
			
			Guaranter gua=new Guaranter();
			gua.setGuaranterName(cust.getGuaranter().getGuaranterName());
			gua.setGuaranterMob(cust.getGuaranter().getGuaranterMob());
			gua.setGuaranterEmail(cust.getGuaranter().getGuaranterEmail());
			
			
			customer.setCustomerAddress(custad);
			customer.setGuaranter(gua);
			
			PersonalDoc perDoc=new PersonalDoc();
			//perDoc.setDocumentID(personal.getDocumentID());
			perDoc.setAddressProof(doc1.getBytes());
			perDoc.setPanCard(doc2.getBytes());
			perDoc.setIncomeTax(doc3.getBytes());
			perDoc.setAddharCard(doc4.getBytes());
			perDoc.setPhoto(doc5.getBytes());
			perDoc.setSignature(doc6.getBytes());
			perDoc.setThumb(doc7.getBytes());
			perDoc.setBankCheque(doc8.getBytes());
			perDoc.setSalarySlips(doc9.getBytes());
			perDoc.setGuaranterPan(doc10.getBytes());
			perDoc.setGuaranterAadhar(doc11.getBytes());
			perDoc.setGuaranterSignature(doc12.getBytes());
			
			
			
			
			customer.setPersonalDoc(perDoc);
			
			Customer custom=ls.setCustomerData(customer);
			
			return new ResponseEntity<Customer>(custom,HttpStatus.CREATED);
			
		} catch (Exception e) {
			
			e.printStackTrace();
			return null;
		}
		
		System.out.println("Git practice by TL");	
		
	}
	
	@GetMapping("/getAllValue")
	  public ResponseEntity<List<Customer>> getAllCustomerData()
	  {
		 List<Customer> list = ls.getAllCustomerData();
		return new ResponseEntity<List<Customer>>(list, HttpStatus.OK);
		  
	  }
	  
	  
	  @GetMapping("/getValue/{customerAccno}")
	  public ResponseEntity< Customer> getCustomerwithcustomerAccno(@PathVariable("customerAccno") Integer customerAccno )
	  {
		  Customer optionwithfindBycustomerAccno = ls.getCustomerwithcustomerAccno(customerAccno);
		return new ResponseEntity<Customer>(optionwithfindBycustomerAccno, HttpStatus.OK);
		  
	  }
	  
	  @GetMapping("/getValueByName/{customerName}")
	  public ResponseEntity< List<Customer>> getCustomerwithcustomerName(@PathVariable("customerName") String customerName )
	  {
		  List<Customer> findBycustomerAccnocustomerName = ls.getCustomerwithcustomerName(customerName);
		return new ResponseEntity<List<Customer>>(findBycustomerAccnocustomerName, HttpStatus.OK);
		  
	  }
	
	  @DeleteMapping("/deleteValueBycustomerAccno/{customerAccno}")
	  public ResponseEntity< List<Customer>> deleteCustomerbycustomerAccno(@PathVariable("customerAccno") Integer customerName )
	  {
		  List<Customer> deleteBycustomerAccnocustomerName = ls.deleteCustomerbycustomerAccno(customerName);
		return new ResponseEntity<List<Customer>>(deleteBycustomerAccnocustomerName, HttpStatus.OK);
		  
	  }
	  @GetMapping("/getCustomerDetailsBiId/{id}")
	  public ResponseEntity<Customer> getCustomerDetails(@PathVariable Integer id)
	  {
		  Customer cust=ls.getCustomerDetails(id);
		  return new ResponseEntity<Customer>(cust,HttpStatus.OK);
	  }
	  
	  @PutMapping("/updateSanctionedAmt/{id}")
	  public ResponseEntity<Customer> updateSanctionedAmt(@PathVariable Integer id,@RequestBody Customer customer)
	  {
		  Customer cust=ls.updateSanctionedAmt(id,customer);
		  return new ResponseEntity<Customer>(cust,HttpStatus.OK);
	  }
	  
	  @PutMapping("/disburseAmt/{id}")
	  public ResponseEntity<Customer> disburseLoanAmt(@PathVariable Integer id,@RequestBody Customer customer){
		
		 Customer c= ls.disburseLoanAmt(id,customer);
		 
		  return new ResponseEntity<Customer>(c,HttpStatus.OK);
		  
	  }

	
	}


	
	


