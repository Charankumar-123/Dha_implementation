package com.ac.dha.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.ac.dha.dto.request.ActivityDTO;
import com.ac.dha.dto.request.AuthorizationDTO;
import com.ac.dha.dto.request.DiagnosisDTO;
import com.ac.dha.dto.request.EncounterDTO;
import com.ac.dha.dto.request.ErxRequestDTO;
import com.ac.dha.dto.request.HeaderDTO;
import com.ac.dha.dto.request.ObservationDTO;
import com.ac.dha.entties.Activity;
import com.ac.dha.entties.Authorization;
import com.ac.dha.entties.Diagnosis;
import com.ac.dha.entties.Encounter;
import com.ac.dha.entties.Header;
import com.ac.dha.entties.Observation;
import com.ac.dha.entties.PriorRequest;

@Component
public class ERXEntityMapper {

	public PriorRequest toPriorRequest(ErxRequestDTO dto) {
		PriorRequest priorRequest = new PriorRequest();
		priorRequest.setHeader(toHeader(dto.getHeader()));
		priorRequest.setAuthorization(toAuthorization(dto.getAuthorization()));
		return priorRequest;
	}

	private Header toHeader(HeaderDTO dto) {
		if (dto == null) {
			return null;
		}
		Header header = new Header();
		header.setSenderID(dto.getSenderID());
		header.setReceiverID(dto.getReceiverID());
		header.setTransactionDate(dto.getTransactionDate());
		header.setRecordCount(dto.getRecordCount());
		header.setDispositionFlag(dto.getDispositionFlag());
		return header;
	}

	private Authorization toAuthorization(AuthorizationDTO dto) {
		if (dto == null) {
			return null;
		}
		Authorization authorization = new Authorization();
		authorization.setType(dto.getType());
		authorization.setAuthorizationId(dto.getId());
		authorization.setMemberID(dto.getMemberID());
		authorization.setPayerID(dto.getPayerID());
		authorization.setEmiratesIDNumber(dto.getEmiratesIDNumber());
		authorization.setDateOrdered(dto.getDateOrdered());
		authorization.setEncounter(toEncounter(dto.getEncounter()));

		List<DiagnosisDTO> diagnosisDTO = dto.getDiagnoses();
		authorization.setDiagnoses(
				dto.getDiagnoses() != null ? diagnosisDTO.stream().map(this::toDiagnosis).collect(Collectors.toList())
						: new ArrayList<>());

		List<ActivityDTO> activityDTO = dto.getActivities();
		authorization.setActivities(
				dto.getActivities() != null ? activityDTO.stream().map(this::toActivity).collect(Collectors.toList())
						: new ArrayList<>());

		List<ObservationDTO> observationDTO = dto.getObservation();
		authorization.setObservations(dto.getObservation() != null
				? observationDTO.stream().map(this::toObservation).collect(Collectors.toList())
				: new ArrayList<>());
		return authorization;
	}

	private Encounter toEncounter(EncounterDTO dto) {
		if (dto == null) {
			return null;
		}
		Encounter encounter = new Encounter();
		encounter.setFacilityID(dto.getFacilityID());
		encounter.setType(dto.getType());
		return encounter;
	}

	private Diagnosis toDiagnosis(DiagnosisDTO dto) {
		if (dto == null) {
			return null;
		}
		Diagnosis diagnosis = new Diagnosis();
		diagnosis.setType(dto.getType());
		diagnosis.setCode(dto.getCode());
		return diagnosis;
	}

	private Activity toActivity(ActivityDTO dto) {
		if (dto == null) {
			return null;
		}
		Activity activity = new Activity();
		activity.setActivityId(dto.getId());
		activity.setStart(dto.getStart());
		activity.setType(dto.getType());
		activity.setCode(dto.getCode());
		activity.setQuantity(dto.getQuantity());
		activity.setNet(dto.getNet());
		activity.setClinician(dto.getClinician());
		List<ObservationDTO> observationDTO = dto.getObservations();
		activity.setObservations(
				observationDTO != null ? observationDTO.stream().map(this::toObservation).collect(Collectors.toList())
						: new ArrayList<Observation>());
		return activity;
	}

	private Observation toObservation(ObservationDTO dto) {
		if (dto == null) {
			return null;
		}
		Observation observation = new Observation();
		observation.setType(dto.getType());
		observation.setCode(dto.getCode());
		observation.setValue(dto.getValue());
		observation.setValueType(dto.getValueType());
		return observation;
	}
}