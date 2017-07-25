package com.companySearch.WsClient.SOAP;

import com.companySearch.WsClient.Request.Request;
import com.companySearch.WsClient.Response.Company;
import com.companySearch.WsClient.Response.SOAPResponseFormat;
import org.apache.commons.lang.StringEscapeUtils;
import org.w3c.dom.Node;

import javax.xml.soap.*;
import java.io.FileOutputStream;
import java.io.PrintStream;

/**
 * Created by Kamil Best on 19.07.2017.
 * SOAPAuthorizer class, making SOAP connection, calling requests and printing SOAP result.
 */
public class SOAPAuthorizer implements AuthorizerInterface {
    private String sessionID;
    private SOAPConnection soapConnection;
    private SOAPBody soapResultBody;
    private SOAPResponseFormat soapResponseFormat;

    public SOAPAuthorizer() throws SOAPException {
        SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
        //Creating SOAP connection
        soapConnection = soapConnectionFactory.createConnection();
        soapResponseFormat = new SOAPResponseFormat();
    }

    /**
     * takes SOAPResult from soapResponse and writes it to response.xml file
     *
     * @param soapResponse message from service
     * @throws Exception
     */
    private void takeSOAPResult(SOAPMessage soapResponse) throws Exception {
        soapResultBody = soapResponse.getSOAPBody();

        //Gets text content of soap response body to String
        String xmlDoc = soapResultBody.getTextContent();
        //Unescape html marks
        String decodedXML = StringEscapeUtils.unescapeHtml(xmlDoc);

        //save taken from soap response body and save it to file
        try (PrintStream out = new PrintStream(new FileOutputStream("response.xml"))) {
            out.print(decodedXML);
        }
    }

    public void formatData(boolean fullRaport) {
        soapResponseFormat.formatData(fullRaport);
    }

    /**
     * Set session ID from ZalogujResult element in soapResultBody
     */
    public void setSessionIDFromLoginRequestResponse() {
        Node loginResult = soapResultBody.getElementsByTagName("ZalogujResult").item(0);
        sessionID = loginResult.getTextContent();
    }

    /**
     * Takes regon from data search result (which is alredy taken to company field.
     *
     * @return String regon number
     */
    public String getRegonNumber() {
        return soapResponseFormat.getRegonNumber();
    }


    public Company getCompany() {
        return soapResponseFormat.getCompany();
    }

    public String getSessionID() {
        return sessionID;
    }


    /**
     * authorizes connection, call given request and print it
     *
     * @param request object
     * @throws Exception
     */
    @Override
    public void authorize(Request request) throws Exception {
        String serviceURI = "https://wyszukiwarkaregontest.stat.gov.pl/wsBIR/UslugaBIRzewnPubl.svc";
        //Call request and receive result from service
        SOAPMessage result = soapConnection.call(request.getSoapMessage(), serviceURI);
        takeSOAPResult(result);
    }
}
