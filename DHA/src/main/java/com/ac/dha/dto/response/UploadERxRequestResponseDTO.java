package com.ac.dha.dto.response;

import java.util.Arrays;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;

@XmlRootElement(name = "UploadERxAuthorizationResponse")
@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class UploadERxRequestResponseDTO {

	@XmlElement(name = "ERxReferenceNo")
	private String eRxReferenceNo;

	@XmlElement(name = "ErrorMessage")
	private String errorMessage;

	@XmlElement(name = "ErrorReport")
	private byte[] errorReport;

	public UploadERxRequestResponseDTO() {
	}

	public String geteRxReferenceNo() {
		return eRxReferenceNo;
	}

	public void seteRxReferenceNo(String integer) {
		this.eRxReferenceNo = integer;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public byte[] getErrorReport() {
		return errorReport;
	}

	public void setErrorReport(byte[] errorReport) {
		this.errorReport = errorReport;
	}

	@Override
	public String toString() {
		return "UploadERxRequestResponseDTO [eRxReferenceNo=" + eRxReferenceNo + ", errorMessage=" + errorMessage
				+ ", errorReport=" + Arrays.toString(errorReport) + "]";
	}

	public UploadERxRequestResponseDTO(String eRxReferenceNo, String errorMessage, byte[] errorReport) {
		super();
		this.eRxReferenceNo = eRxReferenceNo;
		this.errorMessage = errorMessage;
		this.errorReport = errorReport;
	}

}
