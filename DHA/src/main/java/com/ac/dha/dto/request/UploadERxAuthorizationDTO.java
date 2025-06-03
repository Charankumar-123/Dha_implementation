package com.ac.dha.dto.request;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Builder
@XmlAccessorType(XmlAccessType.FIELD)
public class UploadERxAuthorizationDTO {

	@XmlElement(name = "PayerLogin")
	private String payerLogin;

	@XmlElement(name = "PayerPwd")
	private String payerPwd;

	@XmlElement(name = "FileContent")
	private byte[] fileContent;

	@XmlElement(name = "FileName")
	private String fileName;

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

}
