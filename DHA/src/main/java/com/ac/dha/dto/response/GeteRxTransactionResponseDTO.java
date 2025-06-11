package com.ac.dha.dto.response;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import lombok.Data;

@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class GeteRxTransactionResponseDTO {
	
	@XmlElement(name = "ErxTransactionResult")
    private Integer erxTransactionResult;

    @XmlElement(name = "XmlTransactions")
    private String xmlTransactions;

    @XmlElement(name = "ErrorMessage")
    private String errorMessage;
}
