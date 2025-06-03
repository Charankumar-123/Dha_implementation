package com.ac.dha.dto.response;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;

@XmlRootElement(name = "UploadERxAuthorizationResponseDTO")
@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class UploadERxAuthorizationResponseDTO {

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
