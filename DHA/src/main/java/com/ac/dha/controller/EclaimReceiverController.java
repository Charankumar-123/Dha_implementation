package com.ac.dha.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ac.dha.dto.response.UploadERxAuthorizationResponseDTO;
import com.ac.dha.utils.XmlUtil;

import jakarta.xml.bind.JAXBException;

@Controller
public class EclaimReceiverController {

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
}
