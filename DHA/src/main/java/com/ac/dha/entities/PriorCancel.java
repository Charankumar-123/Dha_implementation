package com.ac.dha.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbl_eclaim_prior_cancel")
public class PriorCancel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "uniq_id")
	private Long uniq_id;

	@Column(name = "create_by")
	public String createBy;

	@Column(name = "create_on")
	// @EpochTime
	public Long createOn;

	@Column(name = "patient_uhid")
	private String patientUhid;

	@Column(name = "patient_no")
	private String patientNo;

	@Column(name = "header_uniq_id")
	private String headerUniqId;

	@Column(name = "cancelAuthorization_uniq_id")
	private String cancelAuthorizationUniqId;

	public PriorCancel() {
	}

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "header_id")
	private Header header;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "cancelAuthorization_id")
	private CancelAuthorization authAuthorization;

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

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Long getCreateOn() {
		return createOn;
	}

	public void setCreateOn(Long createOn) {
		this.createOn = createOn;
	}

	public String getPatientUhid() {
		return patientUhid;
	}

	public void setPatientUhid(String patientUhid) {
		this.patientUhid = patientUhid;
	}

	public String getPatientNo() {
		return patientNo;
	}

	public void setPatientNo(String patientNo) {
		this.patientNo = patientNo;
	}

	public String getHeaderUniqId() {
		return headerUniqId;
	}

	public void setHeaderUniqId(String headerUniqId) {
		this.headerUniqId = headerUniqId;
	}

	public String getCancelAuthorizationUniqId() {
		return cancelAuthorizationUniqId;
	}

	public void setCancelAuthorizationUniqId(String cancelAuthorizationUniqId) {
		this.cancelAuthorizationUniqId = cancelAuthorizationUniqId;
	}

	public Header getHeader() {
		return header;
	}

	public void setHeader(Header header) {
		this.header = header;
	}

	public CancelAuthorization getCancelAuthAuthorization() {
		return authAuthorization;
	}

	public void setCancelAuthAuthorization(CancelAuthorization authAuthorization) {
		this.authAuthorization = authAuthorization;
	}

	@Override
	public String toString() {
		return "PriorCancel [id=" + id + ", uniq_id=" + uniq_id + ", createBy=" + createBy + ", createOn=" + createOn
				+ ", patientUhid=" + patientUhid + ", patientNo=" + patientNo + ", headerUniqId=" + headerUniqId
				+ ", cancelAuthorizationUniqId=" + cancelAuthorizationUniqId + ", header=" + header
				+ ", authAuthorization=" + authAuthorization + "]";
	}

	public PriorCancel(Long id, Long uniq_id, String createBy, Long createOn, String patientUhid, String patientNo,
			String headerUniqId, String cancelAuthorizationUniqId, Header header,
			CancelAuthorization authAuthorization) {
		super();
		this.id = id;
		this.uniq_id = uniq_id;
		this.createBy = createBy;
		this.createOn = createOn;
		this.patientUhid = patientUhid;
		this.patientNo = patientNo;
		this.headerUniqId = headerUniqId;
		this.cancelAuthorizationUniqId = cancelAuthorizationUniqId;
		this.header = header;
		this.authAuthorization = authAuthorization;
	}

}
