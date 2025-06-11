package com.ac.dha.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbl_eclaim_prior_activity")
public class AuthActivity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "uniq_id")
	private Long uniqId;

	@Column(name = "authActivity_id")
	private Long acctivityId;

	private String type;

	private String code;

	private String quantity;

	private String net;

	private String patientShare;

	private String paymentAmount;

	@ManyToOne
	@JoinColumn(name = "authAuthorization_id")
	private AuthAuthorization authAuthorization;

	public AuthActivity() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUniqId() {
		return uniqId;
	}

	public void setUniqId(Long uniqId) {
		this.uniqId = uniqId;
	}

	public Long getAcctivityId() {
		return acctivityId;
	}

	public void setAcctivityId(Long acctivityId) {
		this.acctivityId = acctivityId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getNet() {
		return net;
	}

	public void setNet(String net) {
		this.net = net;
	}

	public String getPatientShare() {
		return patientShare;
	}

	public void setPatientShare(String patientShare) {
		this.patientShare = patientShare;
	}

	public String getPaymentAmount() {
		return paymentAmount;
	}

	public void setPaymentAmount(String paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

	public AuthAuthorization getAuthAuthorization() {
		return authAuthorization;
	}

	public void setAuthAuthorization(AuthAuthorization authAuthorization) {
		this.authAuthorization = authAuthorization;
	}

	public AuthActivity(Long id, Long uniqId, Long acctivityId, String type, String code, String quantity, String net,
			String patientShare, String paymentAmount, AuthAuthorization authAuthorization) {
		super();
		this.id = id;
		this.uniqId = uniqId;
		this.acctivityId = acctivityId;
		this.type = type;
		this.code = code;
		this.quantity = quantity;
		this.net = net;
		this.patientShare = patientShare;
		this.paymentAmount = paymentAmount;
		this.authAuthorization = authAuthorization;
	}

	@Override
	public String toString() {
		return "AuthActivity [id=" + id + ", uniqId=" + uniqId + ", acctivityId=" + acctivityId + ", type=" + type
				+ ", code=" + code + ", quantity=" + quantity + ", net=" + net + ", patientShare=" + patientShare
				+ ", paymentAmount=" + paymentAmount + ", authAuthorization=" + authAuthorization + "]";
	}

}
