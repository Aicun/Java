package com.lac.ws.client;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.xml.namespace.QName;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPHeaderElement;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.Dispatch;
import javax.xml.ws.Service;

import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;

import com.lac.ws.IUserService;
import com.lac.ws.User;
import com.lac.ws.UserRunTimeException_Exception;
import com.lac.ws.UserService;
import com.lac.ws.client.tool.HeaderTool;
import com.sun.xml.ws.api.message.Headers;
import com.sun.xml.ws.developer.WSBindingProvider;

public class TestService {
	private IUserService port;  
    private UserService us;  
    private String ns = "http://ws.lac.com";  
      
    @Before  
    public void init(){  
        try {  
            URL url = new URL("http://localhost:8001/JaxwsProject/us?wsdl");  
            QName name = new QName(ns,"UserService");  
            us = new UserService(url,name);  
            port = us.getUserServicePort();  
        } catch (MalformedURLException e) {  
            e.printStackTrace();  
        }  
    }  
      
    @Test  
    public void testList(){  
        List<User> list = port.list();  
        for (User user : list) {  
            System.out.println(user.getNickname());  
        }  
    }  
  
    @Test
    //using wsbindingprovider
    public void testAdd(){  
        try{  
            User user = new User();  
            user.setNickname("ËÑË÷");  
            user.setPassword("456123");  
            user.setUsername("ss");  
            Document doc = HeaderTool.addSoapHeader("admin", "admin");
            WSBindingProvider provider = (WSBindingProvider) port;
			provider.setOutboundHeaders(Headers.create(doc.getDocumentElement()));
            port.add(user);  
        }catch (UserRunTimeException_Exception e) {  
            System.out.println(e.getMessage());  
        }  
    }
    
    @Test
    //using handler
    public void testAdd2(){  
        try{  
            User user = new User();  
            user.setNickname("ËÑË÷");  
            user.setPassword("456123");  
            user.setUsername("ss");  
            //Document doc = HeaderTool.addSoapHeader("admin", "admin");
            //WSBindingProvider provider = (WSBindingProvider) port;
			//provider.setOutboundHeaders(Headers.create(doc.getDocumentElement()));
            port.add(user);  
        }catch (UserRunTimeException_Exception e) {  
            System.out.println(e.getMessage());  
        }  
    }
    
    @Test
    //using dispatch
    public void testAdd3(){  
    	QName name = new QName("http://ws.lac.com","userServicePort");
    	Dispatch<SOAPMessage> dispatch = us.createDispatch(name, SOAPMessage.class, Service.Mode.MESSAGE);
        
    	SOAPMessage message;
		try {
			message = MessageFactory.newInstance().createMessage();
			SOAPEnvelope envelope = message.getSOAPPart().getEnvelope();
			SOAPHeader header = envelope.getHeader();
			
			SOAPHeaderElement element = header.addHeaderElement(new QName("http://ws.lac.com","username"));
			element.setNodeValue("abc");
			
			dispatch.invoke(message);
			
			message.writeTo(System.out);
		} catch (SOAPException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
  
    @Test  
    public void testLogin(){  
        try{  
            User u = port.login("ss", "456123");  
            System.out.println(u.getNickname());  
        }catch (UserRunTimeException_Exception e) {  
            System.out.println(e.getMessage());  
        }  
    }  
  
    @Test  
    public void testDelete(){  
        port.delete("ss");  
    }  
}
