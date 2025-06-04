// PriorRequest.java
package com.ac.dha.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "prior_request")
public class PriorRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "header_id", referencedColumnName = "id")
    private Header header;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "authorization_id", referencedColumnName = "id")
    private Authorization authorization;
    
    // getters and setters


    public PriorRequest() {
    }

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
        this.id = id;
        this.header = header;
        this.authorization = authorization;
    }
}