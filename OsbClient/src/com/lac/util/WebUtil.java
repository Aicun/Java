package com.lac.util;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;

public class WebUtil {

	public static Document addLicenceHeader() {
		try {
			// 1����һ������ת��Ϊxmlͨ��JAXB
			JAXBContext ctx = JAXBContext.newInstance(OSBHeader.class);
			OSBHeader header = new OSBHeader();
			header.setAppCode("123");
			header.setTransId("123456578");
			QName name = new QName("http://ws.lac.com","soaReqHeader");
			JAXBElement<OSBHeader> jele = new JAXBElement<OSBHeader>(name,
					OSBHeader.class, header);
			Marshaller mars = ctx.createMarshaller();
			mars.setProperty(Marshaller.JAXB_FRAGMENT, true);
			mars.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");

			// 2��ת��ΪDOM
			Document doc = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder().newDocument();
			mars.marshal(jele, doc);

			return doc;
			/*
			 * //3��ͨ��Headers.create�������header����� //��ȡWSBindingProvider
			 * WSBindingProvider wsb = (WSBindingProvider)port;
			 * wsb.setOutboundHeaders(Headers.create(doc.getDocumentElement()));
			 */
		} catch (PropertyException e) {
			e.printStackTrace();
		} catch (JAXBException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String args[]) {
		addLicenceHeader();
	}
}
