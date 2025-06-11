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

import com.ac.dha.controller.EclaimReceiverController;
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
import com.ac.dha.dto.response.DownloadTransactionFileResponseDTO;
import com.ac.dha.dto.response.GetNewTransactionsResponseDTO;
import com.ac.dha.dto.response.GeteRxTransactionResponseDTO;
import com.ac.dha.dto.response.SearchTransactionsResponseDTO;
import com.ac.dha.dto.response.SetTransactionDownloadedResponseDTO;
import com.ac.dha.dto.response.UploadERxAuthorizationResponseDTO;
import com.ac.dha.dto.response.UploadERxRequestResponseDTO;
import com.ac.dha.entities.DownloadTransactionFileRequest;
import com.ac.dha.entities.GetNewTransactionsRequest;
import com.ac.dha.entities.GeteRxTransactionReques;
import com.ac.dha.entities.PriorRequest;
import com.ac.dha.entities.SearchTransactionsRequest;
import com.ac.dha.entities.SetTransactionDownloadedRequest;
import com.ac.dha.entities.UploadERxAuthorization;
import com.ac.dha.entities.UploadERxRequest;
import com.ac.dha.repository.DownloadTransactionFileRepo;
import com.ac.dha.repository.GetNewTransactionRepository;
import com.ac.dha.repository.GeteRxTransactionRequesRepository;
import com.ac.dha.repository.PriorRequestRepository;
import com.ac.dha.repository.SearchTransactionsRepo;
import com.ac.dha.repository.SetTransactionDownRepo;
import com.ac.dha.repository.UploadERxAuthorizationRepository;
import com.ac.dha.repository.UploadERxRequestRepository;
import com.ac.dha.utils.ERXAuthEntityMapper;
import com.ac.dha.utils.ERXCancelEntityMapper;
import com.ac.dha.utils.ERXEntityMapper;
import com.ac.dha.utils.Generator;
import com.ac.dha.utils.XmlUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.xml.bind.JAXBException;

@Service
public class EClaimService {

	private final EclaimReceiverController eclaimReceiverController;

	public static final Logger log = LoggerFactory.getLogger(EClaimService.class);

	private final ObjectMapper objectMapper = new ObjectMapper();

	@Autowired
	private UploadERxAuthorizationRepository UploadERxAuthorizationRepository;

	@Autowired
	private ExternalServiceClient externalServiceClient;

	@Autowired
	private GetNewTransactionRepository getNewTransactionRepository;

	private final RestTemplate restTemplate = new RestTemplate();

	@Autowired
	private ERXCancelEntityMapper erxCancelEntityMapper;

	@Autowired
	private PriorRequestRepository priorRequestRepository;

	@Autowired
	private UploadERxRequestRepository uploadERxRequestRepository;

	@Autowired
	private GeteRxTransactionRequesRepository transactionRepository;

	@Autowired
	private SearchTransactionsRepo searchTransactionsRequestRepository;

	@Autowired
	private DownloadTransactionFileRepo downloadTransactionFileRepo;

	@Autowired
	private SetTransactionDownRepo setTransactionDownRepo;

	@Autowired
	private ERXAuthEntityMapper authEntityMapper;

	@Value("${eclaim.endpoint.url}")
	private String eclaimUrl;

	@Autowired
	private ERXEntityMapper dtoToEntityMapper;

	@Autowired
	private XmlUtil xmlUtil;

	@Autowired
	private Generator generator;

	EClaimService(EclaimReceiverController eclaimReceiverController) {
		this.eclaimReceiverController = eclaimReceiverController;
	}

	public ResponseEntity<String> sendPriorRequestToEclaim(ErxRequestDTO priorRequest) {
		try {
			log.info("Req Format ", priorRequest.getAuthorization());
			log.debug("XML Format ", xmlUtil.convertToXml(priorRequest));
			byte[] xmlPayload = xmlUtil.convertToXml(priorRequest);
			System.out.println("Convert XML to byte[]\n------------>" + xmlPayload);

			System.out.println("XML Payload byte[] length: " + xmlPayload.length);

			String xmlFormat = new String(xmlPayload, StandardCharsets.UTF_8);
			System.out.println(" XML Payload as String:\n ------>" + xmlFormat);

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
			String generatedERxRefNo = "ERX-" + System.currentTimeMillis();
			savedEntity.seteRxReferenceNo(generatedERxRefNo); // ✅ Set in entity
			uploadERxRequestRepository.save(savedEntity);
			UploadERxRequestResponseDTO responseDTO = new UploadERxRequestResponseDTO();
			responseDTO.seteRxReferenceNo(generatedERxRefNo);
			responseDTO.setStatus("SUCCESS");
			responseDTO.setErrorMessage(null);
			responseDTO.setErrorReport(null);

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
			if (dtoUser == null || dtoUser.getPriorRequest() == null || dtoUser.getFileName() == null
					|| dtoUser.getPayerLogin() == null || dtoUser.getPayerPwd() == null) {
				String errorMsg = dtoUser == null ? "Request body is null"
						: dtoUser.getPriorRequest() == null ? "PriorRequest is null"
								: dtoUser.getFileName() == null ? "FileName is null"
										: dtoUser.getPayerLogin() == null ? "PayerLogin is null" : "PayerPwd is null";
				log.error("Validation failed: {}", errorMsg);
				return buildErrorResponse(400, errorMsg);
			}
//			UploadERxAuthorization authorization = toUploadERxAuthorization(dtoUser);
//			UploadERxAuthorizationRepository.save(authorization);
			log.info("uploadERxAuthorization called with: {}", dtoUser);
			byte[] xmlPayload = xmlUtil.convertToXml(dtoUser.getPriorRequest());
			UploadERxAuthorization authorization = new UploadERxAuthorization();
			authorization.setPayerLogin(dtoUser.getPayerLogin());
			authorization.setPayerPwd(dtoUser.getPayerPwd());
			authorization.setFileName(dtoUser.getFileName());
			authorization.setFileContent(xmlPayload);

			UploadERxAuthorizationRepository.save(authorization);

			UploadERxAuthorizationDTO dto = new UploadERxAuthorizationDTO();
			dto.setFileName(dtoUser.getFileName());
			dto.setPayerLogin(dtoUser.getPayerLogin());
			dto.setPayerPwd(dtoUser.getPayerPwd());
			dto.setFileContent(xmlPayload);
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_XML);
			headers.set("payerLogin", dtoUser.getPayerLogin());
			headers.set("payerPwd", dtoUser.getPayerPwd());
			HttpEntity<UploadERxAuthorizationDTO> requestEntity = new HttpEntity<>(dto, headers);
			log.info("Sending POST to: {}/UploadERxAuthorization", eclaimUrl);
			ResponseEntity<String> response = restTemplate.postForEntity(eclaimUrl + "/UploadERxAuthorization",
					requestEntity, String.class);
			UploadERxRequestResponseDTO responseDTO = xmlUtil.fromXml(response.getBody(),
					UploadERxRequestResponseDTO.class);

			UploadERxAuthorizationResponseDTO authorizationResponseDTO = new UploadERxAuthorizationResponseDTO();
			authorizationResponseDTO.setFileContent(responseDTO.getErrorReport());
			if (responseDTO == null) {
				log.error("Failed to parse response into UploadERxAuthorizationResponseDTO");
				return buildErrorResponse(500, "Failed to parse API response");
			}

			String xmlResponse = xmlUtil.convertToXmlss(authorizationResponseDTO,
					UploadERxAuthorizationResponseDTO.class);

			log.info("Response Status Code: {}", response.getStatusCodeValue());
			log.info("Response Body: [{}]", response.getBody());
			return ResponseEntity.status(response.getStatusCode()).contentType(MediaType.APPLICATION_XML)
					.body(xmlResponse);
		} catch (Exception e) {
			String errorMessage = e.getMessage() != null ? e.getMessage() : "Unknown error";
			log.error("General Exception: {}", errorMessage, e);
			return ResponseEntity.status(500).body("Error: " + e.getMessage());
		}
	}

	public ResponseEntity<String> getNewTransactions(GetNewTransactionsRequestDTO dto) {
		try {
			GetNewTransactionsRequest getNewTransactionsRequest = toGetNewTransaction(dto);
			getNewTransactionRepository.save(getNewTransactionsRequest);
			log.info("getNewTransactions called with: {}", dto);
			byte[] xmlPayload = xmlUtil.convertToXml(dto);

			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_XML);
			headers.set("login", dto.getLogin());
			headers.set("pwd", dto.getPwd());
			HttpEntity<byte[]> requestEntity = new HttpEntity<>(xmlPayload, headers);
			log.info("Sending POST to: {}/getNewTransactions", eclaimUrl);

			ResponseEntity<String> response = restTemplate.postForEntity(eclaimUrl + "/GetNewTransactions",
					requestEntity, String.class);

			// Parse the XML response into UploadERxRequestResponseDTO
			UploadERxRequestResponseDTO temp = xmlUtil.fromXml(response.getBody(), UploadERxRequestResponseDTO.class);

			// Create and populate the response DTO
			GetNewTransactionsResponseDTO responseDTO = new GetNewTransactionsResponseDTO();
			responseDTO.setErrorMessage(temp.getErrorMessage());
			// Set the xmlTransactions field with the response body
			responseDTO.setXmlTransactions(response.getBody()); // Properly set the xmlTransactions

			// Convert responseDTO to XML for the response
			String xmlResponse = xmlUtil.convertToXmls(responseDTO);

			log.info("Response Status Code: {}", response.getStatusCodeValue());
			log.info("Response Body: [{}]", xmlResponse);

			return ResponseEntity.status(response.getStatusCode()).contentType(MediaType.APPLICATION_XML)
					.body(xmlResponse);

		} catch (Exception e) {
			log.error("General Exception: {}", e);
			GetNewTransactionsResponseDTO errorResponse = new GetNewTransactionsResponseDTO();
			errorResponse.setErrorMessage("Error: " + e.getMessage());
			errorResponse.setXmlTransactions(null);

			try {
				String xmlError = xmlUtil.convertToXmls(errorResponse);
				return ResponseEntity.status(500).contentType(MediaType.APPLICATION_XML).body(xmlError);
			} catch (Exception ex) {
				log.error("Error serializing error response: {}", ex.getMessage(), ex);
				return ResponseEntity.status(500).contentType(MediaType.APPLICATION_XML).body(
						"<GetNewTransactionsResponseDTO><xmlTransactions/><errorMessage>Internal server error</errorMessage></GetNewTransactionsResponseDTO>");
			}
		}
	}

	private GetNewTransactionsRequest toGetNewTransaction(GetNewTransactionsRequestDTO dto) {
		GetNewTransactionsRequest request = new GetNewTransactionsRequest();
		request.setLogin(dto.getLogin());
		request.setPwd(dto.getPwd());
		return request;
	}

	public ResponseEntity<String> geteRxTransaction(GeteRxTransactionRequestDTO dto) {

		try {
			GeteRxTransactionReques dtoRequest = toGeteRxTransactionReques(dto);
			transactionRepository.save(dtoRequest);
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
			UploadERxRequestResponseDTO temp = xmlUtil.fromXml(response.getBody(), UploadERxRequestResponseDTO.class);
			GeteRxTransactionResponseDTO responseDTO = new GeteRxTransactionResponseDTO();
			responseDTO.setErrorMessage(temp.getErrorMessage());
			responseDTO.setXmlTransactions(response.getBody());
			String xmlDTO = xmlUtil.convertToXmls(responseDTO);
			log.info("Response Status Code: {}", response.getStatusCodeValue());
			log.info("Response Body: [{}]", response.getBody());
			return ResponseEntity.status(response.getStatusCode()).contentType(MediaType.APPLICATION_XML).body(xmlDTO);
		} catch (Exception e) {
			log.error("General Exception: {}", e);
			return ResponseEntity.status(500).body("Error: " + e.getMessage());
		}
	}

	private GeteRxTransactionReques toGeteRxTransactionReques(GeteRxTransactionRequestDTO dto) {
		GeteRxTransactionReques request = new GeteRxTransactionReques();
		request.setLogin(dto.getLogin());
		request.setPwd(dto.getPwd());
		request.setMemberID(dto.getMemberID());
		return request;
	}

	public ResponseEntity<String> searchTransactions(SearchTransactionsRequestDTO dto) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

		try {
			SearchTransactionsRequest requestEntity = toSearchTransactionsRequest(dto);
			searchTransactionsRequestRepository.save(requestEntity);

			byte[] xmlPayload = xmlUtil.convertToXml(dto);

			HttpHeaders headers = new HttpHeaders();
			headers.set("login", dto.getLogin());
			headers.set("pwd", dto.getPwd());
			headers.set("direction", dto.getDirection());
			headers.set("callerLicense", dto.getCallerLicense());
			headers.set("clinicianLicense", dto.getClinicianLicense());
			headers.set("memberID", dto.getMemberID());
			headers.set("eRxReferenceNo", dto.geteRxReferenceNo());
			headers.set("transactionStatus", dto.getTransactionStatus());
			headers.set("transactionFromDate", dto.getTransactionFromDate().format(formatter));
			headers.set("transactionToDate", dto.getTransactionToDate().format(formatter));
			headers.set("minRecordCount", String.valueOf(dto.getMinRecordCount()));
			headers.set("maxRecordCount", String.valueOf(dto.getMaxRecordCount()));
			headers.setContentType(MediaType.APPLICATION_XML);

			HttpEntity<byte[]> requestEntity1 = new HttpEntity<>(xmlPayload, headers);
			ResponseEntity<String> response = restTemplate.postForEntity(eclaimUrl + "/SearchTransactions",
					requestEntity1, String.class);

			UploadERxRequestResponseDTO tempResponse = xmlUtil.fromXml(response.getBody(),
					UploadERxRequestResponseDTO.class);

			// Populate your response DTO
			SearchTransactionsResponseDTO responseDTO = new SearchTransactionsResponseDTO();
			responseDTO.setFoundTransactions(tempResponse.geteRxReferenceNo() != null ? "1" : "0");
			responseDTO.setErrorMessage(tempResponse.getErrorMessage());

			// Convert responseDTO to XML
			String xmlResponse = xmlUtil.convertToXmls(responseDTO); // Returns XML string with
																		// <SearchTransactionsResponseDTO>

			return ResponseEntity.status(response.getStatusCode()).contentType(MediaType.APPLICATION_XML)
					.body(xmlResponse);

		} catch (Exception e) {
			log.error("Error in searchTransactions: {}", e.getMessage(), e);

			SearchTransactionsResponseDTO errorResponse = new SearchTransactionsResponseDTO();
			errorResponse.setFoundTransactions("0");
			errorResponse.setErrorMessage("Error: " + e.getMessage());

			try {
				String xmlError = xmlUtil.convertToXmls(errorResponse);
				return ResponseEntity.status(500).contentType(MediaType.APPLICATION_XML).body(xmlError);
			} catch (Exception ex) {
				log.error("Error serializing error response: {}", ex.getMessage(), ex);
				return ResponseEntity.status(500).contentType(MediaType.APPLICATION_XML).body(
						"<SearchTransactionsResponseDTO><foundTransactions>0</foundTransactions><errorMessage>Internal server error</errorMessage></SearchTransactionsResponseDTO>");
			}
		}
	}

	private SearchTransactionsRequest toSearchTransactionsRequest(SearchTransactionsRequestDTO dto) {
		SearchTransactionsRequest entity = new SearchTransactionsRequest();
		entity.setLogin(dto.getLogin());
		entity.setPwd(dto.getPwd());
		entity.setDirection(dto.getDirection());
		entity.setCallerLicense(dto.getCallerLicense());
		entity.setClinicianLicense(dto.getClinicianLicense());
		entity.setMemberID(dto.getMemberID());
		entity.seteRxReferenceNo(dto.geteRxReferenceNo());
		entity.setTransactionStatus(dto.getTransactionStatus());
		entity.setTransactionFromDate(dto.getTransactionFromDate());
		entity.setTransactionToDate(dto.getTransactionToDate());
		entity.setMinRecordCount(dto.getMinRecordCount());
		entity.setMaxRecordCount(dto.getMaxRecordCount());
		return entity;
	}

	public ResponseEntity<String> downloadTransactionFile(DownloadTransactionFileRequestDTO dto) {
		try {
			// Convert DTO to entity and save to repository
			DownloadTransactionFileRequest request = toDownloadTransactionFileRequest(dto);
			downloadTransactionFileRepo.save(request);
			log.info("downloadTransactionFile called with: {}", dto);

			// Convert DTO to XML payload
			byte[] xmlPayload = xmlUtil.convertToXml(dto);

			// Set up HTTP headers
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_XML);
			headers.set("login", dto.getLogin());
			headers.set("pwd", dto.getPwd());
			headers.set("fileID", dto.getFileID());
			HttpEntity<byte[]> requestEntity = new HttpEntity<>(xmlPayload, headers);

			// Log and send POST request to external API
			log.info("Sending POST to: {}/DownloadTransactionFile", eclaimUrl);
			ResponseEntity<String> response = restTemplate.postForEntity(eclaimUrl + "/DownloadTransactionFile",
					requestEntity, String.class);

			// Parse the XML response into DownloadTransactionFileResponseDTO
			DownloadTransactionFileResponseDTO responseDTO = xmlUtil.fromXml(response.getBody(),
					DownloadTransactionFileResponseDTO.class);

			// Validate response
			if (responseDTO == null) {
				log.error("Failed to parse response into DownloadTransactionFileResponseDTO");
				responseDTO = new DownloadTransactionFileResponseDTO();
				responseDTO.setErrorMessage("Failed to parse API response");
			} else {
				// Log response details
				log.info("Response Status Code: {}", response.getStatusCodeValue());
				log.info("Response Body: [fileName={}, fileLength={}, errorMessage={}]", responseDTO.getFileName(),
						responseDTO.getFile() != null ? responseDTO.getFile().length : 0,
						responseDTO.getErrorMessage());
			}

			// Convert response DTO to XML string
			String xmlResponse = xmlUtil.convertToXmls(responseDTO);

			// Return response with XML content type
			return ResponseEntity.status(response.getStatusCode()).contentType(MediaType.APPLICATION_XML)
					.body(xmlResponse);

		} catch (JAXBException e) {
			log.error("XML processing error: {}", e.getMessage(), e);
			DownloadTransactionFileResponseDTO errorResponse = new DownloadTransactionFileResponseDTO();
			errorResponse.setErrorMessage("XML Error: " + e.getMessage());
			errorResponse.setFileName(null);
			errorResponse.setFile(null);

			try {
				String xmlError = xmlUtil.convertToXmls(errorResponse);
				return ResponseEntity.status(500).contentType(MediaType.APPLICATION_XML).body(xmlError);
			} catch (Exception ex) {
				log.error("Error serializing error response: {}", ex.getMessage(), ex);
				return ResponseEntity.status(500).contentType(MediaType.APPLICATION_XML).body(
						"<DownloadTransactionFileResponseDTO><fileName/><file/><errorMessage>Internal server error</errorMessage></DownloadTransactionFileResponseDTO>");
			}
		} catch (Exception e) {
			log.error("General Exception: {}", e.getMessage(), e);
			DownloadTransactionFileResponseDTO errorResponse = new DownloadTransactionFileResponseDTO();
			errorResponse.setErrorMessage("Error: " + e.getMessage());
			errorResponse.setFileName(null);
			errorResponse.setFile(null);

			try {
				String xmlError = xmlUtil.convertToXmls(errorResponse);
				return ResponseEntity.status(500).contentType(MediaType.APPLICATION_XML).body(xmlError);
			} catch (Exception ex) {
				log.error("Error serializing error response: {}", ex.getMessage(), ex);
				return ResponseEntity.status(500).contentType(MediaType.APPLICATION_XML).body(
						"<DownloadTransactionFileResponseDTO><fileName/><file/><errorMessage>Internal server error</errorMessage></DownloadTransactionFileResponseDTO>");
			}
		}
	}

	private DownloadTransactionFileRequest toDownloadTransactionFileRequest(DownloadTransactionFileRequestDTO dto) {
		DownloadTransactionFileRequest request = new DownloadTransactionFileRequest();
		request.setLogin(dto.getLogin());
		request.setPwd(dto.getPwd());
		request.setFileID(dto.getFileID());
		return request;
	}

	public ResponseEntity<String> setTransactionDownloaded(SetTransactionDownloadedRequestDTO dto) {
		try {
			// Validate input DTO
			if (dto == null || dto.getLogin() == null || dto.getPwd() == null || dto.getFileID() == null) {
				String errorMsg = dto == null ? "Request body is null"
						: dto.getLogin() == null ? "Login is null"
								: dto.getPwd() == null ? "Password is null" : "FileID is null";
				log.error("Validation failed: {}", errorMsg);
				return buildErrorResponse(400, errorMsg);
			}

			// Convert DTO to entity and save to repository
			SetTransactionDownloadedRequest request = toSetTransactionDownloadedRequest(dto);
			setTransactionDownRepo.save(request);
			log.info("setTransactionDownloaded called with: {}", dto);

			// Convert DTO to XML payload
			byte[] xmlPayload = xmlUtil.convertToXml(dto);

			// Set up HTTP headers
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_XML);
			headers.set("login", dto.getLogin());
			headers.set("pwd", dto.getPwd());
			headers.set("fileID", dto.getFileID());
			HttpEntity<byte[]> requestEntity = new HttpEntity<>(xmlPayload, headers);

			// Log and send POST request to external API
			log.info("Sending POST to: {}/SetTransactionDownloaded", eclaimUrl);
			ResponseEntity<String> response = restTemplate.postForEntity(eclaimUrl + "/SetTransactionDownloaded",
					requestEntity, String.class);

			// Parse the XML response into SetTransactionDownloadedResponseDTO
			UploadERxRequestResponseDTO responseDTO = xmlUtil.fromXml(response.getBody(),
					UploadERxRequestResponseDTO.class);

			SetTransactionDownloadedResponseDTO responseDTO2 = new SetTransactionDownloadedResponseDTO();
			responseDTO2.setErrorMessage(responseDTO.getErrorMessage());

			// Validate response
			if (responseDTO2 == null) {
				log.error("Failed to parse response into SetTransactionDownloadedResponseDTO");
				return buildErrorResponse(500, "Failed to parse API response");
			}

			// Log response details
			log.info("Response Status Code: {}", response.getStatusCodeValue());
			log.info("Response Body: [errorMessage={}]", responseDTO.getErrorMessage());

			// Convert response DTO to XML string
			String xmlResponse = xmlUtil.convertToXmls(responseDTO2);

			// Return response with XML content type
			return ResponseEntity.status(response.getStatusCode()).contentType(MediaType.APPLICATION_XML)
					.body(xmlResponse);

		} catch (JAXBException e) {
			log.error("XML processing error: {}", e.getMessage(), e);
			return buildErrorResponse(500, "XML Error: " + e.getMessage());
		} catch (Exception e) {
			log.error("General Exception: {}", e.getMessage(), e);
			return buildErrorResponse(500, "Error: " + e.getMessage());
		}
	}

	private ResponseEntity<String> buildErrorResponse(int statusCode, String errorMessage) {
		try {
			SetTransactionDownloadedResponseDTO errorResponse = new SetTransactionDownloadedResponseDTO();
			errorResponse.setErrorMessage(errorMessage);

			String xmlError = xmlUtil.convertToXmls(errorResponse);
			return ResponseEntity.status(statusCode).contentType(MediaType.APPLICATION_XML).body(xmlError);
		} catch (Exception ex) {
			log.error("Error serializing error response: {}", ex.getMessage(), ex);
			return ResponseEntity.status(500).contentType(MediaType.APPLICATION_XML).body(
					"<SetTransactionDownloadedResponseDTO><errorMessage>Internal server error</errorMessage></SetTransactionDownloadedResponseDTO>");
		}
	}

	private SetTransactionDownloadedRequest toSetTransactionDownloadedRequest(SetTransactionDownloadedRequestDTO dto) {
		SetTransactionDownloadedRequest request = new SetTransactionDownloadedRequest();
		request.setLogin(dto.getLogin());
		request.setPwd(dto.getPwd());
		request.setFileID(dto.getFileID());
		return request;
	}
}
