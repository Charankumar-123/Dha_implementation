package com.ac.dha.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Observation")
public class Observation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	

	@Column(name = "type", nullable = false)
	private String type;

	@Column(name = "code", nullable = false)
	private String code;

	@Column(name = "value")
	private String value;

	@Column(name = "value_type", nullable = false)
	private String valueType;
	
//	@ManyToOne
//	@JoinColumn(name = "activity_id", nullable = false)
//	private Observation observation;
	
	@ManyToOne
    @JoinColumn(name = "activity_id", nullable = false)
    private Activity activity;



	public Observation() {
	}



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



	public String getCode() {
		return code;
	}



	public void setCode(String code) {
		this.code = code;
	}



	public String getValue() {
		return value;
	}



	public void setValue(String value) {
		this.value = value;
	}



	public String getValueType() {
		return valueType;
	}



	public void setValueType(String valueType) {
		this.valueType = valueType;
	}



	public Activity getActivity() {
		return activity;
	}



	public void setActivity(Activity activity) {
		this.activity = activity;
	}



	public Observation(Long id, String type, String code, String value, String valueType, Activity activity) {
		super();
		this.id = id;
		this.type = type;
		this.code = code;
		this.value = value;
		this.valueType = valueType;
		this.activity = activity;
	}



	@Override
	public String toString() {
		return "Observation [id=" + id + ", type=" + type + ", code=" + code + ", value=" + value + ", valueType="
				+ valueType + ", activity=" + activity + "]";
	}

	

}