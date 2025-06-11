package com.ac.dha.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Encounter")
public class Encounter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "authorization_id", nullable = false)
    private Authorization authorization;

    @Column(name = "facility_id", nullable = false)
    private String facilityID;

    @Column(name = "type", nullable = false)
    private String type;
    
    public Encounter() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Authorization getAuthorization() {
		return authorization;
	}

	public void setAuthorization(Authorization authorization) {
		this.authorization = authorization;
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

	public Encounter(Long id, Authorization authorization, String facilityID, String type) {
		super();
		this.id = id;
		this.authorization = authorization;
		this.facilityID = facilityID;
		this.type = type;
	}

	@Override
	public String toString() {
		return "Encounter [id=" + id + ", authorization=" + authorization + ", facilityID=" + facilityID + ", type="
				+ type + "]";
	}
    
    
}
