package com.ac.dha.dto.response;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;

@XmlRootElement(name = "Response")
@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class UploadERxRequestResponseDTO {

    @XmlElement(name = "ERxReferenceNo")
    private int eRxReferenceNo;

    @XmlElement(name = "ErrorMessage")
    private String errorMessage;

    @XmlElement(name = "ErrorReport")
    private byte[] errorReport;
}