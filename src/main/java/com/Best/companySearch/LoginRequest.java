package com.Best.companySearch;

import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;

/**
 * Created by Kamil Best on 18.07.2017.
 */
final class LoginRequest extends Request {
    @Override
    protected void prepareSOAPEnvelope() throws Exception {

        soapEnvelope.addNamespaceDeclaration("ns", serverURI);
        soapEnvelope.setPrefix("soap");  //to make sure prefix match
        soapEnvelope.addNamespaceDeclaration("ns", serverURI);
        soapEnvelope.removeNamespaceDeclaration("env");
    }

    @Override
    protected void prepareSOAPHeader() throws SOAPException {
        String headerURI = "http://www.w3.org/2005/08/addressing";

        soapHeader.setPrefix("soap");//to make sure prefix match
        soapHeader.addNamespaceDeclaration("wsa", headerURI);
        SOAPElement soapHeaderElement = soapHeader.addChildElement("Action", "wsa");
        SOAPElement soapHeaderElement1 = soapHeader.addChildElement("To", "wsa");
        soapHeaderElement.addTextNode("http://CIS/BIR/PUBL/2014/07/IUslugaBIRzewnPubl/Zaloguj");
        soapHeaderElement1.addTextNode("https://wyszukiwarkaregontest.stat.gov.pl/wsBIR/UslugaBIRzewnPubl.svc");
    }

    @Override
    protected void prepareSOAPBody() throws SOAPException {

        soapBody.setPrefix("soap");
        SOAPElement soapBodyElement = soapBody.addChildElement("Zaloguj", "ns");
        SOAPElement soapBodyElement1 = soapBodyElement.addChildElement("pKluczUzytkownika", "ns");
        soapBodyElement1.addTextNode("abcde12345abcde12345");
    }

    @Override
    protected void prepareMimeHeaders() {

        headers.addHeader("SOAPAction", serverURI + "Zaloguj");
    }
}
