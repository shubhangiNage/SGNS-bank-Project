package com.cjc.homeloanapplication.app.model;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

import lombok.Data;

@Entity
@Data
public class PersonalDoc {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@Lob
	private byte[] addressProof;
	@Lob
	private byte[] panCard;
	@Lob
	private byte[] incomeTax;
	@Lob
	private byte[] addharCard;
	@Lob
	private byte[] photo;
	@Lob
	private byte[] signature;
	@Lob
	private byte[] thumb;
	@Lob
	private byte[] bankCheque;
	@Lob
	private byte[] salarySlips;
	@Lob
	private byte[] guaranterPan;
	@Lob
	private byte[] guaranterAadhar;
	@Lob
	private byte[] guaranterSignature;

}
