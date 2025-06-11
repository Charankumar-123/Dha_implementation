package com.ac.dha.dto.request;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Builder
@XmlRootElement(name = "Prior.Request")
@XmlAccessorType(XmlAccessType.FIELD)
public class ErxRequestDTO {

	@XmlElement(name = "Header")
	private HeaderDTO header;

	@XmlElement(name = "Authorization")
	private AuthorizationDTO authorization;

	public HeaderDTO getHeader() {
		return header;
	}

	public void setHeader(HeaderDTO header) {
		this.header = header;
	}

	public AuthorizationDTO getAuthorization() {
		return authorization;
	}

	public void setAuthorization(AuthorizationDTO authorization) {
		this.authorization = authorization;
	}

}
