package com.ac.dha.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbl_eclaim_setTrxDwRq")
public class SetTransactionDownloadedRequest {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String login;

	private String pwd;

	private String fileID;
	
	public SetTransactionDownloadedRequest() {}

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

	@Override
	public String toString() {
		return "SetTransactionDownloadedRequest [id=" + id + ", login=" + login + ", pwd=" + pwd + ", fileID=" + fileID
				+ "]";
	}

	public SetTransactionDownloadedRequest(Long id, String login, String pwd, String fileID) {
		super();
		this.id = id;
		this.login = login;
		this.pwd = pwd;
		this.fileID = fileID;
	}

}
