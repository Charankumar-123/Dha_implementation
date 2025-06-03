package com.ac.dha.entties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "prior_request")
public class PriorRequest {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "header_id")
	private Header header;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "authorization_id")
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
