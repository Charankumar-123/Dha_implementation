package com.ac.dha.entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Activity")

public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "uniq_id")
	// @CustomUniqueCode(entityType = "clinicalEditHistory", prefix = "ECPR", numberWidth = 10)
	public String uniqId;

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
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "authorization_id", nullable = false)
    private Authorization authorization;

    @OneToMany(mappedBy = "activity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Observation> observations;

    public Activity() {
        // Initialize required fields with default values
        this.start = "";
        this.type = "";
        this.code = "";
        this.quantity = 0;
        this.net = 0.0;
        this.clinician = "";
        // Note: authorization must be set separately as it's a relationship field
    }

   

    // Helper method to add observation
    public void addObservation(Observation observation) {
        observation.setActivity(this);
        this.observations.add(observation);
    }

    // Helper method to remove observation
    public void removeObservation(Observation observation) {
        observation.setActivity(null);
        this.observations.remove(observation);
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



	public Activity(Long id, String uniqId, String start, String type, String code, int quantity, double net,
			String clinician, Authorization authorization, List<Observation> observations) {
		super();
		this.id = id;
		this.uniqId = uniqId;
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
		return "Activity [id=" + id + ", uniqId=" + uniqId + ", start=" + start + ", type=" + type + ", code=" + code
				+ ", quantity=" + quantity + ", net=" + net + ", clinician=" + clinician + ", authorization="
				+ authorization + ", observations=" + observations + "]";
	}
    
    
    
}


//package com.ac.dha.entities;
//
//import jakarta.persistence.*;
//import lombok.Getter;
//import lombok.Setter;
//
//import java.util.List;
//
//@Entity
//@Table(name = "Activity")
//public class Activity extends DhaBase  {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Column(name = "activity_id", nullable = false)
//    private String activityId;
//
//    @Column(name = "start_date", nullable = false)
//    private String start;
//
//    @Column(name = "type", nullable = false)
//    private String type;
//
//    @Column(name = "code", nullable = false)
//    private String code;
//
//    @Column(name = "quantity", nullable = false)
//    private int quantity;
//
//    @Column(name = "net", nullable = false)
//    private double net;
//
//    @Column(name = "clinician", nullable = false)
//    private String clinician;
//    
//    @ManyToOne
//    @JoinColumn(name = "authorization_id", nullable = false)
//    private Authorization authorization;
//
//    @OneToMany(mappedBy = "activity", cascade = CascadeType.ALL)
//    private List<Observation> observations;
//
//    
//    
//    public Activity() {}
//
//	public Long getId() {
//		return id;
//	}
//
//	public void setId(Long id) {
//		this.id = id;
//	}
//
//	public Authorization getAuthorization() {
//		return authorization;
//	}
//
//	public void setAuthorization(Authorization authorization) {
//		this.authorization = authorization;
//	}
//
//	public String getActivityId() {
//		return activityId;
//	}
//
//	public void setActivityId(String activityId) {
//		this.activityId = activityId;
//	}
//
//	public String getStart() {
//		return start;
//	}
//
//	public void setStart(String start) {
//		this.start = start;
//	}
//
//	public String getType() {
//		return type;
//	}
//
//	public void setType(String type) {
//		this.type = type;
//	}
//
//	public String getCode() {
//		return code;
//	}
//
//	public void setCode(String code) {
//		this.code = code;
//	}
//
//	public int getQuantity() {
//		return quantity;
//	}
//
//	public void setQuantity(int quantity) {
//		this.quantity = quantity;
//	}
//
//	public double getNet() {
//		return net;
//	}
//
//	public void setNet(double net) {
//		this.net = net;
//	}
//
//	public String getClinician() {
//		return clinician;
//	}
//
//	public void setClinician(String clinician) {
//		this.clinician = clinician;
//	}
//
//	public List<Observation> getObservations() {
//		return observations;
//	}
//
//	public void setObservations(List<Observation> observations) {
//		this.observations = observations;
//	}
//
//	@Override
//	public String toString() {
//		return "Activity [id=" + id + ", authorization=" + authorization + ", activityId=" + activityId + ", start="
//				+ start + ", type=" + type + ", code=" + code + ", quantity=" + quantity + ", net=" + net
//				+ ", clinician=" + clinician + ", observations=" + observations + "]";
//	}
//
//	public Activity(Long id, Authorization authorization, String activityId, String start, String type, String code,
//			int quantity, double net, String clinician, List<Observation> observations) {
//		super();
//		this.id = id;
//		this.authorization = authorization;
//		this.activityId = activityId;
//		this.start = start;
//		this.type = type;
//		this.code = code;
//		this.quantity = quantity;
//		this.net = net;
//		this.clinician = clinician;
//		this.observations = observations;
//	}
//
//	
//}
