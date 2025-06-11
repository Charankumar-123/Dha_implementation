package com.ac.dha.dto.request;

import java.util.Arrays;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Builder
@XmlRootElement(name = "UploadERxRequest")
@XmlAccessorType(XmlAccessType.FIELD)
public class UploadERxRequestDTO {

	// @Column(name = "uniq_id", nullable = false)
	// private String uniqId;

	@XmlElement(name = "FacilityLogin")
	private String facilityLogin;

	@XmlElement(name = "FacilityPwd")
	private String facilityPwd;

	@XmlElement(name = "ClinicianLogin")
	private String clinicianLogin;

	@XmlElement(name = "ClinicianPwd")
	private String clinicianPwd;

	@XmlElement(name = "FileContent")
	private byte[] fileContent;

	@XmlElement(name = "FileName")
	private String fileName;

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

	public UploadERxRequestDTO(String facilityLogin, String facilityPwd, String clinicianLogin, String clinicianPwd,
			byte[] fileContent, String fileName, Integer eRxReferenceNo, String errorMessage, byte[] errorReport) {
		super();

		this.facilityLogin = facilityLogin;
		this.facilityPwd = facilityPwd;
		this.clinicianLogin = clinicianLogin;
		this.clinicianPwd = clinicianPwd;
		this.fileContent = fileContent;
		this.fileName = fileName;
	}

	// @Override
	// public String toString() {
	// return ", facilityLogin=" + facilityLogin + ", facilityPwd="
	// + facilityPwd + ", clinicianLogin=" + clinicianLogin + ", clinicianPwd=" +
	// clinicianPwd
	// + ", fileContent=" + Arrays.toString(fileContent) + ", fileName=" + fileName
	// + "]";
	// }

	// public String getUniqId() {
	// return uniqId;
	// }
	//
	// public void setUniqId(String uniqId) {
	// this.uniqId = uniqId;
	// }
	// =======
	//
	// }

	@Override
	public String toString() {
		return "UploadERxRequestDTO [facilityLogin=" + facilityLogin + ", facilityPwd=" + facilityPwd
				+ ", clinicianLogin=" + clinicianLogin + ", clinicianPwd=" + clinicianPwd + ", fileContent="
				+ Arrays.toString(fileContent) + ", fileName=" + fileName + "]";
	}

	public UploadERxRequestDTO() {
		super();
	}

}
