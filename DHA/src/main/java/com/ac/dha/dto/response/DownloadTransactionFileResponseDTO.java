package com.ac.dha.dto.response;
import java.util.Arrays;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import lombok.Data;

@XmlAccessorType(XmlAccessType.FIELD)
//@Data
public class DownloadTransactionFileResponseDTO {
	
	@XmlElement(name = "DownloadTransactionFileResult")
    private String downloadTransactionFileResult;

    @XmlElement(name = "FileName")
    private String fileName;

    @XmlElement(name = "File")
    private byte[] file;

    @XmlElement(name = "ErrorMessage")
    private String errorMessage;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public byte[] getFile() {
		return file;
	}

	public void setFile(byte[] file) {
		this.file = file;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public DownloadTransactionFileResponseDTO(String fileName, byte[] file, String errorMessage) {
		super();
		this.fileName = fileName;
		this.file = file;
		this.errorMessage = errorMessage;
	}

	@Override
	public String toString() {
		return "DownloadTransactionFileResponseDTO [fileName=" + fileName + ", file=" + Arrays.toString(file)
				+ ", errorMessage=" + errorMessage + "]";
	}
    
	public DownloadTransactionFileResponseDTO() {}
    
}
