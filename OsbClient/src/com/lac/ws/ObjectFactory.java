
package com.lac.ws;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.lac package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _RequestSendADebitToLDResponseReturn_QNAME = new QName("http://service.hao24.dms", "return");
    private final static QName _RequestSendReplCostBillToLDCS_QNAME = new QName("http://service.hao24.dms", "CS");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.lac
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link RequestSendADebitToLDResponse }
     * 
     */
    public RequestSendADebitToLDResponse createRequestSendADebitToLDResponse() {
        return new RequestSendADebitToLDResponse();
    }

    /**
     * Create an instance of {@link RequestSendReplCostBillToLD }
     * 
     */
    public RequestSendReplCostBillToLD createRequestSendReplCostBillToLD() {
        return new RequestSendReplCostBillToLD();
    }

    /**
     * Create an instance of {@link RequestSendRunCostBillToLD }
     * 
     */
    public RequestSendRunCostBillToLD createRequestSendRunCostBillToLD() {
        return new RequestSendRunCostBillToLD();
    }

    /**
     * Create an instance of {@link RequestOrderStateToLDResponse }
     * 
     */
    public RequestOrderStateToLDResponse createRequestOrderStateToLDResponse() {
        return new RequestOrderStateToLDResponse();
    }

    /**
     * Create an instance of {@link Main }
     * 
     */
    public Main createMain() {
        return new Main();
    }

    /**
     * Create an instance of {@link RequestSendKSErrorToLD }
     * 
     */
    public RequestSendKSErrorToLD createRequestSendKSErrorToLD() {
        return new RequestSendKSErrorToLD();
    }

    /**
     * Create an instance of {@link RequestOrderStateToLD }
     * 
     */
    public RequestOrderStateToLD createRequestOrderStateToLD() {
        return new RequestOrderStateToLD();
    }

    /**
     * Create an instance of {@link RequestOrderFlowToLDResponse }
     * 
     */
    public RequestOrderFlowToLDResponse createRequestOrderFlowToLDResponse() {
        return new RequestOrderFlowToLDResponse();
    }

    /**
     * Create an instance of {@link RequestSendRunCostBillToLDResponse }
     * 
     */
    public RequestSendRunCostBillToLDResponse createRequestSendRunCostBillToLDResponse() {
        return new RequestSendRunCostBillToLDResponse();
    }

    /**
     * Create an instance of {@link RequestDelveryInfoToLD }
     * 
     */
    public RequestDelveryInfoToLD createRequestDelveryInfoToLD() {
        return new RequestDelveryInfoToLD();
    }

    /**
     * Create an instance of {@link RequestSendADebitToLD }
     * 
     */
    public RequestSendADebitToLD createRequestSendADebitToLD() {
        return new RequestSendADebitToLD();
    }

    /**
     * Create an instance of {@link RequestDelveryInfoToLDResponse }
     * 
     */
    public RequestDelveryInfoToLDResponse createRequestDelveryInfoToLDResponse() {
        return new RequestDelveryInfoToLDResponse();
    }

    /**
     * Create an instance of {@link RequestSendTReplCostBillToLDResponse }
     * 
     */
    public RequestSendTReplCostBillToLDResponse createRequestSendTReplCostBillToLDResponse() {
        return new RequestSendTReplCostBillToLDResponse();
    }

    /**
     * Create an instance of {@link RequestOrderFlowToLD }
     * 
     */
    public RequestOrderFlowToLD createRequestOrderFlowToLD() {
        return new RequestOrderFlowToLD();
    }

    /**
     * Create an instance of {@link RequestSendErrorBillToLDResponse }
     * 
     */
    public RequestSendErrorBillToLDResponse createRequestSendErrorBillToLDResponse() {
        return new RequestSendErrorBillToLDResponse();
    }

    /**
     * Create an instance of {@link RequestSendErrorBillToLD }
     * 
     */
    public RequestSendErrorBillToLD createRequestSendErrorBillToLD() {
        return new RequestSendErrorBillToLD();
    }

    /**
     * Create an instance of {@link RequestSendErrorToLD }
     * 
     */
    public RequestSendErrorToLD createRequestSendErrorToLD() {
        return new RequestSendErrorToLD();
    }

    /**
     * Create an instance of {@link RequestSendKSErrorToLDResponse }
     * 
     */
    public RequestSendKSErrorToLDResponse createRequestSendKSErrorToLDResponse() {
        return new RequestSendKSErrorToLDResponse();
    }

    /**
     * Create an instance of {@link RequestSendRewardBillToLDResponse }
     * 
     */
    public RequestSendRewardBillToLDResponse createRequestSendRewardBillToLDResponse() {
        return new RequestSendRewardBillToLDResponse();
    }

    /**
     * Create an instance of {@link RequestSendReplCostBillToLDResponse }
     * 
     */
    public RequestSendReplCostBillToLDResponse createRequestSendReplCostBillToLDResponse() {
        return new RequestSendReplCostBillToLDResponse();
    }

    /**
     * Create an instance of {@link RequestSendTReplCostBillToLD }
     * 
     */
    public RequestSendTReplCostBillToLD createRequestSendTReplCostBillToLD() {
        return new RequestSendTReplCostBillToLD();
    }

    /**
     * Create an instance of {@link RequestSendRewardBillToLD }
     * 
     */
    public RequestSendRewardBillToLD createRequestSendRewardBillToLD() {
        return new RequestSendRewardBillToLD();
    }

    /**
     * Create an instance of {@link RequestSendErrorToLDResponse }
     * 
     */
    public RequestSendErrorToLDResponse createRequestSendErrorToLDResponse() {
        return new RequestSendErrorToLDResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.hao24.dms", name = "return", scope = RequestSendADebitToLDResponse.class)
    public JAXBElement<String> createRequestSendADebitToLDResponseReturn(String value) {
        return new JAXBElement<String>(_RequestSendADebitToLDResponseReturn_QNAME, String.class, RequestSendADebitToLDResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.hao24.dms", name = "CS", scope = RequestSendReplCostBillToLD.class)
    public JAXBElement<String> createRequestSendReplCostBillToLDCS(String value) {
        return new JAXBElement<String>(_RequestSendReplCostBillToLDCS_QNAME, String.class, RequestSendReplCostBillToLD.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.hao24.dms", name = "CS", scope = RequestSendRunCostBillToLD.class)
    public JAXBElement<String> createRequestSendRunCostBillToLDCS(String value) {
        return new JAXBElement<String>(_RequestSendReplCostBillToLDCS_QNAME, String.class, RequestSendRunCostBillToLD.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.hao24.dms", name = "return", scope = RequestOrderStateToLDResponse.class)
    public JAXBElement<String> createRequestOrderStateToLDResponseReturn(String value) {
        return new JAXBElement<String>(_RequestSendADebitToLDResponseReturn_QNAME, String.class, RequestOrderStateToLDResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.hao24.dms", name = "CS", scope = RequestSendKSErrorToLD.class)
    public JAXBElement<String> createRequestSendKSErrorToLDCS(String value) {
        return new JAXBElement<String>(_RequestSendReplCostBillToLDCS_QNAME, String.class, RequestSendKSErrorToLD.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.hao24.dms", name = "CS", scope = RequestOrderStateToLD.class)
    public JAXBElement<String> createRequestOrderStateToLDCS(String value) {
        return new JAXBElement<String>(_RequestSendReplCostBillToLDCS_QNAME, String.class, RequestOrderStateToLD.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.hao24.dms", name = "return", scope = RequestOrderFlowToLDResponse.class)
    public JAXBElement<String> createRequestOrderFlowToLDResponseReturn(String value) {
        return new JAXBElement<String>(_RequestSendADebitToLDResponseReturn_QNAME, String.class, RequestOrderFlowToLDResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.hao24.dms", name = "return", scope = RequestSendRunCostBillToLDResponse.class)
    public JAXBElement<String> createRequestSendRunCostBillToLDResponseReturn(String value) {
        return new JAXBElement<String>(_RequestSendADebitToLDResponseReturn_QNAME, String.class, RequestSendRunCostBillToLDResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.hao24.dms", name = "CS", scope = RequestDelveryInfoToLD.class)
    public JAXBElement<String> createRequestDelveryInfoToLDCS(String value) {
        return new JAXBElement<String>(_RequestSendReplCostBillToLDCS_QNAME, String.class, RequestDelveryInfoToLD.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.hao24.dms", name = "CS", scope = RequestSendADebitToLD.class)
    public JAXBElement<String> createRequestSendADebitToLDCS(String value) {
        return new JAXBElement<String>(_RequestSendReplCostBillToLDCS_QNAME, String.class, RequestSendADebitToLD.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.hao24.dms", name = "return", scope = RequestDelveryInfoToLDResponse.class)
    public JAXBElement<String> createRequestDelveryInfoToLDResponseReturn(String value) {
        return new JAXBElement<String>(_RequestSendADebitToLDResponseReturn_QNAME, String.class, RequestDelveryInfoToLDResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.hao24.dms", name = "return", scope = RequestSendTReplCostBillToLDResponse.class)
    public JAXBElement<String> createRequestSendTReplCostBillToLDResponseReturn(String value) {
        return new JAXBElement<String>(_RequestSendADebitToLDResponseReturn_QNAME, String.class, RequestSendTReplCostBillToLDResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.hao24.dms", name = "CS", scope = RequestOrderFlowToLD.class)
    public JAXBElement<String> createRequestOrderFlowToLDCS(String value) {
        return new JAXBElement<String>(_RequestSendReplCostBillToLDCS_QNAME, String.class, RequestOrderFlowToLD.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.hao24.dms", name = "return", scope = RequestSendErrorBillToLDResponse.class)
    public JAXBElement<String> createRequestSendErrorBillToLDResponseReturn(String value) {
        return new JAXBElement<String>(_RequestSendADebitToLDResponseReturn_QNAME, String.class, RequestSendErrorBillToLDResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.hao24.dms", name = "CS", scope = RequestSendErrorBillToLD.class)
    public JAXBElement<String> createRequestSendErrorBillToLDCS(String value) {
        return new JAXBElement<String>(_RequestSendReplCostBillToLDCS_QNAME, String.class, RequestSendErrorBillToLD.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.hao24.dms", name = "CS", scope = RequestSendErrorToLD.class)
    public JAXBElement<String> createRequestSendErrorToLDCS(String value) {
        return new JAXBElement<String>(_RequestSendReplCostBillToLDCS_QNAME, String.class, RequestSendErrorToLD.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.hao24.dms", name = "return", scope = RequestSendKSErrorToLDResponse.class)
    public JAXBElement<String> createRequestSendKSErrorToLDResponseReturn(String value) {
        return new JAXBElement<String>(_RequestSendADebitToLDResponseReturn_QNAME, String.class, RequestSendKSErrorToLDResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.hao24.dms", name = "return", scope = RequestSendRewardBillToLDResponse.class)
    public JAXBElement<String> createRequestSendRewardBillToLDResponseReturn(String value) {
        return new JAXBElement<String>(_RequestSendADebitToLDResponseReturn_QNAME, String.class, RequestSendRewardBillToLDResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.hao24.dms", name = "return", scope = RequestSendReplCostBillToLDResponse.class)
    public JAXBElement<String> createRequestSendReplCostBillToLDResponseReturn(String value) {
        return new JAXBElement<String>(_RequestSendADebitToLDResponseReturn_QNAME, String.class, RequestSendReplCostBillToLDResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.hao24.dms", name = "CS", scope = RequestSendTReplCostBillToLD.class)
    public JAXBElement<String> createRequestSendTReplCostBillToLDCS(String value) {
        return new JAXBElement<String>(_RequestSendReplCostBillToLDCS_QNAME, String.class, RequestSendTReplCostBillToLD.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.hao24.dms", name = "CS", scope = RequestSendRewardBillToLD.class)
    public JAXBElement<String> createRequestSendRewardBillToLDCS(String value) {
        return new JAXBElement<String>(_RequestSendReplCostBillToLDCS_QNAME, String.class, RequestSendRewardBillToLD.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.hao24.dms", name = "return", scope = RequestSendErrorToLDResponse.class)
    public JAXBElement<String> createRequestSendErrorToLDResponseReturn(String value) {
        return new JAXBElement<String>(_RequestSendADebitToLDResponseReturn_QNAME, String.class, RequestSendErrorToLDResponse.class, value);
    }

}
