package com.ac.dha.entties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "diagnosis")
public class Diagnosis {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String type;

	private String code;

	@ManyToOne
	@JoinColumn(name = "authorization_id")
	private Authorization authorization;

	public Diagnosis() {
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

	public Authorization getAuthorization() {
		return authorization;
	}

	public void setAuthorization(Authorization authorization) {
		this.authorization = authorization;
	}

	public Diagnosis(Long id, String type, String code, Authorization authorization) {
		super();
		this.id = id;
		this.type = type;
		this.code = code;
		this.authorization = authorization;
	}

	@Override
	public String toString() {
		return "Diagnosis [id=" + id + ", type=" + type + ", code=" + code + ", authorization=" + authorization + "]";
	}

}
