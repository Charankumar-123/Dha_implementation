package com.ac.dha.entties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "uploadERxRequestForUser")
public class UploadERxRequestForUser {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String facilityLogin;

	private String facilityPwd;

	private String clinicianLogin;

	private String clinicianPwd;
	
	private String fileName;
	
	private PriorRequest priorRequest;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public PriorRequest getPriorRequest() {
		return priorRequest;
	}

	public void setPriorRequest(PriorRequest priorRequest) {
		this.priorRequest = priorRequest;
	}

	@Override
	public String toString() {
		return "UploadERxRequestForUser [id=" + id + ", facilityLogin=" + facilityLogin + ", facilityPwd=" + facilityPwd
				+ ", clinicianLogin=" + clinicianLogin + ", clinicianPwd=" + clinicianPwd + ", fileName=" + fileName
				+ ", priorRequest=" + priorRequest + "]";
	}

	public UploadERxRequestForUser(Long id, String facilityLogin, String facilityPwd, String clinicianLogin,
			String clinicianPwd, String fileName, PriorRequest priorRequest) {
		super();
		this.id = id;
		this.facilityLogin = facilityLogin;
		this.facilityPwd = facilityPwd;
		this.clinicianLogin = clinicianLogin;
		this.clinicianPwd = clinicianPwd;
		this.fileName = fileName;
		this.priorRequest = priorRequest;
	}
	
	
}
