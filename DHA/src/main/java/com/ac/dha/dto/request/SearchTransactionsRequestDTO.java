package com.ac.dha.dto.request;

import java.time.LocalDateTime;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
//
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Builder

@XmlRootElement(name = "SearchTransactionsRequest")
@XmlAccessorType(XmlAccessType.FIELD)
public class SearchTransactionsRequestDTO {

	@XmlElement(name = "Login")
	private String login;

	@XmlElement(name = "Pwd")
	private String pwd;

	@XmlElement(name = "Direction")
	private String direction;

	@XmlElement(name = "CallerLicense")
	private String callerLicense;

	@XmlElement(name = "ClinicianLicense")
	private String clinicianLicense;

	@XmlElement(name = "MemberID")
	private String memberID;

	@XmlElement(name = "ERxReferenceNo")
	private String eRxReferenceNo;

	@XmlElement(name = "TransactionStatus")
	private String transactionStatus;

	@XmlElement(name = "TransactionFromDate")
	private LocalDateTime transactionFromDate;

	@XmlElement(name = "TransactionToDate")
	private LocalDateTime transactionToDate;

	@XmlElement(name = "MinRecordCount")
	private int minRecordCount;

	@XmlElement(name = "MaxRecordCount")
	private int maxRecordCount;

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public String getCallerLicense() {
		return callerLicense;
	}

	public void setCallerLicense(String callerLicense) {
		this.callerLicense = callerLicense;
	}

	public String getClinicianLicense() {
		return clinicianLicense;
	}

	public void setClinicianLicense(String clinicianLicense) {
		this.clinicianLicense = clinicianLicense;
	}

	public String getMemberID() {
		return memberID;
	}

	public void setMemberID(String memberID) {
		this.memberID = memberID;
	}

	public String geteRxReferenceNo() {
		return eRxReferenceNo;
	}

	public void seteRxReferenceNo(String eRxReferenceNo) {
		this.eRxReferenceNo = eRxReferenceNo;
	}

	public String getTransactionStatus() {
		return transactionStatus;
	}

	public void setTransactionStatus(String transactionStatus) {
		this.transactionStatus = transactionStatus;
	}

	public LocalDateTime getTransactionFromDate() {
		return transactionFromDate;
	}

	public void setTransactionFromDate(LocalDateTime transactionFromDate) {
		this.transactionFromDate = transactionFromDate;
	}

	public LocalDateTime getTransactionToDate() {
		return transactionToDate;
	}

	public void setTransactionToDate(LocalDateTime transactionToDate) {
		this.transactionToDate = transactionToDate;
	}

	public int getMinRecordCount() {
		return minRecordCount;
	}

	public void setMinRecordCount(int minRecordCount) {
		this.minRecordCount = minRecordCount;
	}

	public int getMaxRecordCount() {
		return maxRecordCount;
	}

	public void setMaxRecordCount(int maxRecordCount) {
		this.maxRecordCount = maxRecordCount;
	}

	public SearchTransactionsRequestDTO(String login, String pwd, String direction, String callerLicense,
			String clinicianLicense, String memberID, String eRxReferenceNo, String transactionStatus,
			LocalDateTime transactionFromDate, LocalDateTime transactionToDate, int minRecordCount,
			int maxRecordCount) {
		super();
		this.login = login;
		this.pwd = pwd;
		this.direction = direction;
		this.callerLicense = callerLicense;
		this.clinicianLicense = clinicianLicense;
		this.memberID = memberID;
		this.eRxReferenceNo = eRxReferenceNo;
		this.transactionStatus = transactionStatus;
		this.transactionFromDate = transactionFromDate;
		this.transactionToDate = transactionToDate;
		this.minRecordCount = minRecordCount;
		this.maxRecordCount = maxRecordCount;
	}

	public SearchTransactionsRequestDTO() {
		super();
	}

	@Override
	public String toString() {
		return "SearchTransactionsRequestDTO [login=" + login + ", pwd=" + pwd + ", direction=" + direction
				+ ", callerLicense=" + callerLicense + ", clinicianLicense=" + clinicianLicense + ", memberID="
				+ memberID + ", eRxReferenceNo=" + eRxReferenceNo + ", transactionStatus=" + transactionStatus
				+ ", transactionFromDate=" + transactionFromDate + ", transactionToDate=" + transactionToDate
				+ ", minRecordCount=" + minRecordCount + ", maxRecordCount=" + maxRecordCount + "]";
	}
	
	
	

}
