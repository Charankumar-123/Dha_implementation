package com.ac.dha.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbl_eclaim_getNewTrxReq")
public class GetNewTransactionsRequest {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String login;

	private String pwd;

	public GetNewTransactionsRequest() {
	}

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

	@Override
	public String toString() {
		return "GetNewTransactionsRequest [id=" + id + ", login=" + login + ", pwd=" + pwd + "]";
	}

	public GetNewTransactionsRequest(Long id, String login, String pwd) {
		super();
		this.id = id;
		this.login = login;
		this.pwd = pwd;
	}

}
