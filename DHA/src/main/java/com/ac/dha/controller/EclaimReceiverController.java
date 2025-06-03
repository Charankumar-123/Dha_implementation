package com.ac.dha.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ac.dha.dto.request.ErxRequestDTO;

@Controller
public class EclaimReceiverController {

//	@Autowired
//	private EClaimService eclaimService;

	@PostMapping(value = "/receive-eclaim", consumes = "application/xml", produces = "application/xml")
	public ResponseEntity<ErxRequestDTO> receiveEclaim(@RequestBody ErxRequestDTO request) {
		System.out.println("Received: " + request);
		return ResponseEntity.ok(request);
	}
}
