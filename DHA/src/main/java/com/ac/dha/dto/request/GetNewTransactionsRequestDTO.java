package com.ac.dha.dto.request;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Builder
@XmlRootElement(name = "GetNewTransactionsRequest")
@XmlAccessorType(XmlAccessType.FIELD)
public class GetNewTransactionsRequestDTO {

	@XmlElement(name = "Login")
	private String login;

	@XmlElement(name = "Pwd")
	private String pwd;
	
	
	

//    @XmlElement(name = "PriorRequest")
//	private ErxRequestDTO priorRequest;

//	public ErxRequestDTO getPriorRequest() {
//		return priorRequest;
//	}
//
//	public void setPriorRequest(ErxRequestDTO priorRequest) {
//		this.priorRequest = priorRequest;
//	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	
}
