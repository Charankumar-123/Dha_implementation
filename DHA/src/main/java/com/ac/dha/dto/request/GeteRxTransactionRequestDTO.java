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
public class GeteRxTransactionRequestDTO {

	public String getMemberID() {
		return memberID;
	}

	@XmlElement(name = "Login")
	private String login;

	@XmlElement(name = "Pwd")
	private String pwd;

	@XmlElement(name = "MemberID")
	private String memberID;

	@XmlElement(name = "ERxReferenceNo")
	private int eRxReferenceNo;

//	@XmlElement(name = "PriorRequest")
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

	public String GeteRxTransactionReques() {
		return memberID;
	}

	public void setMemberID(String memberID) {
		this.memberID = memberID;
	}

	public int geteRxReferenceNo() {
		return eRxReferenceNo;
	}

	public void seteRxReferenceNo(int eRxReferenceNo) {
		this.eRxReferenceNo = eRxReferenceNo;
	}

}
