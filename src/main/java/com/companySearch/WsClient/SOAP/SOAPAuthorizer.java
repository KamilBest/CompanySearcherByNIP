package com.companySearch.WsClient.SOAP;

import com.companySearch.WsClient.Request.Request;
import org.apache.commons.lang.StringEscapeUtils;
import org.w3c.dom.Node;

import javax.xml.soap.*;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;

/**
 * Created by Kamil Best on 19.07.2017.
 * SOAPAuthorizer class, making SOAP connection, calling requests and printing SOAP result.
 */
public class SOAPAuthorizer implements AuthorizerInterface {
    String sessionID;
    private SOAPConnectionFactory soapConnectionFactory;
    private SOAPConnection soapConnection;
    private SOAPBody soapResultBody;

    public SOAPAuthorizer() throws SOAPException {
        soapConnectionFactory = SOAPConnectionFactory.newInstance();
        //Creating SOAP connection
        soapConnection = soapConnectionFactory.createConnection();
    }


    /**
     * Prints SOAPResult from soapResponse
     *
     * @param soapResponse message from service
     * @param title        printed result title
     * @throws Exception
     */
    private void printSOAPResult(SOAPMessage soapResponse, String title) throws Exception {

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
        final Source soapContent = soapResponse.getSOAPPart().getContent();
        System.out.println(title + ": ");
        final StreamResult result = new StreamResult(System.out); //print
        //  new StreamResult(new File("response.xml") write response to file
        transformer.transform(soapContent, result);
        soapResultBody = soapResponse.getSOAPBody();

    }

    /**
     * Set session ID from ZalogujResult element in soapResultBody
     */
    public void setSessionIDFromLoginRequestResponse() {
        Node loginResult = soapResultBody.getElementsByTagName("ZalogujResult").item(0);
        sessionID = loginResult.getTextContent();
    }

    /**
     * Takes data from DaneSzukajResult
     */
    public void getDataSearchResult() {
        System.out.println();
        Node returnList = soapResultBody.getElementsByTagName("DaneSzukajResult").item(0);
        String data = returnList.getChildNodes().item(0).toString();
        System.out.println(data);
        String decodedXML = StringEscapeUtils.unescapeHtml(data);
        System.out.println(decodedXML);


    }


    public String getSessionID() {
        return sessionID;
    }


    /**
     * authorizes connection, call given request and print it
     *
     * @param request
     * @throws Exception
     */
    @Override
    public void authorize(Request request) throws Exception {
        String serviceURI = "https://wyszukiwarkaregontest.stat.gov.pl/wsBIR/UslugaBIRzewnPubl.svc";
        //Call request and receive result from service
        SOAPMessage result = soapConnection.call(request.getSoapMessage(), serviceURI);
        printSOAPResult(result, "Result SOAPMessage: ");
    }
}
