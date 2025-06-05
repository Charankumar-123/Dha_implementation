package com.ac.dha.entties;

import java.util.Arrays;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "uploadERxRequests")
public class UploadERxRequest {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "uniq_id")
	// @CustomUniqueCode(entityType = "uploadERxRequest", prefix = "ERX",
	// numberWidth = 10)
	private String uniqId;

	private String facilityLogin;

	private String facilityPwd;

	private String clinicianLogin;

	private String clinicianPwd;

	private byte[] fileContent;

	private String fileName; // priorRequestUniqId

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUniqId() {
		return uniqId;
	}

	public void setUniqId(String uniqId) {
		this.uniqId = uniqId;
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

	public byte[] getFileContent() {
		return fileContent;
	}

	public void setFileContent(byte[] fileContent) {
		this.fileContent = fileContent;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public UploadERxRequest(Long id, String uniqId, String facilityLogin, String facilityPwd, String clinicianLogin,
			String clinicianPwd, byte[] fileContent, String fileName) {
		super();
		this.id = id;
		this.uniqId = uniqId;
		this.facilityLogin = facilityLogin;
		this.facilityPwd = facilityPwd;
		this.clinicianLogin = clinicianLogin;
		this.clinicianPwd = clinicianPwd;
		this.fileContent = fileContent;
		this.fileName = fileName;
	}

	public UploadERxRequest() {
	}

	@Override
	public String toString() {
		return "UploadERxRequest [id=" + id + ", uniqId=" + uniqId + ", facilityLogin=" + facilityLogin
				+ ", facilityPwd=" + facilityPwd + ", clinicianLogin=" + clinicianLogin + ", clinicianPwd="
				+ clinicianPwd + ", fileContent=" + Arrays.toString(fileContent) + ", fileName=" + fileName + "]";
	}
}
