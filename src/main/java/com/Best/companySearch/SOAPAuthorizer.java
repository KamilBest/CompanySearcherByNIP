package com.Best.companySearch;

import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;

/**
 * Created by Kamil Best on 19.07.2017.
 * SOAPAuthorizer class, making SOAP connection, calling requests and printing SOAP result.
 */
public class SOAPAuthorizer implements Authorizer {
    private SOAPConnectionFactory soapConnectionFactory;
    private SOAPConnection soapConnection;

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
        Source sourceContent = soapResponse.getSOAPPart().getContent();
        System.out.print(title);
        StreamResult result = new StreamResult(System.out);
        transformer.transform(sourceContent, result);
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
        SOAPMessage result = soapConnection.call(request.constructRequestMessage(), serviceURI);
        printSOAPResult(result, "Result SOAPMessage: ");
    }
}
