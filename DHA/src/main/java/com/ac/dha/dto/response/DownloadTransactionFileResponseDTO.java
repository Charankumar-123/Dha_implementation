package com.ac.dha.dto.response;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import lombok.Data;

@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class DownloadTransactionFileResponseDTO {

    @XmlElement(name = "FileName")
    private String fileName;

    @XmlElement(name = "File")
    private byte[] file;

    @XmlElement(name = "ErrorMessage")
    private String errorMessage;
}
