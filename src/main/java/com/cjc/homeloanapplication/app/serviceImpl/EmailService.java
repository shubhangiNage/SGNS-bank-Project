package com.cjc.homeloanapplication.app.serviceImpl;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cjc.homeloanapplication.app.model.EmailSender;

@Service
public class EmailService {
	
	
	@Autowired
	  private JavaMailSender jms;
		
		
		
		
	    public void sendEmail(EmailSender emailSender)
		{
	         SimpleMailMessage mssg = new SimpleMailMessage();
	    	 
	    	      mssg.setFrom(emailSender.getFromEmail());
	    	 mssg.setTo(emailSender.getToEmail());
	    	 mssg.setSubject(emailSender.getSubject());
	    	 mssg.setText(emailSender.getTextBody());
			
		}



		public void sendMailWithAttachment(EmailSender email, MultipartFile attachDacument)  
		{
			MimeMessage createMimeMessage = jms.createMimeMessage();
			
		    try 
		    {
				MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(createMimeMessage, true);
				
				mimeMessageHelper.setFrom(email.getFromEmail());
				mimeMessageHelper.setTo(email.getToEmail());
				mimeMessageHelper.setSubject(email.getSubject());
				mimeMessageHelper.setText(email.getTextBody());
				
				mimeMessageHelper.addAttachment(attachDacument.getOriginalFilename(), attachDacument);
				
				jms.send(createMimeMessage);
				
				System.out.println("send Message");
				
			} catch (Exception e) {
				System.out.println("not send Message");
				e.printStackTrace();
			
			}
			
			
		}



		public void sendMailEnqDocument(EmailSender email) {
			
			 SimpleMailMessage mssg = new SimpleMailMessage();
			 mssg.setFrom(email.getFromEmail());
			 mssg.setTo(email.getToEmail());
			 mssg.setSubject(email.getSubject());
			 mssg.setText(email.getTextBody());
			 
			 jms.send(mssg);
			 System.out.println("Mail Sent from Service for Enq");
		}


		//SanctionLetter
		public void sendMailToCustomerForSactterLetter(EmailSender email) {
			
			SimpleMailMessage mssg = new SimpleMailMessage();
			 mssg.setFrom(email.getFromEmail());
			 mssg.setTo(email.getToEmail());
			 mssg.setSubject(email.getSubject());
			 mssg.setText(email.getTextBody());
			 
			 jms.send(mssg);
			 System.out.println("Mail Sent from Service for Sanction Letter to Customer");
			
		}
		
		
}
