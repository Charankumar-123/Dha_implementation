package com.ac.dha.entities;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class PriorRequest {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne
	private Header header;

	@OneToOne
	private Authorization authorization;
	
	public PriorRequest() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Header getHeader() {
		return header;
	}

	public void setHeader(Header header) {
		this.header = header;
	}

	public Authorization getAuthorization() {
		return authorization;
	}

	public void setAuthorization(Authorization authorization) {
		this.authorization = authorization;
	}

	@Override
	public String toString() {
		return "PriorRequest [id=" + id + ", header=" + header + ", authorization=" + authorization + "]";
	}

	public PriorRequest(Long id, Header header, Authorization authorization) {
		super();
		this.id = id;
		this.header = header;
		this.authorization = authorization;
	}

	
	
}
