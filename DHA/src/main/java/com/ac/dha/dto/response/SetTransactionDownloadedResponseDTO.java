package com.ac.dha.dto.response;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "SetTransactionDownloadedResponseDTO")
//@Data
public class SetTransactionDownloadedResponseDTO {
	
	@XmlElement(name = "SetTransactionDownloadedResult")
	private Integer setTransactionDownloadedResult;
	
	@XmlElement(name = "ErrorMessage")
	private String errorMessage;
	
	public SetTransactionDownloadedResponseDTO() {}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	@Override
	public String toString() {
		return "SetTransactionDownloadedResponseDTO [errorMessage=" + errorMessage + "]";
	}

	public SetTransactionDownloadedResponseDTO(String errorMessage) {
		super();
		this.errorMessage = errorMessage;
	}
	
	
}
