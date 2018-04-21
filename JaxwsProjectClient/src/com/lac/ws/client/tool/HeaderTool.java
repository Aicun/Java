package com.lac.ws.client.tool;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;

public class HeaderTool {

	public static Document addSoapHeader(String username, String password) {
		try {

			JAXBContext ctx = JAXBContext.newInstance(LicenceInfo.class);
			LicenceInfo header = new LicenceInfo();
			header.setUsername(username);
			header.setPassword(password);
			QName name = new QName("soaReqHeader");
			JAXBElement<LicenceInfo> jele = new JAXBElement<LicenceInfo>(name,
					LicenceInfo.class, header);
			Marshaller mars = ctx.createMarshaller();
			mars.setProperty(Marshaller.JAXB_FRAGMENT, true);
			mars.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");

			// 2¡¢×ª»»ÎªDOM
			Document doc = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder().newDocument();
			mars.marshal(jele, doc);

			return doc;
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
