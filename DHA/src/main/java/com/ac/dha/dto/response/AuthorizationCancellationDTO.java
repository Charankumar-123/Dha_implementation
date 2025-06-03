package com.ac.dha.dto.response;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import lombok.Data;

@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class AuthorizationCancellationDTO {

	@XmlElement(name = "Type")
	private String type; // Cancellation

	@XmlElement(name = "ID")
	private String id;

	@XmlElement(name = "IDPayer")
	private String idPayer;

	@XmlElement(name = "MemberID")
	private String memberID;

	@XmlElement(name = "PayerID")
	private String payerID;

	@XmlElement(name = "EmiratesIDNumber")
	private String emiratesIDNumber;

	@XmlElement(name = "DateOrdered")
	private String dateOrdered; // Format: DD/MM/YYYY
}
