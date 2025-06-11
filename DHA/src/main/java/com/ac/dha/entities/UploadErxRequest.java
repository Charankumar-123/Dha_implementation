package com.ac.dha.entities;

import java.util.Arrays;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "tbl_eclaim_uploadERxReq")
public class UploadERxRequest {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "uniq_id", nullable = false)
	private String uniqId;

	@Column(name = "facility_login")
	private String facilityLogin;

	@Column(name = "facility_pwd")
	private String facilityPwd;

	@Column(name = "clinician_login")
	private String clinicianLogin;

	@Column(name = "clinician_pwd")
	private String clinicianPwd;

	@Column(name = "file_name")
	private String fileName;

	@Column(name = "file_content")
	private byte[] fileContent;

	@Column(name = "e_rx_reference_no") 
	private String  eRxReferenceNo;

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

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public byte[] getFileContent() {
		return fileContent;
	}

	public void setFileContent(byte[] fileContent) {
		this.fileContent = fileContent;
	}

	public String  geteRxReferenceNo() {
		return eRxReferenceNo;
	}

	public void seteRxReferenceNo(String generatedERxRefNo) {
		this.eRxReferenceNo = generatedERxRefNo;
	}

	@Override
	public String toString() {
		return "UploadERxRequest [id=" + id + ", uniqId=" + uniqId + ", facilityLogin=" + facilityLogin
				+ ", facilityPwd=" + facilityPwd + ", clinicianLogin=" + clinicianLogin + ", clinicianPwd="
				+ clinicianPwd + ", fileName=" + fileName + ", fileContent=" + Arrays.toString(fileContent) + "]";
		// ", eRxReferenceNo=" + eRxReferenceNo +
	}

	public UploadERxRequest(Long id, String uniqId, String facilityLogin, String facilityPwd, String clinicianLogin,
			String clinicianPwd, String fileName, byte[] fileContent) {
		super();
		this.id = id;
		this.uniqId = uniqId;
		this.facilityLogin = facilityLogin;
		this.facilityPwd = facilityPwd;
		this.clinicianLogin = clinicianLogin;
		this.clinicianPwd = clinicianPwd;
		this.fileName = fileName;
		this.fileContent = fileContent;
//		this.eRxReferenceNo = eRxReferenceNo;
	}

	public UploadERxRequest() {
		// TODO Auto-generated constructor stub
	}

}