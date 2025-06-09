package com.ac.dha.service;

import java.nio.charset.StandardCharsets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ac.dha.controller.EClaimController;
import com.ac.dha.dto.response.UploadERxAuthorizationResponseDTO;
import com.ac.dha.dto.response.UploadERxRequestResponseDTO;
import com.ac.dha.utils.XmlUtil;

import jakarta.xml.bind.JAXBException;

@Component
public class ExternalServiceClient {

	public static final Logger logger = LoggerFactory.getLogger(EClaimController.class);

	@Autowired
	private XmlUtil xmlUtil;

	public String callUploadERxAuthorization(String payerLogin, String payerPwd, byte[] fileContent, String fileName)
			throws JAXBException {
		if (fileContent == null) {
			throw new IllegalArgumentException("fileContent cannot be null");
		}
		if (fileName == null) {
			throw new IllegalArgumentException("fileName cannot be null");
		}

		UploadERxAuthorizationResponseDTO responseDTO = new UploadERxAuthorizationResponseDTO();
		responseDTO.setPayerLogin(payerLogin != null ? payerLogin : "defaultUser");
		responseDTO.setPayerPwd(payerPwd != null ? payerPwd : "defaultPass");
		responseDTO.setFileContent(fileContent);
		responseDTO.setFileName(fileName);

		byte[] xmlBytes = xmlUtil.convertToXml(responseDTO);
		if (xmlBytes == null || xmlBytes.length == 0) {
			throw new JAXBException("Failed to generate XML response");
		}
		String xmlResponse = new String(xmlBytes, StandardCharsets.UTF_8);
		logger.info("Generated XML Response: " + xmlResponse);

		if (!xmlResponse.startsWith("<?xml")) {
			throw new JAXBException("Invalid XML generated: " + xmlResponse);
		}

		return xmlResponse;
	}

	public String callUploadERxResponse(Integer eRxReferenceNo, String errorMessage, String errorReport)
			throws JAXBException {

		UploadERxRequestResponseDTO responseDTO = new UploadERxRequestResponseDTO();

		responseDTO.seteRxReferenceNo(eRxReferenceNo != null ? eRxReferenceNo.toString() : "0");
		responseDTO.setErrorMessage(errorMessage);
		responseDTO.setErrorReport(errorReport != null ? errorReport.getBytes(StandardCharsets.UTF_8) : null);

		byte[] xmlBytes = xmlUtil.convertToXml(responseDTO);
		if (xmlBytes == null || xmlBytes.length == 0) {
			throw new JAXBException("Failed to generate XML response");
		}
		String xmlResponse = new String(xmlBytes, StandardCharsets.UTF_8);
		logger.info("Generated XML Response: " + xmlResponse);

		if (!xmlResponse.startsWith("<?xml")) {
			throw new JAXBException("Invalid XML generated: " + xmlResponse);
		}

		return xmlResponse;
	}
	
	

}