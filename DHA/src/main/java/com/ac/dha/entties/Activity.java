package com.ac.dha.entties;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "activity")
public class Activity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "activity_id")
	private String activityId;

	private String start;

	private String type;

	private String code;

	private int quantity;

	private double net;

	private String clinician;
	
	public Activity() {}

	@ManyToOne
	@JoinColumn(name = "authorization_id")
	private Authorization authorization;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "activity_id", referencedColumnName = "id")
	private List<Observation> observations;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getActivityId() {
		return activityId;
	}

	public void setActivityId(String activityId) {
		this.activityId = activityId;
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

	public Authorization getAuthorization() {
		return authorization;
	}

	public void setAuthorization(Authorization authorization) {
		this.authorization = authorization;
	}

	public List<Observation> getObservations() {
		return observations;
	}

	public void setObservations(List<Observation> observations) {
		this.observations = observations;
	}

	public Activity(Long id, String activityId, String start, String type, String code, int quantity, double net,
			String clinician, Authorization authorization, List<Observation> observations) {
		super();
		this.id = id;
		this.activityId = activityId;
		this.start = start;
		this.type = type;
		this.code = code;
		this.quantity = quantity;
		this.net = net;
		this.clinician = clinician;
		this.authorization = authorization;
		this.observations = observations;
	}

	@Override
	public String toString() {
		return "Activity [id=" + id + ", activityId=" + activityId + ", start=" + start + ", type=" + type + ", code="
				+ code + ", quantity=" + quantity + ", net=" + net + ", clinician=" + clinician + ", authorization="
				+ authorization + ", observations=" + observations + "]";
	}

}
