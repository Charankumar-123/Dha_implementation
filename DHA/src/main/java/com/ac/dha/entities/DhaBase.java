//package com.ac.dha.entities;
//
//
//import jakarta.persistence.MappedSuperclass;
//
//
//@MappedSuperclass
//public class DhaBase {
//    private String uniqId;          
//    private String createBy;        
//    private Long createOn;          
//    private String patientUhid;     
//    private String patientNo;       
//    private String headerUniqId;    
//    private String authorizationUniqId;
//	public String getUniqId() {
//		return uniqId;
//	}
//	public void setUniqId(String uniqId) {
//		this.uniqId = uniqId;
//	}
//	public String getCreateBy() {
//		return createBy;
//	}
//	public void setCreateBy(String createBy) {
//		this.createBy = createBy;
//	}
//	public Long getCreateOn() {
//		return createOn;
//	}
//	public void setCreateOn(Long createOn) {
//		this.createOn = createOn;
//	}
//	public String getPatientUhid() {
//		return patientUhid;
//	}
//	public void setPatientUhid(String patientUhid) {
//		this.patientUhid = patientUhid;
//	}
//	public String getPatientNo() {
//		return patientNo;
//	}
//	public void setPatientNo(String patientNo) {
//		this.patientNo = patientNo;
//	}
//	public String getHeaderUniqId() {
//		return headerUniqId;
//	}
//	public void setHeaderUniqId(String headerUniqId) {
//		this.headerUniqId = headerUniqId;
//	}
//	public String getAuthorizationUniqId() {
//		return authorizationUniqId;
//	}
//	public void setAuthorizationUniqId(String authorizationUniqId) {
//		this.authorizationUniqId = authorizationUniqId;
//	}
//	public DhaBase(String uniqId, String createBy, Long createOn, String patientUhid, String patientNo,
//			String headerUniqId, String authorizationUniqId) {
//		super();
//		this.uniqId = uniqId;
//		this.createBy = createBy;
//		this.createOn = createOn;
//		this.patientUhid = patientUhid;
//		this.patientNo = patientNo;
//		this.headerUniqId = headerUniqId;
//		this.authorizationUniqId = authorizationUniqId;
//	}
//	@Override
//	public String toString() {
//		return "DhaBase [uniqId=" + uniqId + ", createBy=" + createBy + ", createOn=" + createOn + ", patientUhid="
//				+ patientUhid + ", patientNo=" + patientNo + ", headerUniqId=" + headerUniqId + ", authorizationUniqId="
//				+ authorizationUniqId + "]";
//	}
//	public DhaBase() {
//		super();
//	}
//	
//	
//    
//    
//    
//  
//}