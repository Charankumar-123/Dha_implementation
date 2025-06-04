package com.ac.dha.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Diagnosis")

public class Diagnosis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "code", nullable = false)
    private String code;
    
    @ManyToOne
    @JoinColumn(name = "authorization_id", nullable = false)
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

	@Override
	public String toString() {
		return "Diagnosis [id=" + id + ", type=" + type + ", code=" + code + ", authorization=" + authorization + "]";
	}

	public Diagnosis(Long id, String type, String code, Authorization authorization) {
		super();
		this.id = id;
		this.type = type;
		this.code = code;
		this.authorization = authorization;
	}

    
}