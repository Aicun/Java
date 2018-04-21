
package com.lac.ws;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.namespace.QName;
import javax.xml.soap.SOAPException;
import javax.xml.ws.BindingProvider;

import org.w3c.dom.Document;

import com.lac.util.WebUtil;
import com.sun.xml.ws.api.message.Headers;
import com.sun.xml.ws.developer.WSBindingProvider;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="args" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "args"
})
@XmlRootElement(name = "main")
public class Main {

    @XmlElement(nillable = true)
    protected List<String> args;

    /**
     * Gets the value of the args property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the args property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getArgs().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getArgs() {
        if (args == null) {
            args = new ArrayList<String>();
        }
        return this.args;
    }
    
    public static void main(String args[]) throws MalformedURLException, SOAPException {
    	URL wsdlLoc = new URL("http://172.168.1.201:8080/axis2/services/DMSService");
		QName serviceName = new QName("http://service.hao24.dms", "DMSService");
    	DMSService service = new DMSService(wsdlLoc,serviceName);
    	
    /*	
    	Dispatch<SOAPMessage> dispatch = service.createDispatch(new QName("http://service.hao24.dms", "DMSService"), SOAPMessage.class, Service.Mode.MESSAGE);
    	
    	
    	 SOAPMessage msg=MessageFactory.newInstance().createMessage();  
         SOAPEnvelope envelope =msg.getSOAPPart().getEnvelope();  
         SOAPHeader header=  envelope.getHeader();
         SOAPBody body=envelope.getBody();
    	
    	*/
    	String endpointURL = "http://localhost:7001/ld/proxy/ldbizproxy";
    	Document doc = WebUtil.addLicenceHeader();
    	DMSServicePortType  port = service.getDMSServiceHttpSoap11Endpoint();
    	WSBindingProvider provider = (WSBindingProvider)port;
    	//WSBindingProvider provider = (WSBindingProvider)port;
    	provider.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endpointURL);
    	provider.setOutboundHeaders(Headers.create(doc.getDocumentElement()));
    	String ss = port.requestOrderStateToLD("123");
    	System.out.println(ss);
    }

}
