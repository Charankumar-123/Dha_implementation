package com.ac.dha.entities;

import java.util.Arrays;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbl_eclaim_uploadERxAuth")
public class UploadERxAuthorization {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String payerLogin;

	private String payerPwd;

	private byte[] fileContent;

	private String fileName;
	
	public UploadERxAuthorization() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPayerLogin() {
		return payerLogin;
	}

	public void setPayerLogin(String payerLogin) {
		this.payerLogin = payerLogin;
	}

	public String getPayerPwd() {
		return payerPwd;
	}

	public void setPayerPwd(String payerPwd) {
		this.payerPwd = payerPwd;
	}

	public byte[] getFileContent() {
		return fileContent;
	}

	public void setFileContent(byte[] fileContent) {
		this.fileContent = fileContent;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@Override
	public String toString() {
		return "UploadERxAuthorization [id=" + id + ", payerLogin=" + payerLogin + ", payerPwd=" + payerPwd
				+ ", fileContent=" + Arrays.toString(fileContent) + ", fileName=" + fileName + "]";
	}

	public UploadERxAuthorization(Long id, String payerLogin, String payerPwd, byte[] fileContent, String fileName) {
		super();
		this.id = id;
		this.payerLogin = payerLogin;
		this.payerPwd = payerPwd;
		this.fileContent = fileContent;
		this.fileName = fileName;
	}
}
