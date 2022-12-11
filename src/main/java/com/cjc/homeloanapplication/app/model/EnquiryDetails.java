package com.cjc.homeloanapplication.app.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class EnquiryDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	private String firstName;
	private String lastName;
	private Integer age;
	private String mobileNo;
	private String alternateMobileNo;
	private String email;
	private Integer aadharNo;
	private String pancardNo;
	private Integer cibil;
	private String status;
	private String remark;
	
	


}
