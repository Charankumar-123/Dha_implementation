package com.ac.dha.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "Activity")
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "activity_id", nullable = false)
    private String activityId;

    @Column(name = "start_date", nullable = false)
    private String start;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "code", nullable = false)
    private String code;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Column(name = "net", nullable = false)
    private double net;

    @Column(name = "clinician", nullable = false)
    private String clinician;
    
    @ManyToOne
    @JoinColumn(name = "authorization_id", nullable = false)
    private Authorization authorization;

    @OneToMany(mappedBy = "activity", cascade = CascadeType.ALL)
    private List<Observation> observations;

    
    
    public Activity() {}

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

	public List<Observation> getObservations() {
		return observations;
	}

	public void setObservations(List<Observation> observations) {
		this.observations = observations;
	}

	@Override
	public String toString() {
		return "Activity [id=" + id + ", authorization=" + authorization + ", activityId=" + activityId + ", start="
				+ start + ", type=" + type + ", code=" + code + ", quantity=" + quantity + ", net=" + net
				+ ", clinician=" + clinician + ", observations=" + observations + "]";
	}

	public Activity(Long id, Authorization authorization, String activityId, String start, String type, String code,
			int quantity, double net, String clinician, List<Observation> observations) {
		super();
		this.id = id;
		this.authorization = authorization;
		this.activityId = activityId;
		this.start = start;
		this.type = type;
		this.code = code;
		this.quantity = quantity;
		this.net = net;
		this.clinician = clinician;
		this.observations = observations;
	}

	
}
