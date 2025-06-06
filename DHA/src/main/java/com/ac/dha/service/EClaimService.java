package com.ac.dha.service;

import java.nio.charset.StandardCharsets;
import java.time.format.DateTimeFormatter;
import java.util.Base64;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.ac.dha.dto.response.UploadERxRequestResponseDTO;
import com.ac.dha.entties.PriorRequest;
import com.ac.dha.entties.UploadERxRequest;
import com.ac.dha.repository.GeteRxTransactionRequesRepository;
import com.ac.dha.repository.PriorRequestRepository;
import com.ac.dha.repository.UploadERxRequestRepository;
import com.ac.dha.utils.ERXEntityMapper;
import com.ac.dha.utils.Generator;
import com.ac.dha.utils.XmlUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.xml.bind.JAXBException;

@Service
public class EClaimService {

	public static final Logger log = LoggerFactory.getLogger(EClaimService.class);

	private final ObjectMapper objectMapper = new ObjectMapper();

	@Autowired
	private ExternalServiceClient externalServiceClient;

	private final RestTemplate restTemplate = new RestTemplate();

	@Autowired
	private PriorRequestRepository priorRequestRepository;

	@Autowired
	private UploadERxRequestRepository uploadERxRequestRepository;

	@Autowired
	private GeteRxTransactionRequesRepository transactionRepository;

	@Value("${eclaim.endpoint.url}")
	private String eclaimUrl;

	@Autowired
	private ERXEntityMapper dtoToEntityMapper;

	@Autowired
	private XmlUtil xmlUtil;

	@Autowired
	private Generator generator;

	public ResponseEntity<String> sendPriorRequestToEclaim(ErxRequestDTO priorRequest) {
		try {
			log.info("Req Format ", priorRequest.getAuthorization());
			log.debug("XML Format ", xmlUtil.convertToXml(priorRequest));
			byte[] xmlPayload = xmlUtil.convertToXml(priorRequest);
			System.out.println("Convert XML to byte[]\n------------>" + xmlPayload);

			System.out.println("XML Payload byte[] length: " + xmlPayload.length);
//			log.debug("Convert XML to byte[] ------>" + xmlPayload.toString());
//			System.out.println("Convert XML to byte[]\n------------>" + xmlPayload);

			String xmlFormat = new String(xmlPayload, StandardCharsets.UTF_8);
			System.out.println(" XML Payload as String:\n ------>" + xmlFormat);
//			String xmlString = new String(xmlPayload, StandardCharsets.UTF_8);

			// Convert DTO to Entity and Save to Database
			PriorRequest entity = dtoToEntityMapper.toPriorRequest(priorRequest);
			priorRequestRepository.save(entity);
			log.info("PriorRequest saved to database with ID: {}", entity.getId());

			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_XML);
			HttpEntity<byte[]> requestEntity = new HttpEntity<>(xmlPayload, headers);

			ResponseEntity<String> response = restTemplate.postForEntity(eclaimUrl, requestEntity, String.class);

			log.info("Response Status Code: ", response.getStatusCodeValue());
			log.info("Response Body:\n", response.getBody());

			return ResponseEntity.status(response.getStatusCode()).body(response.getBody());

		} catch (Exception e) {
			log.error("Exception in sendPriorRequestToEclaim: ", e);
			return ResponseEntity.status(500).body("Error: " + e.getMessage());
		}
	}


	public ResponseEntity<String> uploadERxRequest(UploadERxRequestForUserDTO requestFromUser) {
		try {
			log.info("uploadERxRequest called");

			// Validate required fields
			if (requestFromUser == null || requestFromUser.getPriorRequest() == null
					|| requestFromUser.getFileName() == null) {
				String errorMsg = requestFromUser == null ? "Request body is null"
						: requestFromUser.getPriorRequest() == null ? "priorRequest is null" : "fileName is null";
				return buildErrorResponse(400, errorMsg, "Validation failed");
			}

			// Convert to XML
			byte[] xmlPayload = xmlUtil.convertToXml(requestFromUser.getPriorRequest());
			String xmlString = new String(xmlPayload, StandardCharsets.UTF_8);
			log.debug("XML Payload: {}", xmlString);

			// Create UploadERxRequestDTO for external API
			UploadERxRequestDTO request = new UploadERxRequestDTO();
			request.setFacilityLogin(requestFromUser.getFacilityLogin());
			request.setFacilityPwd(requestFromUser.getFacilityPwd());
			request.setClinicianLogin(requestFromUser.getClinicianLogin());
			request.setClinicianPwd(requestFromUser.getClinicianPwd());
			request.setFileName(requestFromUser.getFileName());
			request.setFileContent(Base64.getEncoder().encode(xmlPayload));

			// Prepare entity
			UploadERxRequest entity = new UploadERxRequest();
			entity.setUniqId(generator.generateUUID());
			entity.setFacilityLogin(request.getFacilityLogin());
			entity.setFacilityPwd(request.getFacilityPwd());
			entity.setClinicianLogin(request.getClinicianLogin());
			entity.setClinicianPwd(request.getClinicianPwd());
			entity.setFileName(request.getFileName());
			entity.setFileContent(xmlPayload);

//			entity.seteRxReferenceNo(Integer.valueOf(generator.generateUUID()));
//			entity.seteRxReferenceNo(Optional.ofNullable(generator.generateERxReferenceNo())
//					.orElseThrow(() -> new IllegalStateException("Failed to generate eRxReferenceNo")));

			System.out.println("entity => " + entity);
			// Save to DB
			UploadERxRequest savedEntity = uploadERxRequestRepository.save(entity);
			log.info("Saved UploadERxRequest with ID: {}, eRxReferenceNo: {}", savedEntity.getId(),
					savedEntity.getUniqId());

			// Send to external API
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_XML);
			HttpEntity<byte[]> requestEntity = new HttpEntity<>(xmlUtil.convertToXml(request), headers);

			ResponseEntity<String> apiResponse = restTemplate.postForEntity(eclaimUrl + "/uploadERxRequest",
					requestEntity, String.class);
			log.info("External API status: {}, body: {}", apiResponse.getStatusCodeValue(), apiResponse.getBody());

			// Parse API response
			UploadERxRequestResponseDTO responseDTO = xmlUtil.fromXml(apiResponse.getBody(),
					UploadERxRequestResponseDTO.class);
//			if (responseDTO.geteRxReferenceNo() == null) {
//				responseDTO.seteRxReferenceNo(String.valueOf(savedEntity.getUniqId()));
//			}
			System.out.println("geteRxReferenceNo----------->" + responseDTO.geteRxReferenceNo());

			return ResponseEntity.ok(objectMapper.writeValueAsString(responseDTO));

		} catch (JAXBException e) {
			log.error("XML processing error", e);
			return buildErrorResponse(500, "XML Error: " + e.getMessage(), "Failed to process XML");
		} catch (Exception e) {
			log.error("Unexpected error", e);
			return buildErrorResponse(500, "Error: " + e.getMessage(), "Unexpected error occurred");
		}
	}

	private ResponseEntity<String> buildErrorResponse(int statusCode, String message, String report) {
		try {
			UploadERxRequestResponseDTO errorResponse = new UploadERxRequestResponseDTO();
			errorResponse.seteRxReferenceNo(null);
			errorResponse.setErrorMessage(message);
			errorResponse.setErrorReport(report.getBytes(StandardCharsets.UTF_8));
			return ResponseEntity.status(statusCode).body(objectMapper.writeValueAsString(errorResponse));
		} catch (Exception e) {
			log.error("Failed to serialize error response", e);
			return ResponseEntity.status(500).body("Error: Failed to serialize response");
		}
	}

	public ResponseEntity<String> uploadERxAuthorization(UploadERxAuthorizationForUserDTO dtoUser) {
		try {
			log.info("uploadERxAuthorization called with: {}", dtoUser);
			byte[] xmlPayload = xmlUtil.convertToXml(dtoUser.getPriorRequest());
			UploadERxAuthorizationDTO dto = new UploadERxAuthorizationDTO();
			dto.setFileName(dtoUser.getFileName());
			dto.setPayerLogin(dtoUser.getPayerLogin());
			dto.setPayerPwd(dtoUser.getPayerPwd());
			dto.setFileContent(xmlPayload);
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_XML);
			HttpEntity<UploadERxAuthorizationDTO> requestEntity = new HttpEntity<>(dto, headers);
			log.info("Sending POST to: {}/UploadERxAuthorization", eclaimUrl);
			ResponseEntity<String> response = restTemplate.postForEntity(eclaimUrl + "/UploadERxAuthorization",
					requestEntity, String.class);
			log.info("Response Status Code: {}", response.getStatusCodeValue());
			log.info("Response Body: [{}]", response.getBody());
			return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
		} catch (Exception e) {
			String errorMessage = e.getMessage() != null ? e.getMessage() : "Unknown error";
			log.error("General Exception: {}", errorMessage, e);
			return ResponseEntity.status(500).body("Error: " + e.getMessage());
		}
	}

	public ResponseEntity<String> getNewTransactions(GetNewTransactionsRequestDTO dto) {
		try {
			log.info("getNewTransactions called with: {}", dto);
			byte[] xmlPayload = xmlUtil.convertToXml(dto);

//			System.out.println("xmlPayload=>"+xmlPayload.toString());
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_XML);
			headers.set("login", dto.getLogin());
			headers.set("pwd", dto.getPwd());
			HttpEntity<byte[]> requestEntity = new HttpEntity<>(xmlPayload, headers);
			log.info("Sending POST to: {}/getNewTransactions", eclaimUrl);
			ResponseEntity<String> response = restTemplate.postForEntity(eclaimUrl + "/GetNewTransactions",
					requestEntity, String.class);
			log.info("Response Status Code: {}", response.getStatusCodeValue());
			log.info("Response Body: [{}]", response.getBody());
			return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
		} catch (Exception e) {
			log.error("General Exception: {}", e);
			return ResponseEntity.status(500).body("Error: " + e.getMessage());
		}
	}

	public ResponseEntity<String> geteRxTransaction(GeteRxTransactionRequestDTO dto) {

		try {
			log.info("geteRxTransaction called with: {}", dto);
			byte[] xmlPayload = xmlUtil.convertToXml(dto);
			HttpHeaders headers = new HttpHeaders();
			headers.set("login", dto.getLogin());
			headers.set("pwd", dto.getPwd());
			headers.set("memberID", String.valueOf(dto.getMemberID()));
			headers.set("eRxReferenceNo", String.valueOf(dto.geteRxReferenceNo()));
			headers.setContentType(MediaType.APPLICATION_XML);
			HttpEntity<byte[]> requestEntity = new HttpEntity<>(xmlPayload, headers);
			log.info("Sending POST to: {}/geteRxTransaction", eclaimUrl);
			ResponseEntity<String> response = restTemplate.postForEntity(eclaimUrl + "/GeteRxTransaction",
					requestEntity, String.class);
			log.info("Response Status Code: {}", response.getStatusCodeValue());
			log.info("Response Body: [{}]", response.getBody());
			return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
		} catch (Exception e) {
			log.error("General Exception: {}", e);
			return ResponseEntity.status(500).body("Error: " + e.getMessage());
		}
	}

	public ResponseEntity<String> searchTransactions(SearchTransactionsRequestDTO dto) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
		try {
			log.info("searchTransactions called with: {}", dto);
			byte[] xmlPayload = xmlUtil.convertToXml(dto);
			HttpHeaders headers = new HttpHeaders();
			headers.set("login", dto.getLogin());
			headers.set("pwd", dto.getPwd());
			headers.set("direction", dto.getDirection());
			headers.set("callerLicense", dto.getCallerLicense());
			headers.set("clinicianLicense", dto.getClinicianLicense());
			headers.set("memberID", String.valueOf(dto.getMemberID()));
			headers.set("eRxReferenceNo", String.valueOf(dto.geteRxReferenceNo()));
			headers.set("transactionStatus", dto.getTransactionStatus());
			headers.set("transactionFromDate", dto.getTransactionFromDate().format(formatter));
			headers.set("transactionToDate", dto.getTransactionFromDate().format(formatter));
			headers.set("minRecordCount", String.valueOf(dto.getMinRecordCount()));
			headers.set("maxRecordCount", String.valueOf(dto.getMaxRecordCount()));
			headers.setContentType(MediaType.APPLICATION_XML);
			HttpEntity<byte[]> requestEntity = new HttpEntity<>(xmlPayload, headers);
			log.info("Sending POST to: {}/searchTransactions", eclaimUrl);
			ResponseEntity<String> response = restTemplate.postForEntity(eclaimUrl + "/SearchTransactions",
					requestEntity, String.class);
			log.info("Response Status Code: {}", response.getStatusCodeValue());
			log.info("Response Body: [{}]", response.getBody());
			return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
		} catch (Exception e) {
			log.error("General Exception: {}", e);
			return ResponseEntity.status(500).body("Error: " + e.getMessage());
		}
	}

	public ResponseEntity<String> downloadTransactionFile(DownloadTransactionFileRequestDTO dto) {
		try {
			log.info("downloadTransactionFile called with: {}", dto);
			byte[] xmlPayload = xmlUtil.convertToXml(dto);
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_XML);
			HttpEntity<byte[]> requestEntity = new HttpEntity<>(xmlPayload, headers);
			log.info("Sending POST to: {}/downloadTransactionFile", eclaimUrl);
			ResponseEntity<String> response = restTemplate.postForEntity(eclaimUrl + "/DownloadTransactionFile",
					requestEntity, String.class);
			log.info("Response Status Code: {}", response.getStatusCodeValue());
			log.info("Response Body: [{}]", response.getBody());
			return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
		} catch (Exception e) {
			log.error("General Exception: {}", e);
			return ResponseEntity.status(500).body("Error: " + e.getMessage());
		}
	}

	public ResponseEntity<String> setTransactionDownloaded(SetTransactionDownloadedRequestDTO dto) {
		try {
			log.info("setTransactionDownloaded called with: {}", dto);
			byte[] xmlPayload = xmlUtil.convertToXml(dto);
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_XML);
			HttpEntity<byte[]> requestEntity = new HttpEntity<>(xmlPayload, headers);
			log.info("Sending POST to: {}/setTransactionDownloaded", eclaimUrl);
			ResponseEntity<String> response = restTemplate.postForEntity(eclaimUrl + "/SetTransactionDownloaded",
					requestEntity, String.class);
			log.info("Response Status Code: {}", response.getStatusCodeValue());
			log.info("Response Body: [{}]", response.getBody());
			return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
		} catch (Exception e) {
			log.error("General Exception: {}", e);
			return ResponseEntity.status(500).body("Error: " + e.getMessage());
		}
	}
}
