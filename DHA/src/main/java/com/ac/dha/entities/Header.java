package com.ac.dha.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbl_eclaim_header")
public class Header {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "uniq_id")
	// @CustomUniqueCode(entityType = "clinicalEditHistory", prefix = "ECPR",
	// numberWidth = 10)
	private String uniqId;

	@Column(name = "create_by")
	private String createBy;

	@Column(name = "create_on")
	// @EpochTime
	private Long createOn;

	@Column(name = "sender_id")
	private String senderID;

	@Column(name = "receiver_id")
	private String receiverID;

	@Column(name = "transaction_date")
	private String transactionDate;

	@Column(name = "record_count")
	private int recordCount;

	@Column(name = "disposition_flag")
	private String dispositionFlag;

	public Header() {
	}

	public Header(Long id, String uniqId, String createBy, Long createOn, String senderID, String receiverID,
			String transactionDate, int recordCount, String dispositionFlag) {
		this.id = id;
		this.uniqId = uniqId;
		this.createBy = createBy;
		this.createOn = createOn;
		this.senderID = senderID;
		this.receiverID = receiverID;
		this.transactionDate = transactionDate;
		this.recordCount = recordCount;
		this.dispositionFlag = dispositionFlag;
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

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Long getCreateOn() {
		return createOn;
	}

	public void setCreateOn(Long createOn) {
		this.createOn = createOn;
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

	@Override
	public String toString() {
		return "Header [id=" + id + ", uniqId=" + uniqId + ", createBy=" + createBy + ", createOn=" + createOn
				+ ", senderID=" + senderID + ", receiverID=" + receiverID + ", transactionDate=" + transactionDate
				+ ", recordCount=" + recordCount + ", dispositionFlag=" + dispositionFlag + "]";
	}
}