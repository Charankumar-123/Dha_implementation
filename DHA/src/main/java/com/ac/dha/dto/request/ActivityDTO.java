package com.ac.dha.dto.request;

import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Builder
@XmlAccessorType(XmlAccessType.FIELD)
public class ActivityDTO {

	@XmlElement(name = "ID")
	private String id;

	@XmlElement(name = "Start")
	private String start;

	@XmlElement(name = "Type")
	private String type;

	@XmlElement(name = "Code")
	private String code;

	@XmlElement(name = "Quantity")
	private int quantity;

	@XmlElement(name = "Net")
	private double net;

	@XmlElement(name = "Clinician")
	private String clinician;

	@XmlElement(name = "Observation")
	private List<ObservationDTO> observations;
	
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
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

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getNet() {
		return net;
	}

	public void setNet(double net) {
		this.net = net;
	}

	public String getClinician() {
		return clinician;
	}

	public void setClinician(String clinician) {
		this.clinician = clinician;
	}

	public List<ObservationDTO> getObservations() {
		return observations;
	}

	public void setObservations(List<ObservationDTO> observations) {
		this.observations = observations;
	}

}
