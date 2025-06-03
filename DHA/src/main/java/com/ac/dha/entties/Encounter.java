package com.ac.dha.entties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "encounter")
public class Encounter {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "facility_id")
	private String facilityID;

	private String type;

	@OneToOne(mappedBy = "encounter")
	private Authorization authorization;

	public Encounter() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Encounter(Long id, String facilityID, String type, Authorization authorization) {
		super();
		this.id = id;
		this.facilityID = facilityID;
		this.type = type;
		this.authorization = authorization;
	}

	@Override
	public String toString() {
		return "Encounter [id=" + id + ", facilityID=" + facilityID + ", type=" + type + ", authorization="
				+ authorization + "]";
	}

}
