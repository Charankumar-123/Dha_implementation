package com.ac.dha.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Observation")
public class Observation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "activity_id", nullable = false)
    private Activity activity;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "code", nullable = false)
    private String code;

    @Column(name = "value")
    private String value;

    @Column(name = "value_type", nullable = false)
    private String valueType;
    
    @ManyToOne
//    @
    public Authorization authorization;
    
    public Observation() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Activity getActivity() {
		return activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
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

	public Authorization getAuthorization() {
		return authorization;
	}

	public void setAuthorization(Authorization authorization) {
		this.authorization = authorization;
	}

	@Override
	public String toString() {
		return "Observation [id=" + id + ", activity=" + activity + ", type=" + type + ", code=" + code + ", value="
				+ value + ", valueType=" + valueType + ", authorization=" + authorization + "]";
	}

	public Observation(Long id, Activity activity, String type, String code, String value, String valueType,
			Authorization authorization) {
		super();
		this.id = id;
		this.activity = activity;
		this.type = type;
		this.code = code;
		this.value = value;
		this.valueType = valueType;
		this.authorization = authorization;
	}

	
}