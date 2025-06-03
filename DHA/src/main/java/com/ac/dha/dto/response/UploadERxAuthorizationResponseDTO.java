package com.ac.dha.dto.response;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import lombok.Data;

@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class UploadERxAuthorizationResponseDTO {

    @XmlElement(name = "ErrorMessage")
    private String errorMessage;

    @XmlElement(name = "ErrorReport")
    private byte[] errorReport;
}
