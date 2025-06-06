package com.ac.dha.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Builder

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

    @JsonProperty("fileName")
    private String fileName;

    @JsonProperty("priorRequest")
    private ErxRequestDTO priorRequest;

    public UploadERxRequestForUserDTO() {} // Required no-arg constructor

    public UploadERxRequestForUserDTO(String facilityLogin, String facilityPwd, String clinicianLogin,
                                      String clinicianPwd, String fileName, ErxRequestDTO priorRequest) {
        this.facilityLogin = facilityLogin;
        this.facilityPwd = facilityPwd;
        this.clinicianLogin = clinicianLogin;
        this.clinicianPwd = clinicianPwd;
        this.fileName = fileName;
        this.priorRequest = priorRequest;
    }
	@Override
	public String toString() {
		return "UploadERxRequestForUserDTO [facilityLogin=" + facilityLogin + ", facilityPwd=" + facilityPwd
				+ ", clinicianLogin=" + clinicianLogin + ", clinicianPwd=" + clinicianPwd + ", fileName=" + fileName
				+ ", priorRequest=" + priorRequest + "]";
	}

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
