package com.ac.dha.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbl_eclaim_searchTrxReq")
public class SearchTransactionsRequest {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String login;

	private String pwd;

	private String direction;

	private String callerLicense;

	private String clinicianLicense;

	private String memberID;

	private String eRxReferenceNo;

	private String transactionStatus;

	private LocalDateTime transactionFromDate;

	private LocalDateTime transactionToDate;

	private int minRecordCount;

	private int maxRecordCount;
	
	public SearchTransactionsRequest() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public SearchTransactionsRequest(Long id, String login, String pwd, String direction, String callerLicense,
			String clinicianLicense, String memberID, String eRxReferenceNo, String transactionStatus,
			LocalDateTime transactionFromDate, LocalDateTime transactionToDate, int minRecordCount,
			int maxRecordCount) {
		super();
		this.id = id;
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

	@Override
	public String toString() {
		return "SearchTransactionsRequest [id=" + id + ", login=" + login + ", pwd=" + pwd + ", direction=" + direction
				+ ", callerLicense=" + callerLicense + ", clinicianLicense=" + clinicianLicense + ", memberID="
				+ memberID + ", eRxReferenceNo=" + eRxReferenceNo + ", transactionStatus=" + transactionStatus
				+ ", transactionFromDate=" + transactionFromDate + ", transactionToDate=" + transactionToDate
				+ ", minRecordCount=" + minRecordCount + ", maxRecordCount=" + maxRecordCount + "]";
	}

}
