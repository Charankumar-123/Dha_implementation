package com.ac.dha.entities;

import jakarta.persistence.*;


import java.time.LocalDateTime;

@Entity
@Table(name = "erx_transaction_record")
public class GeteRxTransactionRequestRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "login")
    private String login;

    @Column(name = "pwd")
    private String pwd;

    @Column(name = "member_id")
    private String memberID;

    @Column(name = "erx_reference_no")
    private int eRxReferenceNo;

    @Column(name = "xml_transactions", columnDefinition = "TEXT")
    private String xmlTransactions;

    @Column(name = "error_message", columnDefinition = "TEXT")
    private String errorMessage;

    @Column(name = "response_status")
    private String responseStatus;

    @Column(name = "requested_at")
    private LocalDateTime requestedAt;

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

	public String getMemberID() {
		return memberID;
	}

	public void setMemberID(String memberID) {
		this.memberID = memberID;
	}

	public int geteRxReferenceNo() {
		return eRxReferenceNo;
	}

	public void seteRxReferenceNo(int eRxReferenceNo) {
		this.eRxReferenceNo = eRxReferenceNo;
	}

	public String getXmlTransactions() {
		return xmlTransactions;
	}

	public void setXmlTransactions(String xmlTransactions) {
		this.xmlTransactions = xmlTransactions;
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

	public GeteRxTransactionRequestRecord(Long id, String login, String pwd, String memberID, int eRxReferenceNo,
			String xmlTransactions, String errorMessage, String responseStatus, LocalDateTime requestedAt) {
		super();
		this.id = id;
		this.login = login;
		this.pwd = pwd;
		this.memberID = memberID;
		this.eRxReferenceNo = eRxReferenceNo;
		this.xmlTransactions = xmlTransactions;
		this.errorMessage = errorMessage;
		this.responseStatus = responseStatus;
		this.requestedAt = requestedAt;
	}

	public GeteRxTransactionRequestRecord() {
		super();
	}

	@Override
	public String toString() {
		return "GeteRxTransactionRequestRecord [id=" + id + ", login=" + login + ", pwd=" + pwd + ", memberID="
				+ memberID + ", eRxReferenceNo=" + eRxReferenceNo + ", xmlTransactions=" + xmlTransactions
				+ ", errorMessage=" + errorMessage + ", responseStatus=" + responseStatus + ", requestedAt="
				+ requestedAt + "]";
	}
    
    
}
