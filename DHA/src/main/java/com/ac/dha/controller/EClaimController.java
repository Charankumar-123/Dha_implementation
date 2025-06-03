package com.ac.dha.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ac.dha.dto.request.DownloadTransactionFileRequestDTO;
import com.ac.dha.dto.request.ErxRequestDTO;
import com.ac.dha.dto.request.GetNewTransactionsRequestDTO;
import com.ac.dha.dto.request.GeteRxTransactionRequestDTO;
import com.ac.dha.dto.request.SearchTransactionsRequestDTO;
import com.ac.dha.dto.request.SetTransactionDownloadedRequestDTO;
import com.ac.dha.dto.request.UploadERxAuthorizationDTO;
import com.ac.dha.dto.request.UploadERxAuthorizationForUserDTO;
import com.ac.dha.dto.request.UploadERxRequestDTO;
import com.ac.dha.dto.request.UploadERxRequestForUserDTO;
import com.ac.dha.service.EClaimService;

@Controller
public class EClaimController {

	@Autowired
	private EClaimService eclaimService;

	@PostMapping("/send-eclaim")
	public ResponseEntity<String> sendEclaim(@RequestBody ErxRequestDTO request) {
		return eclaimService.sendPriorRequestToEclaim(request);
	}

	@PostMapping("/upload-erx-request")
	public ResponseEntity<String> uploadERxRequest(@RequestBody UploadERxRequestForUserDTO dto) {
		return eclaimService.uploadERxRequest(dto);
	}

	@PostMapping("/upload-authorization")
	public ResponseEntity<String> uploadAuthorization(@RequestBody UploadERxAuthorizationForUserDTO dto) {
		return eclaimService.uploadERxAuthorization(dto);
	}

	@PostMapping("/get-new-transactions")
	public ResponseEntity<String> getNewTransactions(@RequestBody GetNewTransactionsRequestDTO dto) {
		return eclaimService.getNewTransactions(dto);
	}

	@PostMapping("/get-erx-transaction")
	public ResponseEntity<String> geteRxTransaction(@RequestBody GeteRxTransactionRequestDTO dto) {
		return eclaimService.geteRxTransaction(dto);
	}

	@PostMapping("/search-transactions")
	public ResponseEntity<String> searchTransactions(@RequestBody SearchTransactionsRequestDTO dto) {
		return eclaimService.searchTransactions(dto);
	}

	@PostMapping("/download-transaction-file")
	public ResponseEntity<String> downloadTransactionFile(@RequestBody DownloadTransactionFileRequestDTO dto) {
		return eclaimService.downloadTransactionFile(dto);
	}

	@PostMapping("/set-transaction-downloaded")
	public ResponseEntity<String> setTransactionDownloaded(@RequestBody SetTransactionDownloadedRequestDTO dto) {
		return eclaimService.setTransactionDownloaded(dto);
	}
}
