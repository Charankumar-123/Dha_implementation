package com.ac.dha.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Arrays;

@Entity
@Table(name = "download_transaction_file_record")
public class DownloadTransactionFileRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "login")
    private String login;

    @Column(name = "pwd")
    private String pwd;

    @Column(name = "file_id")
    private String fileID;

    @Column(name = "file_name")
    private String fileName;

    @Lob
    @Column(name = "file_data")
    private byte[] file;

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

	public String getFileID() {
		return fileID;
	}

	public void setFileID(String fileID) {
		this.fileID = fileID;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public byte[] getFile() {
		return file;
	}

	public void setFile(byte[] file) {
		this.file = file;
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

	public DownloadTransactionFileRecord(Long id, String login, String pwd, String fileID, String fileName, byte[] file,
			String errorMessage, String responseStatus, LocalDateTime requestedAt) {
		super();
		this.id = id;
		this.login = login;
		this.pwd = pwd;
		this.fileID = fileID;
		this.fileName = fileName;
		this.file = file;
		this.errorMessage = errorMessage;
		this.responseStatus = responseStatus;
		this.requestedAt = requestedAt;
	}

	public DownloadTransactionFileRecord() {
		super();
	}

	@Override
	public String toString() {
		return "DownloadTransactionFileRecord [id=" + id + ", login=" + login + ", pwd=" + pwd + ", fileID=" + fileID
				+ ", fileName=" + fileName + ", file=" + Arrays.toString(file) + ", errorMessage=" + errorMessage
				+ ", responseStatus=" + responseStatus + ", requestedAt=" + requestedAt + "]";
	}
    
    
}

