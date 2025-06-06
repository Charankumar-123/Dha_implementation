package com.ac.dha.utils;

import java.io.ByteArrayOutputStream;
import java.io.StringReader;

import org.springframework.stereotype.Component;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

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
	
	@SuppressWarnings("unchecked")
	public static <T> T fromXml(String xml, Class<T> clazz) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(clazz);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        return (T) unmarshaller.unmarshal(new StringReader(xml));
    }
}
