package com.ac.dha.service;

import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

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
import com.ac.dha.entities.PriorRequest;
import com.ac.dha.entities.UploadErxRequest;
import com.ac.dha.repository.EclaimRepository;
import com.ac.dha.repository.UploadErxRequestRepository;
import com.ac.dha.utils.ERXEntityMapper;
import com.ac.dha.utils.EclaimHttpResponse;
//import com.ac.dha.utils.RandomUIGenerator;
import com.ac.dha.utils.XmlUtil;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;



@Service
public class EClaimService {
	public static final Logger log = LoggerFactory.getLogger(EClaimService.class);

	@Autowired
	private EclaimRepository eclaimRepository;

	@Autowired
	private ERXEntityMapper entityMapper;
	
//	@Autowired
//	private RandomUIGenerator randomUIGenerator;
	
	@Autowired
	private UploadErxRequestRepository uploadErxRequestRepository;

	private final RestTemplate restTemplate = new RestTemplate();

	@Value("${eclaim.endpoint.url}")
	private String eclaimUrl;

	@Autowired
	private XmlUtil xmlUtil;

	@Autowired
	private EclaimHttpResponse eclaimHttpResponse; // work and test during API testing

//	@Autowired
//	private AuthorizationRepository authorizationRepository;
//	
//	@Autowired
//	private HeaderRepository headerRepository;

	public ResponseEntity<String> sendPriorRequestToEclaim(ErxRequestDTO priorRequest) {
		try {
			log.info("Req Format ", priorRequest.getAuthorization());
			log.debug("XML Format ", xmlUtil.convertToXml(priorRequest));
			byte[] xmlPayload = xmlUtil.convertToXml(priorRequest);
			log.debug("XML Payload byte[] length: ", xmlPayload.length);
			String xmlString = new String(xmlPayload, StandardCharsets.UTF_8);
			log.debug("XML Payload as String:\n", xmlString);

			// Convert DTO to Entity and Save to Database
			PriorRequest entity = entityMapper.toPriorRequest(priorRequest);
			eclaimRepository.save(entity);
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
	
	
	@Transactional
	public ResponseEntity<String> uploadERxRequest(UploadERxRequestForUserDTO requestFromUser) {
	    if (requestFromUser == null || requestFromUser.getPriorRequest() == null) {
	        throw new IllegalArgumentException("Missing request or priorRequest");
	    }

	    UploadErxRequest record = new UploadErxRequest();

	    try {
	        // Convert DTO to XML
	        byte[] xmlPayload = xmlUtil.convertToXml(requestFromUser.getPriorRequest());

	        // Prepare DTO for request
	        UploadERxRequestDTO uploadDTO = new UploadERxRequestDTO();
	        uploadDTO.setFacilityLogin(requestFromUser.getFacilityLogin());
	        uploadDTO.setFacilityPwd(requestFromUser.getFacilityPwd());
	        uploadDTO.setClinicianLogin(requestFromUser.getClinicianLogin());
	        uploadDTO.setClinicianPwd(requestFromUser.getClinicianPwd());
	        uploadDTO.setFileName(requestFromUser.getFileName());
	        uploadDTO.setFileContent(xmlPayload);

	        // Fill entity details
	        record.setFacilityLogin(requestFromUser.getFacilityLogin());
	        record.setFacilityPwd(requestFromUser.getFacilityPwd());
	        record.setClinicianLogin(requestFromUser.getClinicianLogin());
	        record.setClinicianPwd(requestFromUser.getClinicianPwd());
	        record.setFileName(requestFromUser.getFileName());
	        record.setFileContent(xmlPayload);
	        record.setUploadDate(LocalDateTime.now());

	        // Save initial record
	        uploadErxRequestRepository.save(record);

	        // Prepare headers and call webhook
	        HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.APPLICATION_XML);
	        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_XML));
	        HttpEntity<UploadERxRequestDTO> requestEntity = new HttpEntity<>(uploadDTO, headers);

	        ResponseEntity<String> response = restTemplate.postForEntity(
	                eclaimUrl + "/uploadERxRequest",
	                requestEntity,
	                String.class
	        );

	        // Clean the response body before parsing
	        String cleanedResponse = cleanXmlResponse(response.getBody());
	        
	        // Try unmarshalling with JAXB
	        try {
	            JAXBContext jaxbContext = JAXBContext.newInstance(UploadERxRequestResponseDTO.class);
	            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
	            UploadERxRequestResponseDTO responseDTO = (UploadERxRequestResponseDTO)
	                    unmarshaller.unmarshal(new StringReader(cleanedResponse));

	            record.seteRxReferenceNo(responseDTO.getERxReferenceNo());
	            record.setErrorMessage(responseDTO.getErrorMessage());
	            record.setErrorReport(responseDTO.getErrorReport());

	        } catch (Exception ex) {
	            log.warn("JAXB unmarshalling failed, trying manual XML parse", ex);
	            try {
	                // Fallback manual XML parse
	                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	                // Prevent XXE attacks
	                factory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
	                DocumentBuilder builder = factory.newDocumentBuilder();
	                Document doc = builder.parse(new InputSource(new StringReader(cleanedResponse)));
	                doc.getDocumentElement().normalize();

	                NodeList eRxRefNodes = doc.getElementsByTagName("ERxReferenceNo");
	                if (eRxRefNodes.getLength() > 0) {
	                    String refNoText = eRxRefNodes.item(0).getTextContent().trim();
	                    if (!refNoText.isEmpty()) {
	                        record.seteRxReferenceNo(Integer.parseInt(refNoText));
	                    }
	                }
	                
	                NodeList errorMessageNodes = doc.getElementsByTagName("ErrorMessage");
	                if (errorMessageNodes.getLength() > 0) {
	                    record.setErrorMessage(errorMessageNodes.item(0).getTextContent().trim());
	                }
	                
	                NodeList errorReportNodes = doc.getElementsByTagName("ErrorReport");
	                if (errorReportNodes.getLength() > 0) {
	                    String errorReport = errorReportNodes.item(0).getTextContent().trim();
	                    if (!errorReport.isEmpty()) {
	                        record.setErrorReport(errorReport.getBytes(StandardCharsets.UTF_8));
	                    }
	                }
	            } catch (Exception e) {
	                log.error("Manual XML parsing also failed", e);
	                record.setErrorMessage("Failed to parse response: " + e.getMessage());
	            }
	        }

	        record.setResponseStatus(response.getStatusCode().toString());
	        uploadErxRequestRepository.save(record);

	        return ResponseEntity.status(response.getStatusCode()).body(response.getBody());

	    } catch (Exception e) {
	        log.error("Upload ERx Request failed: {}", e.getMessage(), e);

	        record.setResponseStatus("error");
	        record.setUploadDate(LocalDateTime.now());
	        record.setErrorMessage("Error: " + e.getMessage());
	        uploadErxRequestRepository.save(record);

	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
	    }
	}

	private String cleanXmlResponse(String xml) {
	    if (xml == null) {
	        return "";
	    }
	    // Remove any BOM or other non-XML content before the prolog
	    String cleaned = xml.replaceAll("^[\\W]+<\\?xml", "<?xml");
	    // Ensure it starts with <?xml
	    if (!cleaned.startsWith("<?xml")) {
	        cleaned = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + cleaned;
	    }
	    return cleaned;
	}


	

//	public ResponseEntity<String> uploadERxRequest(UploadERxRequestForUserDTO requestFromUser) {
//		try {
//			byte[] xmlPayload = xmlUtil.convertToXml(requestFromUser.getPriorRequest());
//			UploadERxRequestDTO request = new UploadERxRequestDTO();
//			request.setClinicianLogin(requestFromUser.getClinicianLogin());
//			request.setClinicianPwd(requestFromUser.getClinicianPwd());
//			request.setFacilityLogin(requestFromUser.getFacilityLogin());
//			request.setFacilityPwd(requestFromUser.getFacilityPwd());
//			request.setFileName(requestFromUser.getFileName());
//			request.setFileContent(xmlPayload);
////			request.setPriorRequest(null);
//			HttpHeaders headers = new HttpHeaders();
//			headers.setContentType(MediaType.APPLICATION_XML);
//
//			HttpEntity<UploadERxRequestDTO> requestEntity = new HttpEntity<>(request, headers);
//
//			ResponseEntity<String> response = restTemplate.postForEntity(eclaimUrl + "/uploadERxRequest", requestEntity,
//					String.class);
//			return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
//		} catch (Exception e) {
//			return ResponseEntity.status(500).body("Error: " + e.getMessage());
//		}
//	}

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
