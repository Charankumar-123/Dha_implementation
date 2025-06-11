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
@Table(name = "tbl_eclaim_observation")
public class Observation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "uniq_id")
	// @CustomUniqueCode(entityType = "clinicalEditHistory", prefix = "ECPR",
	// numberWidth = 10)
	private String uniqId;

	private String type;

	private String code;

	private String value;

	@Column(name = "value_type")
	private String valueType;

	@ManyToOne
	@JoinColumn(name = "activity_id")
	private Activity activity;

	@ManyToOne
	@JoinColumn(name = "authorization_id")
	private Authorization authorizationId;

	public Observation() {
	}

	public Observation(Long id, String uniqId, String type, String code, String value, String valueType,
			Activity activity, Authorization authorizationId) {
		this.id = id;
		this.uniqId = uniqId;
		this.type = type;
		this.code = code;
		this.value = value;
		this.valueType = valueType;
		this.activity = activity;
		this.authorizationId = authorizationId;
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

	public Authorization getAuthorizationId() {
		return authorizationId;
	}

	public void setAuthorizationId(Authorization authorizationId) {
		this.authorizationId = authorizationId;
	}

	@Override
	public String toString() {
		return "Observation [id=" + id + ", uniqId=" + uniqId + ", type=" + type + ", code=" + code + ", value=" + value
				+ ", valueType=" + valueType + ", activity=" + activity + ", authorizationId=" + authorizationId + "]";
	}
}