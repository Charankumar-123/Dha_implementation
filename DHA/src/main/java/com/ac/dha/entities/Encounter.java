package com.ac.dha.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbl_eclaim_encounter")
public class Encounter {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "uniq_id")
	// @CustomUniqueCode(entityType = "clinicalEditHistory", prefix = "ECPR",
	// numberWidth = 10)
	private String uniqId;

	@Column(name = "facility_id")
	private String facilityID;

	private String type;

	@OneToOne(mappedBy = "encounter")
	private Authorization authorization;

	public Encounter() {
	}

	public Encounter(Long id, String uniqId, String facilityID, String type, Authorization authorization) {
		this.id = id;
		this.uniqId = uniqId;
		this.facilityID = facilityID;
		this.type = type;
		this.authorization = authorization;
	}

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

	public String getFacilityID() {
		return facilityID;
	}

	public void setFacilityID(String facilityID) {
		this.facilityID = facilityID;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Authorization getAuthorization() {
		return authorization;
	}

	public void setAuthorization(Authorization authorization) {
		this.authorization = authorization;
	}

	@Override
	public String toString() {
		return "Encounter [id=" + id + ", uniqId=" + uniqId + ", facilityID=" + facilityID + ", type=" + type
				+ ", authorization=" + authorization + "]";
	}
}