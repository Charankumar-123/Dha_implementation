//package com.ac.dha.entities;
//
//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.Table;
//
//
//@Entity
//@Table(name = "getnewtransactions_response")
//public class GetNewTransactionsResponse {
//	@Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Column(name = "xml_transactions")
//    private String xmlTransactions;
//
//   
//    @Column(name = "error_message")
//    private String errorMessage;
//
//
//	public Long getId() {
//		return id;
//	}
//
//
//	public void setId(Long id) {
//		this.id = id;
//	}
//
//
//	public String getXmlTransactions() {
//		return xmlTransactions;
//	}
//
//
//	public void setXmlTransactions(String xmlTransactions) {
//		this.xmlTransactions = xmlTransactions;
//	}
//
//
//	public String getErrorMessage() {
//		return errorMessage;
//	}
//
//
//	public void setErrorMessage(String errorMessage) {
//		this.errorMessage = errorMessage;
//	}
//
//
//	public GetNewTransactionsResponse(Long id, String xmlTransactions, String errorMessage) {
//		super();
//		this.id = id;
//		this.xmlTransactions = xmlTransactions;
//		this.errorMessage = errorMessage;
//	}
//
//
//	public GetNewTransactionsResponse() {
//		super();
//	}
//
//
//	@Override
//	public String toString() {
//		return "GetNewTransactionsResponse [id=" + id + ", xmlTransactions=" + xmlTransactions + ", errorMessage="
//				+ errorMessage + "]";
//	}
//    
//    
//
//}
