package com.ac.dha.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbl_eclaim_prior_authCancel")
public class CancelAuthorization {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "uniq_id")
	private Long uniq_id;

	private String type;

	@Column(name = "cancelAuthorization_id")
	private String authId;

	@Column(name = "idPayer")
	private String idPayer;

	@Column(name = "member_id")
	private String memberId;

	@Column(name = "payer_id")
	private String payerID;

	private String emiratesIDNumber;

	private String dateOrdered;

	public CancelAuthorization() {
	}

	public CancelAuthorization(Long id, Long uniq_id, String type, String authId, String idPayer, String memberId,
			String payerID, String emiratesIDNumber, String dateOrdered) {
		super();
		this.id = id;
		this.uniq_id = uniq_id;
		this.type = type;
		this.authId = authId;
		this.idPayer = idPayer;
		this.memberId = memberId;
		this.payerID = payerID;
		this.emiratesIDNumber = emiratesIDNumber;
		this.dateOrdered = dateOrdered;
	}

	@Override
	public String toString() {
		return "CancelAuthorization [id=" + id + ", uniq_id=" + uniq_id + ", type=" + type + ", authId=" + authId
				+ ", idPayer=" + idPayer + ", memberId=" + memberId + ", payerID=" + payerID + ", emiratesIDNumber="
				+ emiratesIDNumber + ", dateOrdered=" + dateOrdered + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUniq_id() {
		return uniq_id;
	}

	public void setUniq_id(Long uniq_id) {
		this.uniq_id = uniq_id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAuthId() {
		return authId;
	}

	public void setAuthId(String authId) {
		this.authId = authId;
	}

	public String getIdPayer() {
		return idPayer;
	}

	public void setIdPayer(String idPayer) {
		this.idPayer = idPayer;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getPayerID() {
		return payerID;
	}

	public void setPayerID(String payerID) {
		this.payerID = payerID;
	}

	public String getEmiratesIDNumber() {
		return emiratesIDNumber;
	}

	public void setEmiratesIDNumber(String emiratesIDNumber) {
		this.emiratesIDNumber = emiratesIDNumber;
	}

	public String getDateOrdered() {
		return dateOrdered;
	}

	public void setDateOrdered(String dateOrdered) {
		this.dateOrdered = dateOrdered;
	}

}
