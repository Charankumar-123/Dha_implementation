package com.ac.dha.utils;

import java.io.ByteArrayOutputStream;

import org.springframework.stereotype.Component;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;

@Component
public class XmlUtil {

	public <T> byte[] convertToXml(T requestObject) throws JAXBException {
		JAXBContext context = JAXBContext.newInstance(requestObject.getClass());
		Marshaller marshaller = context.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		marshaller.marshal(requestObject, baos);
		return baos.toByteArray();
	}
}
