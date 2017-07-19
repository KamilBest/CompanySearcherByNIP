package com.companySearch.WsClient.Request;

import javax.xml.soap.*;

/**
 * Created by Kamil Best on 19.07.2017.
 */
public class LogoutRequest extends Request {
    public LogoutRequest(String sessionID) {
        this.sessionID = sessionID;
    }

    @Override
    protected void prepareSOAPEnvelope() throws Exception {
        SOAPEnvelope soapEnvelope = soapPart.getEnvelope();
        soapEnvelope.setPrefix("soap");  //to make sure prefix match
        soapEnvelope.addNamespaceDeclaration("ns", serverURI);
        soapEnvelope.removeNamespaceDeclaration("env");
    }

    @Override
    protected void prepareSOAPHeader() throws SOAPException {
        String headerURI = "http://www.w3.org/2005/08/addressing";
        SOAPHeader soapHeader = soapEnvelope.getHeader();
        soapHeader.setPrefix("soap");//to make sure prefix match
        soapHeader.addNamespaceDeclaration("wsa", headerURI);
        SOAPElement soapHeaderElement = soapHeader.addChildElement("Action", "wsa");
        SOAPElement soapHeaderElement1 = soapHeader.addChildElement("To", "wsa");
        soapHeaderElement.addTextNode("http://CIS/BIR/PUBL/2014/07/IUslugaBIRzewnPubl/Wyloguj");
        soapHeaderElement1.addTextNode("https://wyszukiwarkaregontest.stat.gov.pl/wsBIR/UslugaBIRzewnPubl.svc");
    }

    @Override
    protected void prepareSOAPBody() throws SOAPException {
        SOAPBody soapBody = soapEnvelope.getBody();
        soapBody.setPrefix("soap");
        SOAPElement soapBodyElement = soapBody.addChildElement("Wyloguj", "ns");
        SOAPElement soapBodyElement1 = soapBodyElement.addChildElement("pIdentyfikatorSesji", "ns");
        soapBodyElement1.addTextNode(sessionID);
    }

    @Override
    protected void prepareMimeHeaders() {
        MimeHeaders headers = soapMessage.getMimeHeaders();
        headers.addHeader("SOAPAction", serverURI + "Wyloguj");
    }
}
