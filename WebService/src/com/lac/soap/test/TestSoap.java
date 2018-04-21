package com.lac.soap.test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPBodyElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import javax.xml.ws.Dispatch;
import javax.xml.ws.Service;

import org.junit.Test;
import org.w3c.dom.Document;

public class TestSoap {

	String ns = "http://www.lac.com/webservice";  
    String wsdlUrl = "http://localhost:8989/ms?wsdl";
	
	@Test
	public void test01(){
		try {
			MessageFactory factory = MessageFactory.newInstance();
			SOAPMessage message = factory.createMessage();
			SOAPPart part = message.getSOAPPart();
			SOAPEnvelope envelope = part.getEnvelope();
			SOAPBody body = envelope.getBody();
			SOAPHeader header = envelope.getHeader();
			QName name = new QName("ns");
			body.addBodyElement(name).setValue("1234");
			body.addBodyElement(name).setValue("<a>2</a><a>2</a>");  
           //SOAPBodyElement ele = body.addBodyElement(name);  
           // ele.addChildElement("a").setValue("11");  
           // ele.addChildElement("b").setValue("22");  
            message.writeTo(System.out);  
		} catch (SOAPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void test02(){
		try {
			URL url = new URL(wsdlUrl);
			Service service = Service.create(url,new QName(ns,"myservice"));
			
			Dispatch<SOAPMessage> dispatch = service.createDispatch(new QName(ns,"myserviceport"),
					SOAPMessage.class, Service.Mode.MESSAGE);
			
			SOAPMessage message = MessageFactory.newInstance().createMessage();
			SOAPEnvelope envelope  = message.getSOAPPart().getEnvelope();
			SOAPBody body = envelope.getBody();  
			
			QName qname = new QName(ns,"ns","nn");
			SOAPBodyElement ele = body.addBodyElement(qname);
			
			ele.addChildElement("a").setValue("22");  
            ele.addChildElement("b").setValue("33");
            
            message.writeTo(System.out);
            
            
            SOAPMessage response = dispatch.invoke(message);
            response.writeTo(System.out);
            
            //将响应的消息转换为dom对象  
            Document doc = response.getSOAPPart().getEnvelope().getBody().extractContentAsDocument();  
            String str = doc.getElementsByTagName("add").item(0).getTextContent();
            System.out.println("\n"+str); 
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (SOAPException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
