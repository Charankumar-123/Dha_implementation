package com.ac.dha.dto.response;

import java.util.Arrays;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;

@XmlRootElement(name = "UploadERxAuthorizationResponseDTO")
@XmlAccessorType(XmlAccessType.FIELD)
//@Data
public class UploadERxAuthorizationResponseDTO {

	public UploadERxAuthorizationResponseDTO(String payerLogin, String payerPwd, byte[] fileContent, String fileName) {
		super();
		this.payerLogin = payerLogin;
		this.payerPwd = payerPwd;
		this.fileContent = fileContent;
		this.fileName = fileName;
	}

	public UploadERxAuthorizationResponseDTO() {
	}

	@Override
	public String toString() {
		return "UploadERxAuthorizationResponseDTO [payerLogin=" + payerLogin + ", payerPwd=" + payerPwd
				+ ", fileContent=" + Arrays.toString(fileContent) + ", fileName=" + fileName + "]";
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

	@XmlElement(name = "PayerLogin")
	private String payerLogin;

	@XmlElement(name = "PayerPwd")
	private String payerPwd;

	@XmlElement(name = "FileContent")
	private byte[] fileContent;

	@XmlElement(name = "FileName")
	private String fileName;

	// Optional: If Lombok @Data is used, the following getters/setters are
	// redundant.

}
