package com.ac.dha.dto.response;

import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import lombok.Data;

@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class AuthorizationApprovalDTO {

    @XmlElement(name = "Result")
    private String result; // Yes or No

    @XmlElement(name = "ID")
    private String id;

    @XmlElement(name = "IDPayer")
    private String idPayer;

    @XmlElement(name = "Start")
    private String start; // Format: DD/MM/YYYY HH:mm

    @XmlElement(name = "End")
    private String end;

    @XmlElement(name = "Comments")
    private String comments;

    @XmlElement(name = "Activity")
    private List<AuthorizationActivityDTO> activity;
}
