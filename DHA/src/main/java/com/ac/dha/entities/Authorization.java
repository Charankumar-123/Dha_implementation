package com.ac.dha.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "auth_name")
public class Authorization {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "type", nullable = false)
	private String type;

	@Column(name = "auth_id", nullable = false, unique = true)
	private String authId;

	@Column(name = "member_id", nullable = false)
	private String memberID;

	@Column(name = "payer_id", nullable = false)
	private String payerID;

	@Column(name = "emirates_id_number")
	private String emiratesIDNumber;

	@Column(name = "date_ordered", nullable = false)
	private String dateOrdered;

//	@OneToOne(mappedBy = "authorization_id", cascade = CascadeType.ALL)
//	private Encounter encounter;
//
//	@OneToMany(mappedBy = "authorization_id", cascade = CascadeType.ALL)
//	private List<Diagnosis> diagnoses;
//
//	@OneToMany(mappedBy = "authorization_id", cascade = CascadeType.ALL)
//	private List<Activity> activities;
//
//	@OneToMany(mappedBy = "authorization_id", cascade = CascadeType.ALL)
//	public List<Observation> observations;
	
	
	@OneToOne(mappedBy = "authorization", cascade = CascadeType.ALL)
    private Encounter encounter;
	
	
	
    @OneToMany(mappedBy = "authorization", cascade = CascadeType.ALL)
    private List<Diagnosis> diagnosis;

    @OneToMany(mappedBy = "authorization", cascade = CascadeType.ALL)
    private List<Activity> activities;
	
	public Authorization() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getMemberID() {
		return memberID;
	}

	public void setMemberID(String memberID) {
		this.memberID = memberID;
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

	public Encounter getEncounter() {
		return encounter;
	}

	public void setEncounter(Encounter encounter) {
		this.encounter = encounter;
	}

	public List<Diagnosis> getDiagnosis() {
		return diagnosis;
	}

	public void setDiagnosis(List<Diagnosis> diagnosis) {
		this.diagnosis = diagnosis;
	}

	public List<Activity> getActivities() {
		return activities;
	}

	public void setActivities(List<Activity> activities) {
		this.activities = activities;
	}

	@Override
	public String toString() {
		return "Authorization [id=" + id + ", type=" + type + ", authId=" + authId + ", memberID=" + memberID
				+ ", payerID=" + payerID + ", emiratesIDNumber=" + emiratesIDNumber + ", dateOrdered=" + dateOrdered
				+ ", encounter=" + encounter + ", diagnosis=" + diagnosis + ", activities=" + activities + "]";
	}

	public Authorization(Long id, String type, String authId, String memberID, String payerID, String emiratesIDNumber,
			String dateOrdered, Encounter encounter, List<Diagnosis> diagnosis, List<Activity> activities) {
		super();
		this.id = id;
		this.type = type;
		this.authId = authId;
		this.memberID = memberID;
		this.payerID = payerID;
		this.emiratesIDNumber = emiratesIDNumber;
		this.dateOrdered = dateOrdered;
		this.encounter = encounter;
		this.diagnosis = diagnosis;
		this.activities = activities;
	}

	
	

}