package com.lac.ws.handler;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConstants;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPFault;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import javax.xml.ws.soap.SOAPFaultException;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class HeaderHandler implements SOAPHandler<SOAPMessageContext> {

	@Override
	public boolean handleMessage(SOAPMessageContext context) {
		System.out.println("Server : handleMessage()......");

		Boolean isRequest = (Boolean) context
				.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);

		// for response message only, true for outbound messages, false for
		// inbound
		if (!isRequest) {

			try {
				SOAPMessage soapMsg = context.getMessage();
				SOAPEnvelope soapEnv = soapMsg.getSOAPPart().getEnvelope();
				SOAPHeader soapHeader = soapEnv.getHeader();

				// if no header, add one
				if (soapHeader == null) {
					soapHeader = soapEnv.addHeader();
					// throw exception
					generateSOAPErrMessage(soapMsg, "No SOAP header.");
				}

				NodeList list = soapHeader.getChildNodes();
				printNode(list);
				// if no header block for next actor found? throw exception
				
			} catch (SOAPException e) {
				System.err.println(e);
			}

		}

		// continue other handler chain
		return true;
	}

	@Override
	public boolean handleFault(SOAPMessageContext context) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void close(MessageContext context) {
		// TODO Auto-generated method stub

	}

	@Override
	public Set<QName> getHeaders() {
		// TODO Auto-generated method stub
		return null;
	}

	private void generateSOAPErrMessage(SOAPMessage msg, String reason) {
		try {
			SOAPBody soapBody = msg.getSOAPPart().getEnvelope().getBody();
			SOAPFault soapFault = soapBody.addFault();
			soapFault.setFaultString(reason);
			throw new SOAPFaultException(soapFault);
		} catch (SOAPException e) {
		}
	}
	
	private void printNode(NodeList list) {
		for(int i=0;i<list.getLength();i++){
			Node n = list.item(i);
			if(n.hasChildNodes()){
				NodeList l = n.getChildNodes();
				printNode(l);
			}else if(n.getNodeType()==Node.TEXT_NODE) {
				if(n.getNodeName().equals("username")||n.getNodeName().equals("password")){
					System.out.println(n.getNodeValue());
				}
			}
		}
	}
}
