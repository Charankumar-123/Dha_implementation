package com.ac.dha.dto.response;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import lombok.Data;

@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class SearchTransactionsResponseDTO {

	@XmlElement(name = "FoundTransactions")
	private String foundTransactions;

	@XmlElement(name = "ErrorMessage")
	private String errorMessage;
}
