package com.ac.dha.dto.request;

import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class AuthAuthorizationDTO {

	@XmlElement(name = "Result")
	private String result;

	@XmlElement(name = "ID")
	private String AuthID;

	@XmlElement(name = "IDPayer")
	private String IdPayer;

	@XmlElement(name = "Start")
	private String start;

	@XmlElement(name = "End")
	private String end;

	@XmlElement(name = "Comments")
	private String comments;

	@XmlElement(name = "Activity")
	private List<AuthActivityDTO> activity;

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getAuthID() {
		return AuthID;
	}

	public void setAuthID(String authID) {
		AuthID = authID;
	}

	public String getIdPayer() {
		return IdPayer;
	}

	public void setIdPayer(String idPayer) {
		IdPayer = idPayer;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public List<AuthActivityDTO> getActivity() {
		return activity;
	}

	public void setActivity(List<AuthActivityDTO> activity) {
		this.activity = activity;
	}

	@Override
	public String toString() {
		return "AuthAuthorizationDTO [result=" + result + ", AuthID=" + AuthID + ", IdPayer=" + IdPayer + ", start="
				+ start + ", end=" + end + ", comments=" + comments + ", activity=" + activity + "]";
	}

	public AuthAuthorizationDTO(String result, String authID, String idPayer, String start, String end, String comments,
			List<AuthActivityDTO> activity) {
		super();
		this.result = result;
		AuthID = authID;
		IdPayer = idPayer;
		this.start = start;
		this.end = end;
		this.comments = comments;
		this.activity = activity;
	}

}
