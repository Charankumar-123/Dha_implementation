package com.ac.dha.service;

import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
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
import com.ac.dha.dto.response.DownloadTransactionFileResponseDTO;
import com.ac.dha.dto.response.GetNewTransactionsResponseDTO;
import com.ac.dha.dto.response.GeteRxTransactionResponseDTO;
import com.ac.dha.dto.response.SearchTransactionsResponseDTO;
import com.ac.dha.dto.response.SetTransactionDownloadedResponseDTO;
import com.ac.dha.dto.response.UploadERxRequestResponseDTO;
import com.ac.dha.entities.DownloadTransactionFileRecord;
import com.ac.dha.entities.GetNewTransactionsRequest;
import com.ac.dha.entities.GeteRxTransactionRequestRecord;
import com.ac.dha.entities.PriorRequest;
import com.ac.dha.entities.SearchTransactionsRequest;
import com.ac.dha.entities.SetTransactionDownloadedRecord;
import com.ac.dha.entities.UploadErxRequest;
import com.ac.dha.repository.DownloadTransactionFileRepository;
import com.ac.dha.repository.EclaimRepository;
import com.ac.dha.repository.GetNewTransactionsRepository;
import com.ac.dha.repository.GeteRxTransactionRequestRepository;
import com.ac.dha.repository.SearchTransactionsRepository;
import com.ac.dha.repository.SetTransactionDownloadedRepository;
import com.ac.dha.repository.UploadErxRequestRepository;
import com.ac.dha.utils.ERXEntityMapper;
import com.ac.dha.utils.EclaimHttpResponse;
//import com.ac.dha.utils.RandomUIGenerator;
import com.ac.dha.utils.XmlUtil;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Unmarshaller;

@Service
public class EClaimService {
	public static final Logger log = LoggerFactory.getLogger(EClaimService.class);

	@Autowired
	private EclaimRepository eclaimRepository;
	
	@Autowired
	private GetNewTransactionsRepository getNewTransactionsRepository;
	
	@Autowired
	private GeteRxTransactionRequestRepository geteRxTransactionRequestRepository;
	
	@Autowired
	private SearchTransactionsRepository searchTransactionsRepository;
	
	@Autowired
	private DownloadTransactionFileRepository downloadTransactionFileRepository;
	
	@Autowired
	private SetTransactionDownloadedRepository setTransactionDownloadedRepository;

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
		record.setFacilityLogin(requestFromUser.getFacilityLogin());
		record.setFacilityPwd(requestFromUser.getFacilityPwd());
		record.setClinicianLogin(requestFromUser.getClinicianLogin());
		record.setClinicianPwd(requestFromUser.getClinicianPwd());
		record.setFileName(requestFromUser.getFileName());
		record.setUploadDate(LocalDateTime.now());

		try {
			// Convert DTO to XML
			byte[] xmlPayload = xmlUtil.convertToXml(requestFromUser.getPriorRequest());
			record.setFileContent(xmlPayload);

			// Prepare upload DTO
			UploadERxRequestDTO uploadDTO = new UploadERxRequestDTO();
			uploadDTO.setFacilityLogin(requestFromUser.getFacilityLogin());
			uploadDTO.setFacilityPwd(requestFromUser.getFacilityPwd());
			uploadDTO.setClinicianLogin(requestFromUser.getClinicianLogin());
			uploadDTO.setClinicianPwd(requestFromUser.getClinicianPwd());
			uploadDTO.setFileName(requestFromUser.getFileName());
			uploadDTO.setFileContent(xmlPayload);

			uploadErxRequestRepository.save(record);

			// Call webhook
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_XML);
			headers.setAccept(Collections.singletonList(MediaType.APPLICATION_XML));
			HttpEntity<UploadERxRequestDTO> requestEntity = new HttpEntity<>(uploadDTO, headers);

			ResponseEntity<String> response = restTemplate.postForEntity(eclaimUrl + "/uploadERxRequest", requestEntity,
					String.class);

			processResponse(record, response.getBody());
			record.setResponseStatus(response.getStatusCode().toString());
			uploadErxRequestRepository.save(record);

			return ResponseEntity.status(response.getStatusCode()).body(response.getBody());

		} catch (Exception e) {
			log.error("Upload ERx Request failed: {}", e.getMessage(), e);
			record.setResponseStatus("error");
			record.setErrorMessage("Error: " + e.getMessage());
			uploadErxRequestRepository.save(record);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
		}
	}

	private void processResponse(UploadErxRequest record, String responseBody) {
		String cleanedResponse = cleanXmlResponse(responseBody);

		// Try JAXB
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(UploadERxRequestResponseDTO.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			UploadERxRequestResponseDTO responseDTO = (UploadERxRequestResponseDTO) unmarshaller
					.unmarshal(new StringReader(cleanedResponse));

            record.seteRxReferenceNo(responseDTO.getERxReferenceNo());
			record.setErrorMessage(responseDTO.getErrorMessage());
			record.setErrorReport(responseDTO.getErrorReport());
			return;

		} catch (Exception ex) {
			log.warn("JAXB failed, trying manual XML parsing: {}", ex.getMessage());
		}

		// Fallback: Manual XML
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			factory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(new InputSource(new StringReader(cleanedResponse)));
			doc.getDocumentElement().normalize();

			NodeList refNoNode = doc.getElementsByTagName("ERxReferenceNo");
//            if (refNoNode.getLength() > 0) {
//                record.seteRxReferenceNo(Integer.parseInt(refNoNode.item(0).getTextContent().trim()));
//            }

			NodeList errorMsgNode = doc.getElementsByTagName("ErrorMessage");
			if (errorMsgNode.getLength() > 0) {
				record.setErrorMessage(errorMsgNode.item(0).getTextContent().trim());
			}

			NodeList errorReportNode = doc.getElementsByTagName("ErrorReport");
			if (errorReportNode.getLength() > 0) {
				record.setErrorReport(errorReportNode.item(0).getTextContent().getBytes(StandardCharsets.UTF_8));
			}

		} catch (Exception e) {
			log.error("Manual XML parsing failed", e);
			record.setErrorMessage("Failed to parse response: " + e.getMessage());
		}
	}

	private String cleanXmlResponse(String xml) {
		if (xml == null)
			return "";
		String cleaned = xml.replaceAll("^[\\W]+<\\?xml", "<?xml");
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
	
	
	
	@Transactional
    public ResponseEntity<String> getNewTransactions(GetNewTransactionsRequestDTO requestDTO) {
        if (requestDTO == null) {
            throw new IllegalArgumentException("Missing requestDTO");
        }

        GetNewTransactionsRequest record = new GetNewTransactionsRequest();
        record.setLogin(requestDTO.getLogin());
        record.setPwd(requestDTO.getPwd());
        record.setRequestedAt(LocalDateTime.now());

        try {
            // 1. Convert DTO to XML string
            byte[] xmlPayload = xmlUtil.convertToXml(requestDTO);
            String xmlString = new String(xmlPayload, StandardCharsets.UTF_8);
            record.setXmlTransactions(xmlString);

            // 2. Prepare HTTP request
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_XML);
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_XML));
            HttpEntity<String> requestEntity = new HttpEntity<>(xmlString, headers);

            // 3. Send to external webhook
            ResponseEntity<String> response = restTemplate.postForEntity(
                    eclaimUrl + "/getNewTransactions", requestEntity, String.class);

            String responseBody = response.getBody();
            log.info("Received response:\n{}", responseBody);

            // 4. Process response
            processResponse(record, responseBody);
            record.setResponseStatus(response.getStatusCode().toString());

            getNewTransactionsRepository.save(record);
            return ResponseEntity.status(response.getStatusCode()).body(responseBody);

        } catch (Exception e) {
            log.error("GetNewTransactions failed: {}", e.getMessage(), e);
            record.setResponseStatus("error");
            record.setErrorMessage("Error: " + e.getMessage());
            getNewTransactionsRepository.save(record);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }

    private void processResponse(GetNewTransactionsRequest record, String responseBody) {
        String cleanedResponse = cleanXmlResponse(responseBody);

        // Try JAXB parsing
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(GetNewTransactionsResponseDTO.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            GetNewTransactionsResponseDTO responseDTO = (GetNewTransactionsResponseDTO)
                    unmarshaller.unmarshal(new StringReader(cleanedResponse));

            record.setXmlTransactions(responseDTO.getXmlTransactions());
            record.setErrorMessage(responseDTO.getErrorMessage());
            return;
        } catch (Exception ex) {
            log.warn("JAXB failed, trying fallback XML: {}", ex.getMessage());
        }
    }

        // Fallback XML parsing
//        try {
//            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//            factory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
//            DocumentBuilder builder = factory.newDocumentBuilder();
//            Document doc = builder.parse(new InputSource(new StringReader(cleanedResponse)));
//            doc.getDocumentElement().normalize();
//
//            NodeList xmlNode = doc.getElementsByTagName("XmlTransactions");
//            if (xmlNode.getLength() > 0) {
//                record.setXmlTransactions(xmlNode.item(0).getTextContent().trim());
//            }
//
//            NodeList errorMsgNode = doc.getElementsByTagName("ErrorMessage");
//            if (errorMsgNode.getLength() > 0) {
//                record.setErrorMessage(errorMsgNode.item(0).getTextContent().trim());
//            }
//
//        } catch (Exception e) {
//            log.error("Fallback XML parsing failed", e);
//            record.setErrorMessage("Failed to parse response: " + e.getMessage());
//        }
//    }

//    private String cleanXmlResponse2(String xml) {
//        if (xml == null)
//            return "";
//        String cleaned = xml.replaceAll("^[\\W]+<\\?xml", "<?xml");
//        if (!cleaned.startsWith("<?xml")) {
//            cleaned = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + cleaned;
//        }
//        return cleaned;
//    }


//	public ResponseEntity<String> getNewTransactions(GetNewTransactionsRequestDTO dto) {
//		try {
//			byte[] xmlPayload = xmlUtil.convertToXml(dto);
//			System.out.println("xmlPayload=>"+xmlPayload.toString());
//			HttpHeaders headers = new HttpHeaders();
//			headers.setContentType(MediaType.APPLICATION_XML);
//			headers.set("login", dto.getLogin());
//			headers.set("pwd", dto.getPwd());
//			HttpEntity<byte[]> requestEntity = new HttpEntity<>(xmlPayload, headers);
//
//			ResponseEntity<String> response = restTemplate.postForEntity(eclaimUrl + "/GetNewTransactions",
//					requestEntity, String.class);
//
//			return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
//		} catch (Exception e) {
//			return ResponseEntity.status(500).body("Error: " + e.getMessage());
//		}
//	}
    
    
    
    
    
    @Transactional
    public ResponseEntity<String> geteRxTransaction(GeteRxTransactionRequestDTO dto) {
        if (dto == null) {
            throw new IllegalArgumentException("Missing request DTO");
        }

        // 1. Create and populate DB record
        GeteRxTransactionRequestRecord record = new GeteRxTransactionRequestRecord();
        record.setLogin(dto.getLogin());
        record.setPwd(dto.getPwd());
        record.setMemberID(dto.getMemberID());
        record.seteRxReferenceNo(dto.geteRxReferenceNo());
        record.setRequestedAt(LocalDateTime.now());

        try {
            // 2. Convert DTO to XML
            byte[] xmlPayload = xmlUtil.convertToXml(dto);
            String xmlString = new String(xmlPayload, StandardCharsets.UTF_8);
            record.setXmlTransactions(xmlString);  // Save request as XML

            // 3. Prepare HTTP request
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_XML);
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_XML));
            HttpEntity<String> requestEntity = new HttpEntity<>(xmlString, headers);

            // 4. Send request to DHPO API
            ResponseEntity<String> response = restTemplate.postForEntity(
                eclaimUrl + "/GeteRxTransaction", requestEntity, String.class);

            String responseBody = response.getBody();
            log.info("Received GeteRxTransaction response:\n{}", responseBody);

            // 5. Process response (parse XML)
            processGeteRxTransactionResponse(record, responseBody);
            record.setResponseStatus(response.getStatusCode().toString());

            // 6. Save request/response to database
            geteRxTransactionRequestRepository.save(record);

            return ResponseEntity.status(response.getStatusCode()).body(responseBody);

        } catch (Exception e) {
            log.error("GeteRxTransaction failed: {}", e.getMessage(), e);
            record.setResponseStatus("ERROR");
            record.setErrorMessage("Exception: " + e.getMessage());
            geteRxTransactionRequestRepository.save(record);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }
    private void processGeteRxTransactionResponse(GeteRxTransactionRequestRecord record, String responseBody) {
        String cleanedResponse = cleanXmlResponse(responseBody); // optional if DHA wraps XML with headers

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(GeteRxTransactionResponseDTO.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            GeteRxTransactionResponseDTO responseDTO =
                (GeteRxTransactionResponseDTO) unmarshaller.unmarshal(new StringReader(cleanedResponse));

            record.setXmlTransactions(responseDTO.getXmlTransactions());
            record.setErrorMessage(responseDTO.getErrorMessage());

        } catch (Exception e) {
            log.warn("Fallback XML parsing failed: {}", e.getMessage());
            record.setErrorMessage("Parsing failed: " + e.getMessage());
        }
    }




//	public ResponseEntity<String> geteRxTransaction(GeteRxTransactionRequestDTO dto) {
//
//		try {
//			byte[] xmlPayload = xmlUtil.convertToXml(dto);
//			HttpHeaders headers = new HttpHeaders();
//			headers.set("login", dto.getLogin());
//			headers.set("pwd", dto.getPwd());
//			headers.set("memberID", String.valueOf(dto.getMemberID()));
//			headers.set("eRxReferenceNo", String.valueOf(dto.geteRxReferenceNo()));
//
//			headers.setContentType(MediaType.APPLICATION_XML);
//			HttpEntity<byte[]> requestEntity = new HttpEntity<>(xmlPayload, headers);
//
//			ResponseEntity<String> response = restTemplate.postForEntity(eclaimUrl + "/GeteRxTransaction",
//					requestEntity, String.class);
//
//			return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
//		} catch (Exception e) {
//			return ResponseEntity.status(500).body("Error: " + e.getMessage());
//		}
//	}
    
    
    
    
    @Transactional
    public ResponseEntity<String> searchTransactions(SearchTransactionsRequestDTO dto) {
        SearchTransactionsRequest record = new SearchTransactionsRequest();

        // Map DTO to entity fields
        record.setLogin(dto.getLogin());
        record.setPwd(dto.getPwd());
        record.setDirection(dto.getDirection());
        record.setCallerLicense(dto.getCallerLicense());
        record.setClinicianLicense(dto.getClinicianLicense());
        record.setMemberID(dto.getMemberID());
        record.setERxReferenceNo(dto.geteRxReferenceNo());
        record.setTransactionStatus(dto.getTransactionStatus());

        // Dates might be String or LocalDateTime in DTO — adjust accordingly
        record.setTransactionFromDate(dto.getTransactionFromDate());
        record.setTransactionToDate(dto.getTransactionToDate());

        record.setMinRecordCount(dto.getMinRecordCount());
        record.setMaxRecordCount(dto.getMaxRecordCount());
        record.setRequestedAt(LocalDateTime.now());

        try {
            // Convert DTO to XML payload
            byte[] xmlPayload = xmlUtil.convertToXml(dto);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_XML);

            HttpEntity<byte[]> requestEntity = new HttpEntity<>(xmlPayload, headers);

            // Call external DHA API
            ResponseEntity<String> response = restTemplate.postForEntity(
                    eclaimUrl + "/SearchTransactions", requestEntity, String.class);

            String responseBody = response.getBody();
            record.setResponseStatus(response.getStatusCode().toString());

            // Store raw request XML and response XML
            record.setFoundTransactions(responseBody);

            // You can parse the response XML into a response DTO if you have one,
            // similar to your other methods, to extract errorMessage if needed.
            try {
                JAXBContext jaxbContext = JAXBContext.newInstance(SearchTransactionsResponseDTO.class);
                Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
                SearchTransactionsResponseDTO responseDTO = (SearchTransactionsResponseDTO)
                        unmarshaller.unmarshal(new StringReader(responseBody));

                record.setFoundTransactions(responseDTO.getFoundTransactions());
                record.setErrorMessage(responseDTO.getErrorMessage());

            } catch (Exception ex) {
                log.warn("Failed to parse SearchTransactions response XML: {}", ex.getMessage());
                record.setErrorMessage("Parsing failed: " + ex.getMessage());
            }

            // Save the record
            searchTransactionsRepository.save(record);

            // Return the raw response back to the caller
            return ResponseEntity.status(response.getStatusCode()).body(responseBody);

        } catch (Exception e) {
            log.error("searchTransactions failed: {}", e.getMessage(), e);
            record.setResponseStatus("ERROR");
            record.setErrorMessage("Exception: " + e.getMessage());
            searchTransactionsRepository.save(record);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }


//	public ResponseEntity<String> searchTransactions(SearchTransactionsRequestDTO dto) {
//		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
//		try {
//			byte[] xmlPayload = xmlUtil.convertToXml(dto);
//			HttpHeaders headers = new HttpHeaders();
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
//			headers.setContentType(MediaType.APPLICATION_XML);
//			HttpEntity<byte[]> requestEntity = new HttpEntity<>(xmlPayload, headers);
//
//			ResponseEntity<String> response = restTemplate.postForEntity(eclaimUrl + "/SearchTransactions",
//					requestEntity, String.class);
//
//			return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
//		} catch (Exception e) {
//			return ResponseEntity.status(500).body("Error: " + e.getMessage());
//		}
//	}
    
    
    
    
    @Transactional
    public ResponseEntity<String> downloadTransactionFile(DownloadTransactionFileRequestDTO dto) {
        DownloadTransactionFileRecord record = new DownloadTransactionFileRecord();
        record.setLogin(dto.getLogin());
        record.setPwd(dto.getPwd());
        record.setFileID(dto.getFileID());
        record.setRequestedAt(LocalDateTime.now());

        try {
            // Convert DTO to XML bytes
            byte[] xmlPayload = xmlUtil.convertToXml(dto);

            // Prepare HTTP headers and entity
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_XML);
            HttpEntity<byte[]> requestEntity = new HttpEntity<>(xmlPayload, headers);

            // Call external DHA web service
            ResponseEntity<String> response = restTemplate.postForEntity(
                    eclaimUrl + "/DownloadTransactionFile", requestEntity, String.class);

            String responseBody = response.getBody();
            record.setResponseStatus(response.getStatusCode().toString());

            // Parse response XML with JAXB
            try {
                JAXBContext jaxbContext = JAXBContext.newInstance(DownloadTransactionFileResponseDTO.class);
                Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
                DownloadTransactionFileResponseDTO responseDTO = (DownloadTransactionFileResponseDTO)
                        unmarshaller.unmarshal(new StringReader(responseBody));

                record.setFileName(responseDTO.getFileName());
                record.setFile(responseDTO.getFile());
                record.setErrorMessage(responseDTO.getErrorMessage());

            } catch (Exception e) {
                log.warn("Failed to parse DownloadTransactionFile response XML: {}", e.getMessage());
                record.setErrorMessage("XML parsing error: " + e.getMessage());
            }

            // Save record to DB
            downloadTransactionFileRepository.save(record);
            

            // Return original response
            return ResponseEntity.status(response.getStatusCode()).body(responseBody);

        } catch (Exception e) {
            record.setResponseStatus("ERROR");
            record.setErrorMessage("Exception: " + e.getMessage());
            downloadTransactionFileRepository.save(record);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }


//	public ResponseEntity<String> downloadTransactionFile(DownloadTransactionFileRequestDTO dto) {
//		try {
//			byte[] xmlPayload = xmlUtil.convertToXml(dto);
//			HttpHeaders headers = new HttpHeaders();
//			headers.setContentType(MediaType.APPLICATION_XML);
//			HttpEntity<byte[]> requestEntity = new HttpEntity<>(xmlPayload, headers);
//
//			ResponseEntity<String> response = restTemplate.postForEntity(eclaimUrl + "/DownloadTransactionFile",
//					requestEntity, String.class);
//
//			return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
//		} catch (Exception e) {
//			return ResponseEntity.status(500).body("Error: " + e.getMessage());
//		}
//	}
    
    
    
    
    @Transactional
    public ResponseEntity<String> setTransactionDownloaded(SetTransactionDownloadedRequestDTO dto) {
        SetTransactionDownloadedRecord record = new SetTransactionDownloadedRecord();
        record.setLogin(dto.getLogin());
        record.setPwd(dto.getPwd());
        record.setFileID(dto.getFileID());
        record.setRequestedAt(LocalDateTime.now());

        try {
            // Convert DTO to XML
            byte[] xmlPayload = xmlUtil.convertToXml(dto);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_XML);
            HttpEntity<byte[]> requestEntity = new HttpEntity<>(xmlPayload, headers);

            // Call external DHPO/eRx web service
            ResponseEntity<String> response = restTemplate.postForEntity(
                eclaimUrl + "/SetTransactionDownloaded", requestEntity, String.class);

            String responseBody = response.getBody();
            record.setResponseStatus(response.getStatusCode().toString());

            // Parse response XML
            try {
                JAXBContext jaxbContext = JAXBContext.newInstance(SetTransactionDownloadedResponseDTO.class);
                Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
                SetTransactionDownloadedResponseDTO responseDTO = (SetTransactionDownloadedResponseDTO)
                    unmarshaller.unmarshal(new StringReader(responseBody));

                record.setErrorMessage(responseDTO.getErrorMessage());

            } catch (Exception e) {
                log.warn("Failed to parse SetTransactionDownloaded response XML: {}", e.getMessage());
                record.setErrorMessage("Parsing failed: " + e.getMessage());
            }

            // Persist record
            setTransactionDownloadedRepository.save(record);
            

            return ResponseEntity.status(response.getStatusCode()).body(responseBody);

        } catch (Exception e) {
            log.error("Exception in setTransactionDownloaded: {}", e.getMessage(), e);
            record.setResponseStatus("ERROR");
            record.setErrorMessage("Exception: " + e.getMessage());
            setTransactionDownloadedRepository.save(record);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }


//	public ResponseEntity<String> setTransactionDownloaded(SetTransactionDownloadedRequestDTO dto) {
//		try {
//			byte[] xmlPayload = xmlUtil.convertToXml(dto);
//			HttpHeaders headers = new HttpHeaders();
//			headers.setContentType(MediaType.APPLICATION_XML);
//			HttpEntity<byte[]> requestEntity = new HttpEntity<>(xmlPayload, headers);
//
//			ResponseEntity<String> response = restTemplate.postForEntity(eclaimUrl + "/SetTransactionDownloaded",
//					requestEntity, String.class);
//
//			return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
//		} catch (Exception e) {
//			return ResponseEntity.status(500).body("Error: " + e.getMessage());
//		}
//	}

}
