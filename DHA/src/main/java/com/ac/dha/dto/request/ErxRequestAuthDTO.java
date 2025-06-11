package com.ac.dha.dto.request;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Prior.Authorization")
@XmlAccessorType(XmlAccessType.FIELD)
public class ErxRequestAuthDTO {

	@XmlElement(name = "Header")
	private HeaderDTO header;

	@XmlElement(name = "Authorization")
	private AuthAuthorizationDTO authorizationAuth;

	public HeaderDTO getHeader() {
		return header;
	}

	public void setHeader(HeaderDTO header) {
		this.header = header;
	}

	public AuthAuthorizationDTO getAuthorizationAuth() {
		return authorizationAuth;
	}

	public void setAuthorizationAuth(AuthAuthorizationDTO authorizationAuth) {
		this.authorizationAuth = authorizationAuth;
	}

	@Override
	public String toString() {
		return "ErxAuth [header=" + header + ", authorizationAuth=" + authorizationAuth + "]";
	}

	public ErxRequestAuthDTO(HeaderDTO header, AuthAuthorizationDTO authorizationAuth) {
		super();
		this.header = header;
		this.authorizationAuth = authorizationAuth;
	}

}
