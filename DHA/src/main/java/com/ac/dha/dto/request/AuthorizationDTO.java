package com.ac.dha.dto.request;

import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;

//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Builder
@XmlAccessorType(XmlAccessType.FIELD)
public class AuthorizationDTO {

	@XmlElement(name = "Type")
	private String type;

	@XmlElement(name = "ID")
	private String id;

	@XmlElement(name = "MemberID")
	private String memberID;

	@XmlElement(name = "PayerID")
	private String payerID;

	@XmlElement(name = "EmiratesIDNumber")
	private String emiratesIDNumber;

	@XmlElement(name = "DateOrdered")
	private String dateOrdered;

	@XmlElement(name = "Encounter")
	private EncounterDTO encounter;

	@XmlElement(name = "Diagnosis")
	private List<DiagnosisDTO> diagnoses;

	@XmlElement(name = "Activity")
	private List<ActivityDTO> activities;

	@XmlElement(name = "Observation")
	private List<ObservationDTO> observation;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public EncounterDTO getEncounter() {
		return encounter;
	}

	public void setEncounter(EncounterDTO encounter) {
		this.encounter = encounter;
	}

	public List<DiagnosisDTO> getDiagnoses() {
		return diagnoses;
	}

	public void setDiagnoses(List<DiagnosisDTO> diagnoses) {
		this.diagnoses = diagnoses;
	}

	public List<ActivityDTO> getActivities() {
		return activities;
	}

	public void setActivities(List<ActivityDTO> activities) {
		this.activities = activities;
	}

	public List<ObservationDTO> getObservation() {
		return observation;
	}

	public void setObservation(List<ObservationDTO> observation) {
		this.observation = observation;
	}

	public AuthorizationDTO(String type, String id, String memberID, String payerID, String emiratesIDNumber,
			String dateOrdered, EncounterDTO encounter, List<DiagnosisDTO> diagnoses, List<ActivityDTO> activities,
			List<ObservationDTO> observation) {
		super();
		this.type = type;
		this.id = id;
		this.memberID = memberID;
		this.payerID = payerID;
		this.emiratesIDNumber = emiratesIDNumber;
		this.dateOrdered = dateOrdered;
		this.encounter = encounter;
		this.diagnoses = diagnoses;
		this.activities = activities;
		this.observation = observation;
	}

	@Override
	public String toString() {
		return "AuthorizationDTO [type=" + type + ", id=" + id + ", memberID=" + memberID + ", payerID=" + payerID
				+ ", emiratesIDNumber=" + emiratesIDNumber + ", dateOrdered=" + dateOrdered + ", encounter=" + encounter
				+ ", diagnoses=" + diagnoses + ", activities=" + activities + ", observation=" + observation + "]";
	}

	
}
