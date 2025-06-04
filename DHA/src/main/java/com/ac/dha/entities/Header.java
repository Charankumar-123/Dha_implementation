package com.ac.dha.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "header")

public class Header {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "sender_id", nullable = false)
    private String senderID;

    @Column(name = "receiver_id", nullable = false)
    private String receiverID;

    @Column(name = "transaction_date", nullable = false)
    private String transactionDate;

    @Column(name = "record_count", nullable = false)
    private int recordCount;

    @Column(name = "disposition_flag", nullable = false)
    private String dispositionFlag;
    
    public Header() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSenderID() {
		return senderID;
	}

	public void setSenderID(String senderID) {
		this.senderID = senderID;
	}

	public String getReceiverID() {
		return receiverID;
	}

	public void setReceiverID(String receiverID) {
		this.receiverID = receiverID;
	}

	public String getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}

	public int getRecordCount() {
		return recordCount;
	}

	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}

	public String getDispositionFlag() {
		return dispositionFlag;
	}

	public void setDispositionFlag(String dispositionFlag) {
		this.dispositionFlag = dispositionFlag;
	}

	public Header(Long id, String senderID, String receiverID, String transactionDate, int recordCount,
			String dispositionFlag) {
		super();
		this.id = id;
		this.senderID = senderID;
		this.receiverID = receiverID;
		this.transactionDate = transactionDate;
		this.recordCount = recordCount;
		this.dispositionFlag = dispositionFlag;
	}

	@Override
	public String toString() {
		return "Header [id=" + id + ", senderID=" + senderID + ", receiverID=" + receiverID + ", transactionDate="
				+ transactionDate + ", recordCount=" + recordCount + ", dispositionFlag=" + dispositionFlag + "]";
	}

//    @OneToOne(mappedBy = "header", cascade = CascadeType.ALL)
//    private Authorization authorization;
    
    
}