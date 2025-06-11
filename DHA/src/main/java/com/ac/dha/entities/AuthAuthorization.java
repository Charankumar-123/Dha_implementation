package com.ac.dha.entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbl_eclaim_prior_auth")
public class AuthAuthorization {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "uniq_id")
	private Long uniq_id;

	private String result;

	@Column(name = "authAuthorization_id")
	private String AuthID;

	@Column(name = "payer_id")
	private String idPayer;

	@Column(name = "start_date")
	private String start;

	@Column(name = "end_date")
	private String end;

	private String comments;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "authAuthorization_id")
	private List<AuthActivity> authActivity;

	public AuthAuthorization() {
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

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getAuthID() {
		return AuthID;
	}

	public void setAuthID(String authID) {
		AuthID = authID;
	}

	public String getIdPayer() {
		return idPayer;
	}

	public void setIdPayer(String idPayer) {
		this.idPayer = idPayer;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public List<AuthActivity> getAuthActivity() {
		return authActivity;
	}

	public void setAuthActivity(List<AuthActivity> authActivity) {
		this.authActivity = authActivity;
	}

	@Override
	public String toString() {
		return "AuthAuthorization [id=" + id + ", uniq_id=" + uniq_id + ", result=" + result + ", AuthID=" + AuthID
				+ ", idPayer=" + idPayer + ", start=" + start + ", end=" + end + ", comments=" + comments
				+ ", authActivity=" + authActivity + "]";
	}

	public AuthAuthorization(Long id, Long uniq_id, String result, String authID, String idPayer, String start,
			String end, String comments, List<AuthActivity> authActivity) {
		super();
		this.id = id;
		this.uniq_id = uniq_id;
		this.result = result;
		AuthID = authID;
		this.idPayer = idPayer;
		this.start = start;
		this.end = end;
		this.comments = comments;
		this.authActivity = authActivity;
	}

}
