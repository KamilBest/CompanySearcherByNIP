package com.companySearch.WsClient.Request;

import javax.xml.soap.*;

/**
 * Created by Kamil Best on 19.07.2017.
 */

public class DataSearchRequest extends Request {

    public DataSearchRequest(String sessionID) {
        this.sessionID = sessionID;
    }
    @Override
    protected void prepareSOAPEnvelope() throws Exception {
        SOAPEnvelope soapEnvelope = soapPart.getEnvelope();
        soapEnvelope.setPrefix("soap");  //to make sure prefix match
        soapEnvelope.addNamespaceDeclaration("dat", "http://CIS/BIR/PUBL/2014/07/DataContract");
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
        soapHeaderElement.addTextNode("http://CIS/BIR/PUBL/2014/07/IUslugaBIRzewnPubl/DaneSzukaj");
        soapHeaderElement1.addTextNode("https://wyszukiwarkaregontest.stat.gov.pl/wsBIR/UslugaBIRzewnPubl.svc");
    }

    @Override
    protected void prepareSOAPBody() throws SOAPException {

        SOAPBody soapBody = soapEnvelope.getBody();
        soapBody.setPrefix("soap");
        SOAPElement soapBodyElement = soapBody.addChildElement("DaneSzukaj", "ns");
        SOAPElement soapBodyElement1 = soapBodyElement.addChildElement("pParametryWyszukiwania", "ns");
        SOAPElement parametr = soapBodyElement1.addChildElement("Nip", "dat");
        parametr.addTextNode("9290016119");
    }

    @Override
    protected void prepareMimeHeaders() {

        headers.addHeader("SOAPAction", serverURI + "DaneSzukaj");
    }
}
