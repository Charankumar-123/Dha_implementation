package com.ac.dha.dto.response;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import lombok.Data;

@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class AuthorizationActivityDTO {

    @XmlElement(name = "ID")
    private String id;

    @XmlElement(name = "Type")
    private String type;

    @XmlElement(name = "Code")
    private String code;

    @XmlElement(name = "Quantity")
    private Double quantity;

    @XmlElement(name = "Net")
    private Double net;

    @XmlElement(name = "PatientShare")
    private Double patientShare;

    @XmlElement(name = "PaymentAmount")
    private Double paymentAmount;

    @XmlElement(name = "DenialCode")
    private String denialCode; // Optional, only appears if rejected
}
