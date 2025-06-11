package com.ac.dha.utils;

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
import com.ac.dha.entities.Activity;
import com.ac.dha.entities.Authorization;
import com.ac.dha.entities.Diagnosis;
import com.ac.dha.entities.Encounter;
import com.ac.dha.entities.Header;
import com.ac.dha.entities.Observation;
import com.ac.dha.entities.PriorRequest;

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
		authorization.setPatientMemberID(dto.getMemberID());
		authorization.setPayerID(dto.getPayerID());
		authorization.setEmiratesIDNumber(dto.getEmiratesIDNumber());
		authorization.setDateOrdered(dto.getDateOrdered());
		authorization.setEncounter(toEncounter(dto.getEncounter()));
//		  UUID.randomUUID();

//		List<Diagnosis> diagnosisDTO = dto.getDiagnoses();
//		authorization.setDiagnoses(diagnosisDTO != null
//				? diagnosisDTO.stream().map(this::toDiagnosis)
//						.peek(diagnosis -> diagnosis.setAuthorization(authorization)).collect(Collectors.toList())
//				: new ArrayList<>());

		if (dto.getDiagnoses() != null) {
			List<Diagnosis> diagnoses = dto.getDiagnoses().stream().map(this::toDiagnosis).collect(Collectors.toList());
			diagnoses.forEach(diagnosis -> diagnosis.setAuthorization(authorization));
			authorization.setDiagnoses(diagnoses);
		}

		if (dto.getActivities() != null) {
			List<Activity> activities = dto.getActivities().stream().map(this::toActivity).collect(Collectors.toList());
			activities.forEach(activity -> activity.setAuthorization(authorization));
			authorization.setActivities(activities);
		}

		if (dto.getObservation() != null) {
			List<Observation> observations = dto.getObservation().stream().map(this::toObservation)
					.collect(Collectors.toList());
			observations.forEach(observation -> observation.setAuthorizationId(authorization));
			authorization.setObservations(observations);
		}
//		List<Activity> activityDTO = dto.getActivities();
//		authorization
//				.setActivities(activityDTO != null
//						? activityDTO.stream().map(this::toActivity)
//								.peek(activity -> activity.setAuthorization(authorization)).collect(Collectors.toList())
//						: new ArrayList<>());

//		List<Observation> observationDTO = dto.getObservation();
//		authorization.setObservations(
//				observationDTO != null ? observationDTO.stream().map(dtoObs ->toObservation(dtoObs, authorization)).peek(observation -> observation.setAuthorizationId(authorization)).collect(Collectors.toList())
//						: new ArrayList<>());
//		
//		authorization.setObservations(
//				dto.getObservation() != null
//						? observationDTO.stream().map(dtoObs -> toObservation(dtoObs, authorization))
//								.collect(Collectors.toList())
//						: new ArrayList<>());

		return authorization;
	}

	private Encounter toEncounter(EncounterDTO dto) {
		if (dto == null) {
			return null;
		}
		Encounter encounter = new Encounter();
		encounter.setFacilityID(dto.getFacilityID());
		encounter.setType(dto.getType());
		encounter.setAuthorization(null);
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
		if (dto == null)
			return null;
		Activity activity = new Activity();
		activity.setStart(dto.getStart());
		activity.setType(dto.getType());
		activity.setCode(dto.getCode());
		activity.setQuantity(dto.getQuantity());
		activity.setNet(dto.getNet());
		activity.setClinician(dto.getClinician());

		if (dto.getObservations() != null) {
			List<Observation> observations = dto.getObservations().stream().map(this::toObservation)
					.collect(Collectors.toList());
			observations.forEach(observation -> observation.setActivity(activity));
			activity.setObservations(observations);
		}

		return activity;
	}

	private Observation toObservation(ObservationDTO dto) {
		if (dto == null)
			return null;
		Observation observation = new Observation();
		observation.setType(dto.getType());
		observation.setCode(dto.getCode());
		observation.setValue(dto.getValue());
		observation.setValueType(dto.getValueType());
		return observation;
	}
}