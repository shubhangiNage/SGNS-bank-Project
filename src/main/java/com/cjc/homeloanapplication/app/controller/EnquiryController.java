package com.cjc.homeloanapplication.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cjc.homeloanapplication.app.model.EnquiryDetails;
import com.cjc.homeloanapplication.app.serviceI.LoanService;

@CrossOrigin("*")
@RestController
@RequestMapping("/enq")
public class EnquiryController {
	
	@Autowired LoanService ls;
	
	@PostMapping("/setEnq")
	public EnquiryDetails setEnqDetalis(@RequestBody EnquiryDetails eqd)
	{
		EnquiryDetails eq =ls.setEnqDetalis(eqd);
		
		return eq;
		
	}
	
	@GetMapping("/getenq")
	public List<EnquiryDetails> getEnqDetails()
	{
		List<EnquiryDetails>  list1=ls.getEnqDetails();
		
		return list1;
	}
	
	@DeleteMapping("/delEnq/{id}")
	public String delData(@PathVariable Integer id)
	{
		ls.delData(id);
		
		return "Deleted";
	}
	
	@GetMapping("/getByIdEnq/{id}")
	public EnquiryDetails getByIdEnq(@PathVariable Integer id)
	{
		EnquiryDetails enqD=ls.getByIdEnq(id);
		
		return enqD;
	}
	
	@PutMapping("/editCibil/{id}")
	public EnquiryDetails editForCibilEnq(@PathVariable Integer id, @RequestBody EnquiryDetails enqDetail)
	{
		EnquiryDetails enq=ls.editForCibilEnq(enqDetail,id);
		return enq;
	}
	
	@GetMapping("/cib/{id}")
	public Integer cibilStatus(@PathVariable Integer id)
	{
		return ls.cibilStatus(id);
	}
	
}
