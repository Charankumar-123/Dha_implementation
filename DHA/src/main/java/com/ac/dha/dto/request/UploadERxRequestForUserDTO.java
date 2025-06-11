package com.ac.dha.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;


@XmlAccessorType(XmlAccessType.FIELD)
public class UploadERxRequestForUserDTO {

	@JsonProperty("facilityLogin")
    private String facilityLogin;

    @JsonProperty("facilityPwd")
    private String facilityPwd;

    @JsonProperty("clinicianLogin")
    private String clinicianLogin;

    @JsonProperty("clinicianPwd")
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
	


	public UploadERxRequestForUserDTO(String facilityLogin, String facilityPwd, String clinicianLogin,
			String clinicianPwd, String fileName, Integer eRxReferenceNo, String errorMessage, byte[] errorReport,
			ErxRequestDTO priorRequest) {
		super();
		this.facilityLogin = facilityLogin;
		this.facilityPwd = facilityPwd;
		this.clinicianLogin = clinicianLogin;
		this.clinicianPwd = clinicianPwd;
		this.fileName = fileName;
		this.priorRequest = priorRequest;
	}


	public UploadERxRequestForUserDTO() {
		super();
	}


	@Override
	public String toString() {
		return "UploadERxRequestForUserDTO [facilityLogin=" + facilityLogin + ", facilityPwd=" + facilityPwd
				+ ", clinicianLogin=" + clinicianLogin + ", clinicianPwd=" + clinicianPwd + ", fileName=" + fileName
				+ ", priorRequest=" + priorRequest + "]";
	}


	
	

}
