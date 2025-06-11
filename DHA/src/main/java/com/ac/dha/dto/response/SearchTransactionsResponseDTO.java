package com.ac.dha.dto.response;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "SearchTransactionsResponseDTO")
//@Data
public class SearchTransactionsResponseDTO {
	
	@XmlElement(name = "SearchTransactionsResult")
	private Integer searchTransactionsResult;

	public SearchTransactionsResponseDTO() {
	}

	@Override
	public String toString() {
		return "SearchTransactionsResponseDTO [foundTransactions=" + foundTransactions + ", errorMessage="
				+ errorMessage + "]";
	}

	public SearchTransactionsResponseDTO(String foundTransactions, String errorMessage) {
		super();
		this.foundTransactions = foundTransactions;
		this.errorMessage = errorMessage;
	}

	public String getFoundTransactions() {
		return foundTransactions;
	}

	public void setFoundTransactions(String foundTransactions) {
		this.foundTransactions = foundTransactions;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	@XmlElement(name = "FoundTransactions")
	private String foundTransactions;

	@XmlElement(name = "ErrorMessage")
	private String errorMessage;

}
