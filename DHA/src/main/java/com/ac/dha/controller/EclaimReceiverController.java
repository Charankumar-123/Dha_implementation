package com.ac.dha.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ac.dha.dto.response.SearchTransactionsResponseDTO;
import com.ac.dha.dto.response.UploadERxAuthorizationResponseDTO;
import com.ac.dha.dto.response.UploadERxRequestResponseDTO;
import com.ac.dha.utils.XmlUtil;

import jakarta.xml.bind.JAXBException;

@Controller
public class EclaimReceiverController {
	private static final Logger log = LoggerFactory.getLogger(EclaimReceiverController.class);

//	@Autowired
//	private EClaimService eclaimService;
//
//	@PostMapping(value = "/receive-eclaim", consumes = "application/xml", produces = "application/xml")
//	public ResponseEntity<ErxRequestDTO> receiveEclaim(@RequestBody ErxRequestDTO request) {
//		System.out.println("Received: " + request);
//		return ResponseEntity.ok(request);
//	}

	@Autowired
	private XmlUtil xmlUtil;

	@PostMapping(value = "/receive-eclaim", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UploadERxAuthorizationResponseDTO> receiveEclaim(@RequestBody String xmlResponse) {
		try {
			System.out.println("Received XML: " + xmlResponse);
			UploadERxAuthorizationResponseDTO responseDTO = xmlUtil.fromXml(xmlResponse,
					UploadERxAuthorizationResponseDTO.class);
			return ResponseEntity.ok(responseDTO);
		} catch (JAXBException e) {
			return ResponseEntity.status(500).body(new UploadERxAuthorizationResponseDTO() {
				{
					setFileName("XML Error: " + e.getMessage());
				}
			});
		}
	}

	@PostMapping(value = "/display-eclaim-response", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UploadERxRequestResponseDTO> displayEclaimResponse(@RequestBody String xmlResponse) {
		try {
			log.info("Received XML for /display-eclaim-response: {}", xmlResponse);
			UploadERxRequestResponseDTO responseDTO = xmlUtil.fromXml(xmlResponse, UploadERxRequestResponseDTO.class);
			log.info("Parsed UploadERxRequestResponseDTO: {}", responseDTO);
			return ResponseEntity.ok(responseDTO);
		} catch (JAXBException e) {
			log.error("Failed to unmarshal XML: {}", xmlResponse, e);
			UploadERxRequestResponseDTO errorResponse = new UploadERxRequestResponseDTO();
			errorResponse.setErrorMessage("XML Error: " + e.getMessage());
			errorResponse.setErrorReport("Failed to process XML".getBytes());
			return ResponseEntity.status(500).body(errorResponse);
		}
	}

	@PostMapping(value = "/search-transactions", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<SearchTransactionsResponseDTO> searchTransactions(@RequestBody String xmlRequest) {
		SearchTransactionsResponseDTO response = new SearchTransactionsResponseDTO();
		try {
			log.info("Received XML for /xml/search-transactions: {}", xmlRequest);
			response.setFoundTransactions("5");
			response.setErrorMessage(null);
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			log.error("Error processing search-transactions request", e);
			response.setFoundTransactions("0");
			response.setErrorMessage("Processing Error: " + e.getMessage());
			return ResponseEntity.status(500).body(response);
		}
	}
}
