package companySearcher;


import javax.xml.namespace.QName;
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
        //TODO check if prefix match
        //TODO addNamespaceDeclaration


        //Creating SOAP header
        String headerURI = "http://www.w3.org/2005/08/addressing";
        SOAPHeader soapHeader = soapEnvelope.getHeader();
        soapHeader.addNamespaceDeclaration("wsa", headerURI);
        QName action = new QName("http://CIS/BIR/PUBL/2014/07/IUslugaBIRzewnPubl/Zaloguj", "Action", "wsa");
        SOAPElement soapHeaderElement = soapHeader.addHeaderElement(action);
        QName to = new QName("https://wyszukiwarkaregontest.stat.gov.pl/wsBIR/UslugaBIRzewnPubl.svc", "To", "wsa");
        SOAPElement soapHeaderElement1 = soapHeader.addHeaderElement(to);


        /*
        SOAPElement soapHeaderElement=soapHeader.addHeaderElement(action);
        QName to=new QName("https://wyszukiwarkaregontest.stat.gov.pl/wsBIR/UslugaBIRzewnPubl.svc","To","wsa");
        SOAPElement soapHeaderElement1=soapHeader.addHeaderElement(to);
        */
        //TODO check if prefix match
        //TODO addNamespaceDeclaration
        //TODO add soap elements for header



             /*
        Zaloguj Request
           <!-- Zaloguj ------------------------------------------------------------->
    <soap:Envelope xmlns:soap="http://www.w3.org/2003/05/soap-envelope" xmlns:ns="http://CIS/BIR/PUBL/2014/07">
    <soap:Header xmlns:wsa="http://www.w3.org/2005/08/addressing">
    <wsa:Action>http://CIS/BIR/PUBL/2014/07/IUslugaBIRzewnPubl/Zaloguj</wsa:Action>
    <wsa:To>https://wyszukiwarkaregontest.stat.gov.pl/wsBIR/UslugaBIRzewnPubl.svc</wsa:To>
    </soap:Header>
       <soap:Body>
          <ns:Zaloguj>
             <ns:pKluczUzytkownika>theUsersKey</ns:pKluczUzytkownika>
          </ns:Zaloguj>
       </soap:Body>
    </soap:Envelope>
    <!-- ------------------------------------------------------------->

         */
        //Gain and modify SOAP body
        SOAPBody soapBody = soapEnvelope.getBody();

        //TODO check if prefix match
        //TODO add soap elements for body (request params)


        SOAPElement soapBodyElement = soapBody.addChildElement("Zaloguj", "ns");
        SOAPElement soapBodyElement1 = soapBodyElement.addChildElement("pKluczUzytkownika", "ns");
        soapBodyElement1.addTextNode("abcde12345abcde12345");

        //Gain and modify mime headers
        MimeHeaders headers = soapMessage.getMimeHeaders();
        //TODO pass your header URI
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

        //Call request and receive result
        //TODO pass your URL to service
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
