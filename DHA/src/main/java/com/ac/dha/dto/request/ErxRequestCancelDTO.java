package com.ac.dha.dto.request;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Prior.Request")
@XmlAccessorType(XmlAccessType.FIELD)
public class ErxRequestCancelDTO {

	@XmlElement(name = "Header")
	private HeaderDTO header;

	@XmlElement(name = "Authorization")
	private CancelAuthorizationDTO cancelAuthorization;

	public HeaderDTO getHeader() {
		return header;
	}

	public void setHeaderDTO(HeaderDTO header) {
		this.header = header;
	}

	public CancelAuthorizationDTO getCancelAuthorization() {
		return cancelAuthorization;
	}

	public void setCancelAuthorizationDTO(CancelAuthorizationDTO cancelAuthorization) {
		this.cancelAuthorization = cancelAuthorization;
	}

	@Override
	public String toString() {
		return "ErxRequestCancel [header=" + header + ", cancelAuthorization=" + cancelAuthorization + "]";
	}

	public ErxRequestCancelDTO(HeaderDTO header, CancelAuthorizationDTO cancelAuthorization) {
		super();
		this.header = header;
		this.cancelAuthorization = cancelAuthorization;
	}

}
