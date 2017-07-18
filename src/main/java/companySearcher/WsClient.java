package companySearcher;

import javax.xml.soap.*;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;

/**
 * Created by Kamil Best on 14.07.2017.
 */
public class WsClient {

    private static SOAPMessage getRequestMessage() throws Exception {
        //SOAP message factory instance (PROTOCOL: SOAP_1_2_PROTOCOL)
        MessageFactory messageFactory = MessageFactory.newInstance(SOAPConstants.SOAP_1_2_PROTOCOL);

        //Creating SOAP message
        SOAPMessage soapMessage = messageFactory.createMessage();

        //Creating SOAP part
        SOAPPart soapPart = soapMessage.getSOAPPart();

        //Creating SOAP envelope
        String serverURI = "http://CIS/BIR/PUBL/2014/07";
        SOAPEnvelope soapEnvelope = soapPart.getEnvelope();
        soapEnvelope.addNamespaceDeclaration("ns", serverURI);
        soapEnvelope.setPrefix("soap");  //to make sure prefix match
        soapEnvelope.addNamespaceDeclaration("ns", serverURI);
        soapEnvelope.removeNamespaceDeclaration("env");

        //Creating SOAP header
        String headerURI = "http://www.w3.org/2005/08/addressing";
        SOAPHeader soapHeader = soapEnvelope.getHeader();
        soapHeader.setPrefix("soap");//to make sure prefix match
        soapHeader.addNamespaceDeclaration("wsa", headerURI);
        SOAPElement soapHeaderElement = soapHeader.addChildElement("Action", "wsa");
        SOAPElement soapHeaderElement1 = soapHeader.addChildElement("To", "wsa");
        soapHeaderElement.addTextNode("http://CIS/BIR/PUBL/2014/07/IUslugaBIRzewnPubl/Zaloguj");
        soapHeaderElement1.addTextNode("https://wyszukiwarkaregontest.stat.gov.pl/wsBIR/UslugaBIRzewnPubl.svc");

        //Gain and modify SOAP body
        SOAPBody soapBody = soapEnvelope.getBody();
        soapBody.setPrefix("soap");
        SOAPElement soapBodyElement = soapBody.addChildElement("Zaloguj", "ns");
        SOAPElement soapBodyElement1 = soapBodyElement.addChildElement("pKluczUzytkownika", "ns");
        soapBodyElement1.addTextNode("abcde12345abcde12345");

        //Gain and modify mime headers
        MimeHeaders headers = soapMessage.getMimeHeaders();
        headers.addHeader("SOAPAction", serverURI + "Zaloguj");

        //Save SOAP message changes
        soapMessage.saveChanges();

        //Print request
        System.out.print("Request SOAPMessage: ");
        soapMessage.writeTo(System.out);
        System.out.println();

        return soapMessage;
    }

    private static void printSOAPResult(SOAPMessage soapResponse, String title) throws Exception {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        Source sourceContent = soapResponse.getSOAPPart().getContent();
        System.out.print(title);
        StreamResult result = new StreamResult(System.out);
        transformer.transform(sourceContent, result);
//        JSONObject xmlJSONObj = XML.toJSONObject(sw.toString());
//        return xmlJSONObj.toString(2);
    }

    public static void soapTest() throws Exception {
        //SOAP connection factory instance
        SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
        //Creating SOAP connection
        SOAPConnection soapConnection = soapConnectionFactory.createConnection();

        //Call request and receive result from service
        SOAPMessage result = soapConnection.call(getRequestMessage(), "https://wyszukiwarkaregontest.stat.gov.pl/wsBIR/UslugaBIRzewnPubl.svc");

        printSOAPResult(result, "Result SOAPMessage: ");
    }

    public static void main(String[] args) {

        try {
            soapTest();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
