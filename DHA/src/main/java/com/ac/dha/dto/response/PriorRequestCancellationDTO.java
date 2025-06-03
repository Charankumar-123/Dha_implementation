package com.ac.dha.dto.response;

import com.ac.dha.dto.request.HeaderDTO;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;

@XmlRootElement(name = "Prior.Request")
@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class PriorRequestCancellationDTO {

	@XmlElement(name = "Header")
	private HeaderDTO header;

	@XmlElement(name = "Authorization")
	private AuthorizationCancellationDTO authorization;
}
