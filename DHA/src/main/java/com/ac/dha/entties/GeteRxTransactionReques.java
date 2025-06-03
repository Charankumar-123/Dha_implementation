package com.ac.dha.entties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "uploadERxRequests")
public class GeteRxTransactionReques {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String login;

	private String pwd;

	private String memberID;

	private int eRxReferenceNo;

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

	@Override
	public String toString() {
		return "GeteRxTransactionReques [id=" + id + ", login=" + login + ", pwd=" + pwd + ", memberID=" + memberID
				+ ", eRxReferenceNo=" + eRxReferenceNo + "]";
	}

	public GeteRxTransactionReques(Long id, String login, String pwd, String memberID, int eRxReferenceNo) {
		super();
		this.id = id;
		this.login = login;
		this.pwd = pwd;
		this.memberID = memberID;
		this.eRxReferenceNo = eRxReferenceNo;
	}

}
