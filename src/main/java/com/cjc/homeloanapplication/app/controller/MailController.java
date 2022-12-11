package com.cjc.homeloanapplication.app.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cjc.homeloanapplication.app.model.Customer;
import com.cjc.homeloanapplication.app.model.EmailSender;
import com.cjc.homeloanapplication.app.model.EnquiryDetails;
import com.cjc.homeloanapplication.app.serviceI.LoanService;
import com.cjc.homeloanapplication.app.serviceImpl.EmailService;
import com.fasterxml.jackson.databind.ObjectMapper;


@CrossOrigin("*")
@RestController
@RequestMapping("/mail")
public class MailController {
	
	@Autowired
	private EmailService es;
	
	@Autowired private LoanService ls;
	
	@Value("${spring.mail.username}")
	String fromEmail;
	@RequestMapping("/send")
	public String sendEmail(@RequestBody EmailSender emailSender)
	{
		try 
		{
			emailSender.setFromEmail(fromEmail);
			
			es.sendEmail(emailSender);
			return "sent";
		}
		catch(Exception e2)
		{
			e2.printStackTrace();
			return "not Sent";
		}
		
		
	}
	
	//Random Hardcore Mail to customer
	@RequestMapping(value="/emailattachment",consumes=MediaType.ALL_VALUE)
	public String sendMailWithAttachment(
			@RequestPart(value ="attachDacument", required = true) MultipartFile attachDacument,
			@RequestPart(value ="objectJason", required = true) String objectJason)
	{
		ObjectMapper objectMapper = new ObjectMapper();
		
		try {
			EmailSender  emailSender = objectMapper.readValue(objectJason, EmailSender.class);
			
			EmailSender email = new EmailSender();
			
			email.setFromEmail(fromEmail);
			email.setToEmail(emailSender.getToEmail());
			email.setSubject(emailSender.getSubject());
			email.setTextBody(emailSender.getTextBody());
			
			es.sendMailWithAttachment(email,attachDacument);
			
			return "send Message";
			
		} 
		catch (Exception e) {
			
			e.printStackTrace();
			
			return "not send Message";
		}
		
		
	}
	
		@PostMapping("/sendMailEnq")
		public String sendMailEnq(@RequestBody EnquiryDetails e)
		{
			System.out.println(e.getFirstName());
			System.out.println(e.getEmail());
			EmailSender email = new EmailSender();
			email.setFromEmail(fromEmail);
			email.setToEmail(e.getEmail());
			email.setSubject("Regarding Document Verification");
			email.setTextBody("Dear, \n   "+e.getFirstName()+" your Cibil is Ok And You are Eligible"
					+ "For Futher Process."
					+ "\n We are happy to inform you that your Home Loan request has been approved and is currently being processed."
					+ "\n Further, we inform you that we have sent an email containing attached documents. \n"
					+ "Also we have sent you the terms and conditions of the loans sanctioned. \n \n"
					+ "We would like to schedule your meeting with the finance officer of the company for any further information and clarifications that you might wish to know.  \n"
					+ "We are happy to be doing business with you.\n"
					+ "\n"
					+ "List Of Document Required \n"
					+ "\n"
					+ "1.Aadhar Card \n"
					+ "2.Pan Card \n"
					+ "3.Income Tax Return Last Two Years\n"
					+ "4.Salary Slips Last three Months \n"
					+ "5.Passport Size Photographs \n"
					+ "6.Guaranter Pan Card \n"
					+ "7.Guaranter Aadhar Card \n"
					+ "\n \n Thanking you."
					+ "\n Sincerely yours, \n \n \n \n"
					+ "Mrs.Shiwangi Naik"
					+ "\n  Branch Manager"
					+ "\n  DreamHomes Finance Ltd. \n Vikas Chauk Karvenagar \n"
					+ "\n Pune, Maharashtra \n India-411052"
					
					+ "\n ThankYou For BankingWith Us \n"
					+ "Dream Homes...!!!");
			
			es.sendMailEnqDocument(email);
			
			return "Mail Sent ";
		}
		
		//SanctionLetter

	@PostMapping(value="/emailSendToCustomer")
    public String sendMailToCustomerForSactterLetter(@RequestBody Customer customer)
    {
		
		System.out.println(customer.getCustomerFirstName());
		System.out.println(customer.getCustomerEmail());
		
		
		
      EmailSender email = new EmailSender();
      email.setFromEmail(fromEmail);
      email.setToEmail(customer.getCustomerEmail());
      email.setSubject("Regarding Sanction Letter of loan against property Acc No:- XXX"+customer.getCustomerAccno());
      email.setTextBody("To,\n"+customer.getCustomerFirstName()+"  "+customer.getCustomerLastName()+"\n"+
             customer.getCustomerAddress().getAreaName()+"\n"+customer.getCustomerAddress().getCityName()+"\n"+
             customer.getCustomerAddress().getState()+"-"+customer.getCustomerAddress().getPincode()  +
           "\n \n Dear sir, \n \n Thank You for choosing Dream Homes Finance pvt ltd. Based on the application and information"
          +"\n provided there in, we are pleased to extend an offer to you for a loan "
          +"\n as per preliminary terms and condition mention below"
          +"\n \n Account No.:- XXX"+customer.getCustomerAccno()+" "   
          +"\n Applicant Name:- "+customer.getCustomerFirstName()+customer.getCustomerLastName()+" " 
          +"\n Applicant Mobile No.:- "+customer.getCustomerMobileNumber()+" "
          +"\n Sanctioned Date.:- "+LocalDate.now()+" " 
          +"\n Loan Type:- Home Loan"
          +"\n Loan Sanctioned Amount.:- "+customer.getCustomerAmountSanctioned()+" "
          +"\n Reference Interest Rate:- "+customer.getRoi()+"(Interest Type: Floating Rate of Interest) "
          +"\n Floating Interest Rate:- Reference Rate Applicable at the time of Disbursement 4.90% "
          +"\n Loan Tenure In Years:- "+customer.getLoanTenure()+" yrs"
          +"\n Total Processing Fee:- 0.25% of Total Loan Amount" 
          +"\n Origination Fee(Inclusive of GST):- INR 2500/-" 
          +"\n Sanctioned Letter Validity:- 180 Days" 
          +"\n Amount Of EMI:- INR "+customer.getEmiAmount()
          
         
          + "\n \n Thanking you For Banking With Us."
          + "\n Sincerely yours, \n \n \n \n"
          + "Mrs.Shiwangi Naik"
          + "\n  Branch Manager"
          + "\n  DreamHomes Finance Pvt Ltd."
          + "\n Vikas Chauk Karvenagar,Pune"
          + "\n Maharashtra -411052"
          );
      
      es.sendMailToCustomerForSactterLetter(email);
      
      ls.SanctionLetterGenerated(customer);
      
      return "Mail Sent";
    }
}

