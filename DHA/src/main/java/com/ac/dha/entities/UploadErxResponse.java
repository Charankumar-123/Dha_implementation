package com.ac.dha.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class UploadErxResponse {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
    private int eRxReferenceNo;
    private String errorMessage;
    private byte[] errorReport;

    // Default constructor
    public UploadErxResponse() {
    }

    // Parameterized constructor
    public UploadErxResponse(int eRxReferenceNo, String errorMessage, byte[] errorReport) {
        this.eRxReferenceNo = eRxReferenceNo;
        this.errorMessage = errorMessage;
        this.errorReport = errorReport;
    }

    // Getters and Setters
    public int getERxReferenceNo() {
        return eRxReferenceNo;
    }

    public void setERxReferenceNo(int eRxReferenceNo) {
        this.eRxReferenceNo = eRxReferenceNo;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public byte[] getErrorReport() {
        return errorReport;
    }

    public void setErrorReport(byte[] errorReport) {
        this.errorReport = errorReport;
    }
}