package com.companySearch.WsClient.Request;

import javax.xml.soap.*;


/**
 * Created by Kamil Best on 18.07.2017.
 * Request abstact class
 * to construct all types of requests
 */
public abstract class Request implements RequestInterface {
    String sessionID = "";
    SOAPEnvelope soapEnvelope;
    SOAPBody soapBody;
    /**
     * determines action name
     */
    String actionName = "";
    /**
     * determines if DataContract namespace is needed
     */
    boolean dataContract = false;
    private String serverURI = "http://CIS/BIR/PUBL/2014/07";
    private SOAPMessage soapMessage;
    private SOAPPart soapPart;
    private SOAPHeader soapHeader;
    private MimeHeaders headers;

    private void prepareSOAPEnvelope(boolean dataContract) throws Exception {
        soapEnvelope.addNamespaceDeclaration("ns", "http://CIS/BIR/PUBL/2014/07");
        soapEnvelope.setPrefix("soap");  //to make sure prefix match
        soapEnvelope.addNamespaceDeclaration("ns", "http://CIS/BIR/PUBL/2014/07");
        soapEnvelope.removeNamespaceDeclaration("env");
        if (dataContract)
            soapEnvelope.addNamespaceDeclaration("dat", "http://CIS/BIR/PUBL/2014/07/DataContract");
    }

    private void prepareSOAPHeader(String actionName) throws SOAPException {
        String headerURI = "http://www.w3.org/2005/08/addressing";
        soapHeader.setPrefix("soap");//to make sure prefix match
        soapHeader.addNamespaceDeclaration("wsa", headerURI);
        SOAPElement soapHeaderElement = soapHeader.addChildElement("Action", "wsa");
        SOAPElement soapHeaderElement1 = soapHeader.addChildElement("To", "wsa");
        soapHeaderElement.addTextNode("http://CIS/BIR/PUBL/2014/07/IUslugaBIRzewnPubl/" + actionName);
        soapHeaderElement1.addTextNode("https://wyszukiwarkaregontest.stat.gov.pl/wsBIR/UslugaBIRzewnPubl.svc");
    }

    protected abstract void prepareSOAPBody() throws SOAPException;

    private void prepareMimeHeaders(String actionName) {
        headers.addHeader("SOAPAction", serverURI + actionName + sessionID);
        headers.addHeader("SID", sessionID);
    }

    /**
     * Taking prepared SOAP parts (Envelope, Header etc...) and construct request message to send.
     *
     * @return constructed SOAP message
     * @throws Exception
     */
    public void constructRequestMessage() throws Exception {
        //SOAP message factory instance (PROTOCOL: SOAP_1_2_PROTOCOL)
        MessageFactory messageFactory = MessageFactory.newInstance(SOAPConstants.SOAP_1_2_PROTOCOL);

        //Creating SOAP message
        soapMessage = messageFactory.createMessage();

        //Creating SOAP part
        soapPart = soapMessage.getSOAPPart();

        //Creating SOAP envelope
        soapEnvelope = soapPart.getEnvelope();
        prepareSOAPEnvelope(dataContract);

        //Creating SOAP header
        soapHeader = soapEnvelope.getHeader();
        prepareSOAPHeader(actionName);

        //Gain and modify SOAP body
        soapBody = soapEnvelope.getBody();
        prepareSOAPBody();

        //Gain and modify mime headers
        headers = soapMessage.getMimeHeaders();
        prepareMimeHeaders(actionName);

        //Save SOAP message changes
        soapMessage.saveChanges();

        //Print request
        /*
        System.out.print("Request SOAPMessage: ");
        soapMessage.writeTo(System.out);
        System.out.println();
        */

    }

    public SOAPMessage getSoapMessage() {
        return soapMessage;
    }
}

