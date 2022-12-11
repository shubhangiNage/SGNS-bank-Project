package com.cjc.homeloanapplication.app.model;

import lombok.Data;

@Data
public class EmailSender {
	
	private String fromEmail;
	private String toEmail;
	private String textBody;
	private String subject;
}
