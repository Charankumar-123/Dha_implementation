package com.ac.dha.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "search_transactions_request")
public class SearchTransactionsRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "login")
    private String login;

    @Column(name = "pwd")
    private String pwd;

    @Column(name = "direction")
    private String direction;

    @Column(name = "caller_license")
    private String callerLicense;

    @Column(name = "clinician_license")
    private String clinicianLicense;

    @Column(name = "member_id")
    private String memberID;

    @Column(name = "erx_reference_no")
    private String eRxReferenceNo;

    @Column(name = "transaction_status")
    private String transactionStatus;

    @Column(name = "transaction_from_date")
    private LocalDateTime transactionFromDate;

    @Column(name = "transaction_to_date")
    private LocalDateTime transactionToDate;

    @Column(name = "min_record_count")
    private Integer minRecordCount;

    @Column(name = "max_record_count")
    private Integer maxRecordCount;

    @Column(name = "found_transactions", columnDefinition = "TEXT")
    private String foundTransactions;

    @Column(name = "error_message", columnDefinition = "TEXT")
    private String errorMessage;

    @Column(name = "response_status")
    private String responseStatus;

    @Column(name = "requested_at")
    private LocalDateTime requestedAt;

    // Getters and Setters below

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

    public void setDirection(String string) {
        this.direction = string;
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

    public String getERxReferenceNo() {
        return eRxReferenceNo;
    }

    public void setERxReferenceNo(String string) {
        this.eRxReferenceNo = string;
    }

    public String getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(String string) {
        this.transactionStatus = string;
    }

    public LocalDateTime getTransactionFromDate() {
        return transactionFromDate;
    }

    public void setTransactionFromDate(LocalDateTime localDateTime) {
        this.transactionFromDate = localDateTime;
    }

    public LocalDateTime getTransactionToDate() {
        return transactionToDate;
    }

    public void setTransactionToDate(LocalDateTime localDateTime) {
        this.transactionToDate = localDateTime;
    }

    public Integer getMinRecordCount() {
        return minRecordCount;
    }

    public void setMinRecordCount(Integer minRecordCount) {
        this.minRecordCount = minRecordCount;
    }

    public Integer getMaxRecordCount() {
        return maxRecordCount;
    }

    public void setMaxRecordCount(Integer maxRecordCount) {
        this.maxRecordCount = maxRecordCount;
    }

    public String getFoundTransactions() {
        return foundTransactions;
    }

    public void setFoundTransactions(String foundTransactions) {
        this.foundTransactions = foundTransactions;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(String responseStatus) {
        this.responseStatus = responseStatus;
    }

    public LocalDateTime getRequestedAt() {
        return requestedAt;
    }

    public void setRequestedAt(LocalDateTime requestedAt) {
        this.requestedAt = requestedAt;
    }

	public SearchTransactionsRequest(Long id, String login, String pwd, String direction, String callerLicense,
			String clinicianLicense, String memberID, String eRxReferenceNo, String transactionStatus,
			LocalDateTime transactionFromDate, LocalDateTime transactionToDate, Integer minRecordCount, Integer maxRecordCount,
			String foundTransactions, String errorMessage, String responseStatus, LocalDateTime requestedAt) {
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
		this.foundTransactions = foundTransactions;
		this.errorMessage = errorMessage;
		this.responseStatus = responseStatus;
		this.requestedAt = requestedAt;
	}

	public SearchTransactionsRequest() {
		super();
	}

	@Override
	public String toString() {
		return "SearchTransactionsRequest [id=" + id + ", login=" + login + ", pwd=" + pwd + ", direction=" + direction
				+ ", callerLicense=" + callerLicense + ", clinicianLicense=" + clinicianLicense + ", memberID="
				+ memberID + ", eRxReferenceNo=" + eRxReferenceNo + ", transactionStatus=" + transactionStatus
				+ ", transactionFromDate=" + transactionFromDate + ", transactionToDate=" + transactionToDate
				+ ", minRecordCount=" + minRecordCount + ", maxRecordCount=" + maxRecordCount + ", foundTransactions="
				+ foundTransactions + ", errorMessage=" + errorMessage + ", responseStatus=" + responseStatus
				+ ", requestedAt=" + requestedAt + "]";
	}
    
    
}
