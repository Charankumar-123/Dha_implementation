package com.ac.dha.entties;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "auth_name")
public class Authorization {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String type;

	@Column(name = "authorization_id")
	private String authorizationId;

	@Column(name = "member_id")
	private String memberID;

	@Column(name = "payer_id")
	private String payerID;

	@Column(name = "emirates_id_number")
	private String emiratesIDNumber;

	@Column(name = "date_ordered")
	private String dateOrdered;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "encounter_id")
	private Encounter encounter;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "authorization_id")
	private List<Diagnosis> diagnoses;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "authorization_id")
	private List<Activity> activities;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "authorization_id")
    private List<Observation> observations;
	
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

	public String getAuthorizationId() {
		return authorizationId;
	}

	public void setAuthorizationId(String authorizationId) {
		this.authorizationId = authorizationId;
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

	public List<Diagnosis> getDiagnoses() {
		return diagnoses;
	}

	public void setDiagnoses(List<Diagnosis> diagnoses) {
		this.diagnoses = diagnoses;
	}

	public List<Activity> getActivities() {
		return activities;
	}

	public void setActivities(List<Activity> activities) {
		this.activities = activities;
	}

	public List<Observation> getObservations() {
		return observations;
	}

	public void setObservations(List<Observation> observations) {
		this.observations = observations;
	}

	@Override
	public String toString() {
		return "Authorization [id=" + id + ", type=" + type + ", authorizationId=" + authorizationId + ", memberID="
				+ memberID + ", payerID=" + payerID + ", emiratesIDNumber=" + emiratesIDNumber + ", dateOrdered="
				+ dateOrdered + ", encounter=" + encounter + ", diagnoses=" + diagnoses + ", activities=" + activities
				+ ", observations=" + observations + "]";
	}

	public Authorization(Long id, String type, String authorizationId, String memberID, String payerID,
			String emiratesIDNumber, String dateOrdered, Encounter encounter, List<Diagnosis> diagnoses,
			List<Activity> activities, List<Observation> observations) {
		super();
		this.id = id;
		this.type = type;
		this.authorizationId = authorizationId;
		this.memberID = memberID;
		this.payerID = payerID;
		this.emiratesIDNumber = emiratesIDNumber;
		this.dateOrdered = dateOrdered;
		this.encounter = encounter;
		this.diagnoses = diagnoses;
		this.activities = activities;
		this.observations = observations;
	}
	
	
}
