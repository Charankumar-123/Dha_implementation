package com.ac.dha.utils;

import org.springframework.stereotype.Component;

import com.ac.dha.dto.request.CancelAuthorizationDTO;
import com.ac.dha.dto.request.ErxRequestCancelDTO;
import com.ac.dha.dto.request.HeaderDTO;
import com.ac.dha.entities.CancelAuthorization;
import com.ac.dha.entities.Header;
import com.ac.dha.entities.PriorCancel;

@Component
public class ERXCancelEntityMapper {

	public PriorCancel toPriorCancel(ErxRequestCancelDTO dto) {
		if (dto == null) {
			return null;
		}
		PriorCancel priorCancel = new PriorCancel();
		priorCancel.setHeader(toCancelHeader(dto.getHeader()));
		priorCancel.setCancelAuthAuthorization(toCancelAuth(dto.getCancelAuthorization()));
		return priorCancel;
	}

	private Header toCancelHeader(HeaderDTO dto) {
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

	private CancelAuthorization toCancelAuth(CancelAuthorizationDTO dto) {
		if (dto == null) {
			return null;
		}
		CancelAuthorization authorization = new CancelAuthorization();
		authorization.setType(dto.getType());
		authorization.setAuthId(dto.getAuthId());
		authorization.setIdPayer(dto.getIDPayer());
		authorization.setMemberId(dto.getMemberID());
		authorization.setPayerID(dto.getPayerID());
		authorization.setEmiratesIDNumber(dto.getEmiratesIDNumber());
		authorization.setDateOrdered(dto.getDateOrdered());
		return authorization;
	}
}
