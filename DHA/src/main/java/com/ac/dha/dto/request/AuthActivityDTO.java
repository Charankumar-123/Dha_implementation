package com.ac.dha.dto.request;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class AuthActivityDTO {

	@XmlElement(name = "ID")
	private Long Id;

	@XmlElement(name = "Type")
	private String type;

	@XmlElement(name = "Code")
	private String code;

	@XmlElement(name = "Quantity")
	private String quantity;

	@XmlElement(name = "Net")
	private String net;

	@XmlElement(name = "PatientShare")
	private String patientShare;

	@XmlElement(name = "PaymentAmount")
	private String paymentAmount;

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
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

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getNet() {
		return net;
	}

	public void setNet(String net) {
		this.net = net;
	}

	public String getPatientShare() {
		return patientShare;
	}

	public void setPatientShare(String patientShare) {
		this.patientShare = patientShare;
	}

	public String getPaymentAmount() {
		return paymentAmount;
	}

	public void setPaymentAmount(String paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

	@Override
	public String toString() {
		return "AuthActivityDTO [Id=" + Id + ", type=" + type + ", code=" + code + ", quantity=" + quantity + ", net="
				+ net + ", patientShare=" + patientShare + ", paymentAmount=" + paymentAmount + "]";
	}

	public AuthActivityDTO(Long id, String type, String code, String quantity, String net, String patientShare,
			String paymentAmount) {
		super();
		Id = id;
		this.type = type;
		this.code = code;
		this.quantity = quantity;
		this.net = net;
		this.patientShare = patientShare;
		this.paymentAmount = paymentAmount;
	}

}
