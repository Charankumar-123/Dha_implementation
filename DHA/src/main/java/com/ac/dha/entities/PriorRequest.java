// PriorRequest.java
package com.ac.dha.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "prior_request")
public class PriorRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "uniq_id")
	// @CustomUniqueCode(entityType = "clinicalEditHistory", prefix = "ECPR", numberWidth = 10)
	public String uniqId;

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

	@Column(name = "authorization_uniq_id")
	private String authorizationUniqId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "header_id", referencedColumnName = "id")
    private Header header;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "authorization_id", referencedColumnName = "id")
    private Authorization authorization;
    
 // getters and setters

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUniqId() {
		return uniqId;
	}

	public void setUniqId(String uniqId) {
		this.uniqId = uniqId;
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

	public String getAuthorizationUniqId() {
		return authorizationUniqId;
	}

	public void setAuthorizationUniqId(String authorizationUniqId) {
		this.authorizationUniqId = authorizationUniqId;
	}

	public Header getHeader() {
		return header;
	}

	public void setHeader(Header header) {
		this.header = header;
	}

	public Authorization getAuthorization() {
		return authorization;
	}

	public void setAuthorization(Authorization authorization) {
		this.authorization = authorization;
	}

	public PriorRequest(Long id, String uniqId, String createBy, Long createOn, String patientUhid, String patientNo,
			String headerUniqId, String authorizationUniqId, Header header, Authorization authorization) {
		super();
		this.id = id;
		this.uniqId = uniqId;
		this.createBy = createBy;
		this.createOn = createOn;
		this.patientUhid = patientUhid;
		this.patientNo = patientNo;
		this.headerUniqId = headerUniqId;
		this.authorizationUniqId = authorizationUniqId;
		this.header = header;
		this.authorization = authorization;
	}

	public PriorRequest() {
		super();
	}

	@Override
	public String toString() {
		return "PriorRequest [id=" + id + ", uniqId=" + uniqId + ", createBy=" + createBy + ", createOn=" + createOn
				+ ", patientUhid=" + patientUhid + ", patientNo=" + patientNo + ", headerUniqId=" + headerUniqId
				+ ", authorizationUniqId=" + authorizationUniqId + ", header=" + header + ", authorization="
				+ authorization + "]";
	}
    
	
   
    
    
    
    
    

}