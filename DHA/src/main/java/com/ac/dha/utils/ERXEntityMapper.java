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
        authorization.setEncounter(toEncounter(dto.getEncounter(), authorization));

        List<DiagnosisDTO> diagnosisDTO = dto.getDiagnoses();
        authorization.setDiagnosis(
                dto.getDiagnoses() != null ? diagnosisDTO.stream().map(this::toDiagnosis).collect(Collectors.toList())
                        : new ArrayList<>());

        List<ActivityDTO> activityDTO = dto.getActivities();
        // Map activities and set the authorization reference
        List<Activity> activities = dto.getActivities() != null
                ? activityDTO.stream()
                        .map(dtoItem -> toActivity(dtoItem, authorization)).collect(Collectors.toList())
                : new ArrayList<>();
        authorization.setActivities(activities);

        List<ObservationDTO> observationDTOs = dto.getObservation();
        if (observationDTOs != null) {
            // Assuming each authorization has at least one activity
            if (!authorization.getActivities().isEmpty()) {
                Activity activity = authorization.getActivities().get(0); // get first activity
                activity.setObservations(
                        observationDTOs.stream()
                                .map(dtoItem -> toObservation(dtoItem, activity)) // Pass activity to toObservation
                                .collect(Collectors.toList())
                );
            } else {
                // Handle case where there are no activities
                Activity newActivity = new Activity();
                newActivity.setAuthorization(authorization); // Set authorization for new activity
                newActivity.setObservations(
                        observationDTOs.stream()
                                .map(dtoItem -> toObservation(dtoItem, newActivity)) // Pass newActivity
                                .collect(Collectors.toList())
                );
                authorization.getActivities().add(newActivity);
            }
        }
        return authorization;
    }

    private Encounter toEncounter(EncounterDTO dto, Authorization authorization) {
        if (dto == null) {
            return null;
        }
        Encounter encounter = new Encounter();
        encounter.setFacilityID(dto.getFacilityID());
        encounter.setType(dto.getType());
        encounter.setAuthorization(authorization); // Set the authorization reference
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

    private Activity toActivity(ActivityDTO dto, Authorization authorization) {
        if (dto == null) {
            return null;
        }
        Activity activity = new Activity();
        activity.setUniqId("ACT123");
        activity.setStart(dto.getStart());
        activity.setType(dto.getType());
        activity.setCode(dto.getCode());
        activity.setQuantity(dto.getQuantity());
        activity.setNet(dto.getNet());
        activity.setClinician(dto.getClinician());
        activity.setAuthorization(authorization); // Set the authorization reference
        List<ObservationDTO> observationDTO = dto.getObservations();
        activity.setObservations(
                observationDTO != null
                        ? observationDTO.stream()
                                .map(dtoItem -> toObservation(dtoItem, activity)) // Pass activity to toObservation
                                .collect(Collectors.toList())
                        : new ArrayList<Observation>());
        return activity;
    }

    private Observation toObservation(ObservationDTO dto, Activity activity) {
        if (dto == null) {
            return null;
        }
        Observation observation = new Observation();
        observation.setType(dto.getType());
        observation.setCode(dto.getCode());
        observation.setValue(dto.getValue());
        observation.setValueType(dto.getValueType());
        observation.setActivity(activity); // Set the activity reference
        return observation;
    }
}