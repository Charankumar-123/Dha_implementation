package com.ac.dha.dto.request;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class CancelAuthorizationDTO {

	@XmlElement(name = "Type")
	private String type;

	@XmlElement(name = "ID")
	private String authId;

	@XmlElement(name = "IDPayer")
	private String IdPayer;

	@XmlElement(name = "MemberID")
	private String memberID;

	@XmlElement(name = "PayerID")
	private String payerID;

	@XmlElement(name = "EmiratesIDNumber")
	private String emiratesIDNumber;

	@XmlElement(name = "DateOrdered")
	private String dateOrdered;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAuthId() {
		return authId;
	}

	public void setAuthId(String authId) {
		this.authId = authId;
	}

	public String getIDPayer() {
		return IdPayer;
	}

	public void setIiPayer(String IdPayer) {
		this.IdPayer = IdPayer;
	}

	public String getMemberID() {
		return memberID;
	}

	public void setMemberID(String memberID) {
		this.memberID = memberID;
	}

	public String getPayerID() {
		return payerID;
	}

	public void setPayerID(String payerID) {
		this.payerID = payerID;
	}

	public String getEmiratesIDNumber() {
		return emiratesIDNumber;
	}

	public void setEmiratesIDNumber(String emiratesIDNumber) {
		this.emiratesIDNumber = emiratesIDNumber;
	}

	public String getDateOrdered() {
		return dateOrdered;
	}

	public void setDateOrdered(String dateOrdered) {
		this.dateOrdered = dateOrdered;
	}

	@Override
	public String toString() {
		return "CancelAuthorizationDTO [type=" + type + ", authId=" + authId + ", IiPayer=" + IdPayer + ", memberID="
				+ memberID + ", payerID=" + payerID + ", emiratesIDNumber=" + emiratesIDNumber + ", dateOrdered="
				+ dateOrdered + "]";
	}

	public CancelAuthorizationDTO(String type, String authId, String IdPayer, String memberID, String payerID,
			String emiratesIDNumber, String dateOrdered) {
		super();
		this.type = type;
		this.authId = authId;
		this.IdPayer = IdPayer;
		this.memberID = memberID;
		this.payerID = payerID;
		this.emiratesIDNumber = emiratesIDNumber;
		this.dateOrdered = dateOrdered;
	}

}
