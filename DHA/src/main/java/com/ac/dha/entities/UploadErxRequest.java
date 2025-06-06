package com.ac.dha.entities;

import java.time.LocalDateTime;
import java.util.Arrays;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "erx_upload_record")
public class UploadErxRequest {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "facility_login", nullable = false)
	private String facilityLogin;

	@Column(name = "facility_pwd", nullable = false)
	private String facilityPwd;

	@Column(name = "clinician_login", nullable = false)
	private String clinicianLogin;

	@Column(name = "clinician_pwd", nullable = false)
	private String clinicianPwd;

	@Column(name = "file_name", nullable = false)
	private String fileName;

	@Column(name = "file_content", nullable = false)
	private byte[] fileContent;

	@Column(name = "upload_date", nullable = false)
	private LocalDateTime uploadDate;

//	@Column(name = "e_rx_reference_no")
//	private Integer eRxReferenceNo;

	@Column(name = "error_message")
	private String errorMessage;

	@Column(name = "error_report")
	private byte[] errorReport;

	@Column(name = "response_status")
	private String responseStatus;
//
//    @Column(name = "transaction_id")
//    private String transactionId;

	// Default constructor
	public UploadErxRequest(String facilityLogin, String facilityPwd, String clinicianLogin, String clinicianPwd,
			String fileName, byte[] fileContent, LocalDateTime uploadDate) {
		this.facilityLogin = facilityLogin;
		this.facilityPwd = facilityPwd;
		this.clinicianLogin = clinicianLogin;
		this.clinicianPwd = clinicianPwd;
		this.fileName = fileName;
		this.fileContent = fileContent;
		this.uploadDate = uploadDate;
	}

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

	public byte[] getFileContent() {
		return fileContent;
	}

	public void setFileContent(byte[] fileContent) {
		this.fileContent = fileContent;
	}

	public LocalDateTime getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(LocalDateTime uploadDate) {
		this.uploadDate = uploadDate;
	}

//	public Integer geteRxReferenceNo() {
//		return eRxReferenceNo;
//	}
//
//	public void seteRxReferenceNo(Integer eRxReferenceNo) {
//		this.eRxReferenceNo = eRxReferenceNo;
//	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public byte[] getErrorReport() {
		return errorReport;
	}

	public void setErrorReport(byte[] errorReport) {
		this.errorReport = errorReport;
	}

	public String getResponseStatus() {
		return responseStatus;
	}

	public void setResponseStatus(String responseStatus) {
		this.responseStatus = responseStatus;
	}

	public UploadErxRequest(Long id, String facilityLogin, String facilityPwd, String clinicianLogin,
			String clinicianPwd, String fileName, byte[] fileContent, LocalDateTime uploadDate, Integer eRxReferenceNo,
			String errorMessage, byte[] errorReport, String responseStatus) {
		super();
		this.id = id;
		this.facilityLogin = facilityLogin;
		this.facilityPwd = facilityPwd;
		this.clinicianLogin = clinicianLogin;
		this.clinicianPwd = clinicianPwd;
		this.fileName = fileName;
		this.fileContent = fileContent;
		this.uploadDate = uploadDate;
//		this.eRxReferenceNo = eRxReferenceNo;
		this.errorMessage = errorMessage;
		this.errorReport = errorReport;
		this.responseStatus = responseStatus;
	}

	public UploadErxRequest() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "UploadErxRequest [id=" + id + ", facilityLogin=" + facilityLogin + ", facilityPwd=" + facilityPwd
				+ ", clinicianLogin=" + clinicianLogin + ", clinicianPwd=" + clinicianPwd + ", fileName=" + fileName
				+ ", fileContent=" + Arrays.toString(fileContent) + ", uploadDate=" + uploadDate + ", errorMessage="
				+ errorMessage + ", errorReport=" + Arrays.toString(errorReport) + ", responseStatus=" + responseStatus
				+ ", transactionId=" + "]";
	}

}