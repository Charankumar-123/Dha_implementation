package com.ac.dha.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "getnewtransactions_record")
public class GetNewTransactionsRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "login", nullable = false)
    private String login;

    @Column(name = "pwd", nullable = false)
    private String pwd;
    
    @Column(name = "Transaction_result", nullable = true)
    private Integer result;

    @Lob
    @Column(name = "xml_transactions")
    private String xmlTransactions;

   
    @Column(name = "error_message")
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
	
	

	public Integer getResult() {
		return result;
	}

	public void setResult(Integer result) {
		this.result = result;
	}

	

	public GetNewTransactionsRequest(Long id, String login, String pwd, Integer result, String xmlTransactions,
			String errorMessage, String responseStatus, LocalDateTime requestedAt) {
		super();
		this.id = id;
		this.login = login;
		this.pwd = pwd;
		this.result = result;
		this.xmlTransactions = xmlTransactions;
		this.errorMessage = errorMessage;
		this.responseStatus = responseStatus;
		this.requestedAt = requestedAt;
	}

	public GetNewTransactionsRequest() {
		super();
	}

	@Override
	public String toString() {
		return "GetNewTransactionsRequest [id=" + id + ", login=" + login + ", pwd=" + pwd + ", result=" + result
				+ ", xmlTransactions=" + xmlTransactions + ", errorMessage=" + errorMessage + ", responseStatus="
				+ responseStatus + ", requestedAt=" + requestedAt + "]";
	}

	
    
}

