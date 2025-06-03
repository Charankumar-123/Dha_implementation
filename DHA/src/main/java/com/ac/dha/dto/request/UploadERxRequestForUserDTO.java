package com.ac.dha.dto.request;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;

//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Builder
@XmlAccessorType(XmlAccessType.FIELD)
public class UploadERxRequestForUserDTO {

	@XmlElement(name = "FacilityLogin")
	private String facilityLogin;

	@XmlElement(name = "FacilityPwd")
	private String facilityPwd;

	@XmlElement(name = "ClinicianLogin")
	private String clinicianLogin;

	@XmlElement(name = "ClinicianPwd")
	private String clinicianPwd;

	@XmlElement(name = "FileName")
	private String fileName;

	private ErxRequestDTO priorRequest;

	public String getFacilityLogin() {
		return facilityLogin;
	}

	public void setFacilityLogin(String facilityLogin) {
		this.facilityLogin = facilityLogin;
	}

	public String getFacilityPwd() {
		return facilityPwd;
	}

	public void setFacilityPwd(String facilityPwd) {
		this.facilityPwd = facilityPwd;
	}

	public String getClinicianLogin() {
		return clinicianLogin;
	}

	public void setClinicianLogin(String clinicianLogin) {
		this.clinicianLogin = clinicianLogin;
	}

	public String getClinicianPwd() {
		return clinicianPwd;
	}

	public void setClinicianPwd(String clinicianPwd) {
		this.clinicianPwd = clinicianPwd;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public ErxRequestDTO getPriorRequest() {
		return priorRequest;
	}

	public void setPriorRequest(ErxRequestDTO priorRequest) {
		this.priorRequest = priorRequest;
	}

}
