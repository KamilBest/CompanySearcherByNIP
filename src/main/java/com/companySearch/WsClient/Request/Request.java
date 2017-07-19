package com.companySearch.WsClient.Request;

import javax.xml.soap.*;


/**
 * Created by Kamil Best on 18.07.2017.
 * Request abstact class
 * to construct all types of requests
 */
public abstract class Request implements RequestInterface {
    protected final String serverURI = "http://CIS/BIR/PUBL/2014/07";
    protected String sessionID = "";
    protected SOAPMessage soapMessage;
    protected SOAPPart soapPart;
    protected SOAPEnvelope soapEnvelope;
    protected SOAPHeader soapHeader;
    protected SOAPBody soapBody;
    protected MimeHeaders headers;

    protected abstract void prepareSOAPEnvelope() throws Exception;

    protected abstract void prepareSOAPHeader() throws SOAPException;

    protected abstract void prepareSOAPBody() throws SOAPException;

    protected abstract void prepareMimeHeaders();

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
        prepareSOAPEnvelope();

        //Creating SOAP header
        soapHeader = soapEnvelope.getHeader();
        prepareSOAPHeader();

        //Gain and modify SOAP body
        soapBody = soapEnvelope.getBody();
        prepareSOAPBody();

        //Gain and modify mime headers
        headers = soapMessage.getMimeHeaders();
        prepareMimeHeaders();

        //Save SOAP message changes
        soapMessage.saveChanges();

        //Print request
        System.out.print("Request SOAPMessage: ");
        soapMessage.writeTo(System.out);
        System.out.println();

    }

    public SOAPMessage getSoapMessage() {
        return soapMessage;
    }
}

