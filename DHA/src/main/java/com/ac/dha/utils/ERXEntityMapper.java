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
		authorization.setAuthId(dto.getId());
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



//package com.ac.dha.utils;
//
//import java.time.LocalDateTime;
//import java.util.stream.Collectors;
//
//import com.ac.dha.dto.request.ActivityDTO;
//import com.ac.dha.dto.request.AuthorizationDTO;
//import com.ac.dha.dto.request.DiagnosisDTO;
//import com.ac.dha.dto.request.EncounterDTO;
//import com.ac.dha.dto.request.ErxRequestDTO;
//import com.ac.dha.dto.request.HeaderDTO;
//import com.ac.dha.dto.request.ObservationDTO;
//import com.ac.dha.entities.Activity;
//import com.ac.dha.entities.Authorization;
//import com.ac.dha.entities.Diagnosis;
//import com.ac.dha.entities.Encounter;
//import com.ac.dha.entities.Header;
//import com.ac.dha.entities.Observation;
//import com.ac.dha.entities.TransactionLog;
//
//public class DtoToEntityConverter {
//    public static Header toHeaderEntity(ErxRequestDTO dto) {
//        Header header = new Header();
//        header.setSenderID(dto.getHeader().getSenderID());
//        header.setReceiverID(dto.getHeader().getReceiverID());
//        header.setTransactionDate(dto.getHeader().getTransactionDate());
//        header.setRecordCount(dto.getHeader().getRecordCount());
//        header.setDispositionFlag(dto.getHeader().getDispositionFlag());
//
//        Authorization authorization = toAuthorizationEntity(dto.getAuthorization(), header);
//        header.setAuthorization(authorization);
//        return header;
//    }
//
//    private static Authorization toAuthorizationEntity(AuthorizationDTO dto, Header header)
// {
//        Authorization entity = new Authorization();
//        entity.setHeader(header);
//        entity.setType(dto.getType());
////        entity.setAuthId(dto.getAuthId());
//        entity.setMemberID(dto.getMemberID());
//        entity.setPayerID(dto.getPayerID());
//        entity.setEmiratesIDNumber(dto.getEmiratesIDNumber());
//        entity.setDateOrdered(dto.getDateOrdered());
//
//        Encounter encounter = toEncounterEntity(dto.getEncounter(), entity);
//        entity.setEncounter(encounter);
//
//        entity.setDiagnoses(dto.getDiagnoses().stream()
//                .map(d -> toDiagnosisEntity(d, entity))
//                .collect(Collectors.toList()));
//
//        entity.setActivities(dto.getActivities().stream()
//                .map(a -> toActivityEntity(a, entity))
//                .collect(Collectors.toList()));
//
//        return entity;
//    }
//
//    private static Encounter toEncounterEntity(EncounterDTO dto, Authorization authorization) {
//        Encounter entity = new Encounter();
//        entity.setAuthorization(authorization);
//        entity.setFacilityID(dto.getFacilityID());
//        entity.setType(dto.getType());
//        return entity;
//    }
//
//    private static Diagnosis toDiagnosisEntity(DiagnosisDTO dto, Authorization authorization) {
//        Diagnosis entity = new Diagnosis();
//        entity.setAuthorization(authorization);
//        entity.setType(dto.getType());
//        entity.setCode(dto.getCode());
//        return entity;
//    }
//
//    private static Activity toActivityEntity(ActivityDTO dto, Authorization authorization) {
//        Activity entity = new Activity();
//        entity.setAuthorization(authorization);
//        entity.setActivityId(dto.getActivityId());
//        entity.setStartDate(dto.getStartDate());
//        entity.setType(dto.getType());
//        entity.setCode(dto.getCode());
//        entity.setQuantity(dto.getQuantity());
//        entity.setNet(dto.getNet());
//        entity.setClinician(dto.getClinician());
//
//        entity.setObservations(dto.getObservations().stream()
//                .map(o -> toObservationEntity(o, entity))
//                .collect(Collectors.toList()));
//
//        return entity;
//    }
//
//    private static Observation toObservationEntity(ObservationDTO dto, Activity activity) {
//        Observation entity = new Observation();
//        entity.setActivity(activity);
//        entity.setType(dto.getType());
//        entity.setCode(dto.getCode());
//        entity.setValue(dto.getValue());
//        entity.setValueType(dto.getValueType());
//        return entity;
//    }
//
//    public static TransactionLog toTransactionLogEntity(String methodName, String fileName, String fileContent,
//                                                        String responseBody, int statusCode) {
//        TransactionLog log = new TransactionLog();
//        log.setMethodName(methodName);
//        log.setFileName(fileName);
//        log.setFileContent(fileContent);
//        log.setErrorMessage(responseBody != null && statusCode >= 400 ? responseBody : null);
//        log.setReturnValue(statusCode >= 200 && statusCode < 300 ? 0 : -1);
//        log.setCreatedAt(LocalDateTime.now().toString());
//        return log;
//    }
//}