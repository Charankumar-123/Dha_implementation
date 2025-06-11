package com.ac.dha.utils;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.ac.dha.dto.request.AuthActivityDTO;
import com.ac.dha.dto.request.AuthAuthorizationDTO;
import com.ac.dha.dto.request.ErxRequestAuthDTO;
import com.ac.dha.dto.request.HeaderDTO;
import com.ac.dha.entities.AuthActivity;
import com.ac.dha.entities.AuthAuthorization;
import com.ac.dha.entities.Header;
import com.ac.dha.entities.PriorAuthorization;

@Component
public class ERXAuthEntityMapper {

	public PriorAuthorization toPriorAuth(ErxRequestAuthDTO dto) {
		if (dto == null) {
			return null;
		}
		PriorAuthorization priorAuth = new PriorAuthorization();
		priorAuth.setHeader(toAuthHeader(dto.getHeader()));
		priorAuth.setAuthAuthorization(toAuthAuthorization(dto.getAuthorizationAuth()));
		return priorAuth;
	}

	private Header toAuthHeader(HeaderDTO dto) {
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

	private AuthAuthorization toAuthAuthorization(AuthAuthorizationDTO dto) {
		if (dto == null) {
			return null;
		}
		AuthAuthorization authorization = new AuthAuthorization();
		authorization.setResult(dto.getResult());
		authorization.setAuthID(dto.getAuthID());
		authorization.setIdPayer(dto.getIdPayer());
		authorization.setStart(dto.getStart());
		authorization.setEnd(dto.getEnd());
		authorization.setComments(dto.getComments());

		if (dto.getActivity() != null) {
			List<AuthActivity> activities = dto.getActivity().stream().map(this::toAuthActivities)
					.collect(Collectors.toList());

			activities.forEach(activity -> activity.setAuthAuthorization(authorization));

			authorization.setAuthActivity(activities);
		}

		return authorization;
	}

	private AuthActivity toAuthActivities(AuthActivityDTO dto) {
		if (dto == null) {
			return null;
		}
		AuthActivity activity = new AuthActivity();
		activity.setId(dto.getId());
		activity.setAcctivityId(dto.getId());
		activity.setType(dto.getType());
		activity.setCode(dto.getCode());
		activity.setQuantity(dto.getQuantity());
		activity.setNet(dto.getNet());
		activity.setPatientShare(dto.getPatientShare());
		activity.setPaymentAmount(dto.getPaymentAmount());
		return activity;
	}
}
