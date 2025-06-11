package com.ac.dha.dto.response;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "GetNewTransactionsResponseDTO")
//@Data
public class GetNewTransactionsResponseDTO {
	
	@XmlElement(name = "GetNewTransactionsResult")
    private Integer result;

	@XmlElement(name = "XmlTransactions")
	private String xmlTransactions;

	@XmlElement(name = "ErrorMessage")
	private String errorMessage;

	public GetNewTransactionsResponseDTO() {
	}

	public String getXmlTransactions() {
		return xmlTransactions;
	}

	public void setXmlTransactions(String xmlTransactions) {
		this.xmlTransactions = xmlTransactions;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	@Override
	public String toString() {
		return "GetNewTransactionsResponseDTO [xmlTransactions=" + xmlTransactions + ", errorMessage=" + errorMessage
				+ "]";
	}

	public GetNewTransactionsResponseDTO(String xmlTransactions, String errorMessage) {
		super();
		this.xmlTransactions = xmlTransactions;
		this.errorMessage = errorMessage;
	}

}
