package com.ac.dha.utils;

import java.io.ByteArrayOutputStream;
import java.io.StringReader;
import java.io.StringWriter;

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

	public static <T> T fromXml(String xml, Class<T> clazz) throws JAXBException {
		try {
			JAXBContext context = JAXBContext.newInstance(clazz);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			return (T) unmarshaller.unmarshal(new StringReader(xml));
		} catch (JAXBException e) {
			System.err.println("Failed to unmarshal XML to " + clazz.getSimpleName());
			System.err.println("XML Content: \n" + xml);
			throw e;
		}
	}
	
	public String convertToXmls(Object object) throws JAXBException {
	    StringWriter sw = new StringWriter();
	    JAXBContext context = JAXBContext.newInstance(object.getClass());
	    Marshaller marshaller = context.createMarshaller();
	    marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
	    marshaller.marshal(object, sw);
	    return sw.toString();
	}
	
	public String convertToXmlss(Object object, Class<?> clazz) throws JAXBException {
	    StringWriter sw = new StringWriter();
	    JAXBContext context = JAXBContext.newInstance(clazz);
	    Marshaller marshaller = context.createMarshaller();
	    marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
	    marshaller.marshal(object, sw);
	    return sw.toString();
	}


}
