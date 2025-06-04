package com.ac.dha.service;

import java.nio.charset.StandardCharsets;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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
import com.ac.dha.entities.PriorRequest;
import com.ac.dha.repository.EclaimRepository;
import com.ac.dha.utils.ERXEntityMapper;
import com.ac.dha.utils.EclaimHttpResponse;
import com.ac.dha.utils.XmlUtil;

@Service
public class EClaimService {
	
	@Autowired
	private EclaimRepository eclaimRepository;
	
	@Autowired
	private ERXEntityMapper entityMapper;

	private final RestTemplate restTemplate = new RestTemplate();

	@Value("${eclaim.endpoint.url}")
	private String eclaimUrl;

	@Autowired
	private XmlUtil xmlUtil;

	@Autowired
	private EclaimHttpResponse eclaimHttpResponse; // work and test during API testing

	public ResponseEntity<String> sendPriorRequestToEclaim(ErxRequestDTO priorRequest) {
		try {
//			System.out.println("Req Format " + priorRequest.getAuthorization());
			System.out.println("XML Format " + xmlUtil.convertToXml(priorRequest));
			byte[] xmlPayload = xmlUtil.convertToXml(priorRequest);
			System.out.println("XML Payload byte[] length: " + xmlPayload.length);
			String xmlString = new String(xmlPayload, StandardCharsets.UTF_8);
			System.out.println("XML Payload as String:\n" + xmlString);
			
			PriorRequest entity = entityMapper.toPriorRequest(priorRequest);
			eclaimRepository.save(entity);

			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_XML);
			HttpEntity<byte[]> requestEntity = new HttpEntity<>(xmlPayload, headers);
			
			// Use the injected URL from application.properties
			ResponseEntity<String> response = restTemplate.postForEntity(eclaimUrl, requestEntity, String.class);

			System.out.println("Response Status Code: " + response.getStatusCodeValue());
			System.out.println("Response Body:\n" + response.getBody());

			return ResponseEntity.status(response.getStatusCode()).body(response.getBody());

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(500).body("Error: " + e.getMessage());
		}
	}

	public ResponseEntity<String> uploadERxRequest(UploadERxRequestForUserDTO requestFromUser) {
		try {
			byte[] xmlPayload = xmlUtil.convertToXml(requestFromUser.getPriorRequest());
			UploadERxRequestDTO request = new UploadERxRequestDTO();
			request.setClinicianLogin(requestFromUser.getClinicianLogin());
			request.setClinicianPwd(requestFromUser.getClinicianPwd());
			request.setFacilityLogin(requestFromUser.getFacilityLogin());
			request.setFacilityPwd(requestFromUser.getFacilityPwd());
			request.setFileName(requestFromUser.getFileName());
			request.setFileContent(xmlPayload);
//			request.setPriorRequest(null);
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_XML);

			HttpEntity<UploadERxRequestDTO> requestEntity = new HttpEntity<>(request, headers);

			ResponseEntity<String> response = restTemplate.postForEntity(eclaimUrl + "/uploadERxRequest", requestEntity,
					String.class);
			return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
		} catch (Exception e) {
			return ResponseEntity.status(500).body("Error: " + e.getMessage());
		}
	}

	public ResponseEntity<String> uploadERxAuthorization(UploadERxAuthorizationForUserDTO dtoUser) {
		try {
			byte[] xmlPayload = xmlUtil.convertToXml(dtoUser.getPriorRequest());
			UploadERxAuthorizationDTO dto = new UploadERxAuthorizationDTO();
			dto.setFileName(dtoUser.getFileName());
			dto.setPayerLogin(dtoUser.getPayerLogin());
			dto.setPayerPwd(dtoUser.getPayerPwd());
			dto.setFileContent(xmlPayload);
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_XML);
			HttpEntity<UploadERxAuthorizationDTO> requestEntity = new HttpEntity<>(dto, headers);

			ResponseEntity<String> response = restTemplate.postForEntity(eclaimUrl + "/UploadERxAuthorization",
					requestEntity, String.class);
			return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
		} catch (Exception e) {
			return ResponseEntity.status(500).body("Error: " + e.getMessage());
		}
	}

	public ResponseEntity<String> getNewTransactions(GetNewTransactionsRequestDTO dto) {
		try {
			byte[] xmlPayload = xmlUtil.convertToXml(dto);
//			System.out.println("xmlPayload=>"+xmlPayload.toString());
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_XML);
			headers.set("login", dto.getLogin());
			headers.set("pwd", dto.getPwd());
			HttpEntity<byte[]> requestEntity = new HttpEntity<>(xmlPayload, headers);

			ResponseEntity<String> response = restTemplate.postForEntity(eclaimUrl + "/GetNewTransactions",
					requestEntity, String.class);

			return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
		} catch (Exception e) {
			return ResponseEntity.status(500).body("Error: " + e.getMessage());
		}
	}

	public ResponseEntity<String> geteRxTransaction(GeteRxTransactionRequestDTO dto) {

		try {
			byte[] xmlPayload = xmlUtil.convertToXml(dto);
			HttpHeaders headers = new HttpHeaders();
			headers.set("login", dto.getLogin());
			headers.set("pwd", dto.getPwd());
			headers.set("memberID", String.valueOf(dto.getMemberID()));
			headers.set("eRxReferenceNo", String.valueOf(dto.geteRxReferenceNo()));

			headers.setContentType(MediaType.APPLICATION_XML);
			HttpEntity<byte[]> requestEntity = new HttpEntity<>(xmlPayload, headers);

			ResponseEntity<String> response = restTemplate.postForEntity(eclaimUrl + "/GeteRxTransaction",
					requestEntity, String.class);

			return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
		} catch (Exception e) {
			return ResponseEntity.status(500).body("Error: " + e.getMessage());
		}
	}

	public ResponseEntity<String> searchTransactions(SearchTransactionsRequestDTO dto) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
		try {
			byte[] xmlPayload = xmlUtil.convertToXml(dto);
			HttpHeaders headers = new HttpHeaders();
//			headers.set("login", dto.getLogin());
//			headers.set("pwd", dto.getPwd());
//			headers.set("direction", dto.getDirection());
//			headers.set("callerLicense", dto.getCallerLicense());
//			headers.set("clinicianLicense", dto.getClinicianLicense());
//			headers.set("memberID", String.valueOf(dto.getMemberID()));
//			headers.set("eRxReferenceNo", String.valueOf(dto.geteRxReferenceNo()));
//			headers.set("transactionStatus", dto.getTransactionStatus());
//			headers.set("transactionFromDate", dto.getTransactionFromDate().format(formatter));
//			headers.set("transactionToDate", dto.getTransactionFromDate().format(formatter));
//			headers.set("minRecordCount", String.valueOf(dto.getMinRecordCount()));
//			headers.set("maxRecordCount", String.valueOf(dto.getMaxRecordCount()));
			headers.setContentType(MediaType.APPLICATION_XML);
			HttpEntity<byte[]> requestEntity = new HttpEntity<>(xmlPayload, headers);

			ResponseEntity<String> response = restTemplate.postForEntity(eclaimUrl + "/SearchTransactions",
					requestEntity, String.class);

			return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
		} catch (Exception e) {
			return ResponseEntity.status(500).body("Error: " + e.getMessage());
		}
	}

	public ResponseEntity<String> downloadTransactionFile(DownloadTransactionFileRequestDTO dto) {
		try {
			byte[] xmlPayload = xmlUtil.convertToXml(dto);
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_XML);
			HttpEntity<byte[]> requestEntity = new HttpEntity<>(xmlPayload, headers);

			ResponseEntity<String> response = restTemplate.postForEntity(eclaimUrl + "/DownloadTransactionFile",
					requestEntity, String.class);

			return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
		} catch (Exception e) {
			return ResponseEntity.status(500).body("Error: " + e.getMessage());
		}
	}

	public ResponseEntity<String> setTransactionDownloaded(SetTransactionDownloadedRequestDTO dto) {
		try {
			byte[] xmlPayload = xmlUtil.convertToXml(dto);
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_XML);
			HttpEntity<byte[]> requestEntity = new HttpEntity<>(xmlPayload, headers);

			ResponseEntity<String> response = restTemplate.postForEntity(eclaimUrl + "/SetTransactionDownloaded",
					requestEntity, String.class);

			return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
		} catch (Exception e) {
			return ResponseEntity.status(500).body("Error: " + e.getMessage());
		}
	}

}
