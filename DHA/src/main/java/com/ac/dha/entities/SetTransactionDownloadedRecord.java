package com.ac.dha.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "set_transaction_downloaded_record")
public class SetTransactionDownloadedRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "login")
    private String login;

    @Column(name = "pwd")
    private String pwd;

    @Column(name = "file_id")
    private String fileID;

    @Column(name = "error_message", columnDefinition = "TEXT")
    private String errorMessage;
    
    @Column(name = "set_transacationDownload_result")
    private Integer setTransactionDownloadedResult;
    
    @Column(name = "response_status")
    private String responseStatus;

    @Column(name = "requested_at")
    private LocalDateTime requestedAt;

    // Getters and Setters
    
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
    public String getFileID() {
        return fileID;
    }
    public void setFileID(String fileID) {
        this.fileID = fileID;
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
    
    
	public Integer getSetTransactionDownloadedResult() {
		return setTransactionDownloadedResult;
	}
	public void setSetTransactionDownloadedResult(Integer setTransactionDownloadedResult) {
		this.setTransactionDownloadedResult = setTransactionDownloadedResult;
	}
	
	
	
	
	public SetTransactionDownloadedRecord(Long id, String login, String pwd, String fileID, String errorMessage,
			Integer setTransactionDownloadedResult, String responseStatus, LocalDateTime requestedAt) {
		super();
		this.id = id;
		this.login = login;
		this.pwd = pwd;
		this.fileID = fileID;
		this.errorMessage = errorMessage;
		this.setTransactionDownloadedResult = setTransactionDownloadedResult;
		this.responseStatus = responseStatus;
		this.requestedAt = requestedAt;
	}
	
	public SetTransactionDownloadedRecord() {
		super();
	}
	@Override
	public String toString() {
		return "SetTransactionDownloadedRecord [id=" + id + ", login=" + login + ", pwd=" + pwd + ", fileID=" + fileID
				+ ", errorMessage=" + errorMessage + ", setTransactionDownloadedResult="
				+ setTransactionDownloadedResult + ", responseStatus=" + responseStatus + ", requestedAt=" + requestedAt
				+ "]";
	}
	
	
	
}
