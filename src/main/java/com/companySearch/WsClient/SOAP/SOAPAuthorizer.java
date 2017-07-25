package com.companySearch.WsClient.SOAP;

import com.companySearch.WsClient.Request.Request;
import com.companySearch.WsClient.Response.Company;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringEscapeUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.*;
import java.io.*;

/**
 * Created by Kamil Best on 19.07.2017.
 * SOAPAuthorizer class, making SOAP connection, calling requests and printing SOAP result.
 */
public class SOAPAuthorizer implements AuthorizerInterface {
    String sessionID;
    Company company = null;
    private SOAPConnectionFactory soapConnectionFactory;
    private SOAPConnection soapConnection;
    private SOAPBody soapResultBody;

    public SOAPAuthorizer() throws SOAPException {
        company = new Company();

        soapConnectionFactory = SOAPConnectionFactory.newInstance();
        //Creating SOAP connection
        soapConnection = soapConnectionFactory.createConnection();
    }


    /**
     * takes SOAPResult from soapResponse
     *
     * @param soapResponse message from service
     * @param title        printed result title
     * @throws Exception
     */
    private void takeSOAPResult(SOAPMessage soapResponse, String title) throws Exception {
        soapResultBody = soapResponse.getSOAPBody();
        String xmlDoc = soapResultBody.getTextContent(); //Text format of soap response body
        String decodedXML = StringEscapeUtils.unescapeHtml(xmlDoc); //Unescape html marks

        //save taken from soap response body and save it to file
        try (PrintStream out = new PrintStream(new FileOutputStream("response.xml"))) {
            out.print(decodedXML);
        }

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
     * @return
     */
    public String getRegonNumber() {
        return company.getRegon();
    }

    /**
     * Takes data from search result (DaneSzukajResult and PobierzPelnyRaportResult
     */
    public void getDataSearchResult(boolean fullRaport) {

        try {
            NodeList nodeList = parseXmlDom();
            if (!fullRaport) {
                company.setRegon(getNodeValue("Regon", nodeList));
                company.setState(getNodeValue("Wojewodztwo", nodeList));
                company.setCity(getNodeValue("Miejscowosc", nodeList));
                company.setPostCode(getNodeValue("KodPocztowy", nodeList));
                company.setStreet(getNodeValue("Ulica", nodeList));
            } else {
                company.setNip(getNodeValue("praw_nip", nodeList));
                company.setFullName(getNodeValue("praw_nazwa", nodeList));
                company.setShortenName(getNodeValue("praw_nazwaSkrocona", nodeList));
                company.setCountry(getNodeValue("praw_adSiedzKraj_Nazwa", nodeList));
                company.setBuildingNumber(getNodeValue("praw_adSiedzNumerNieruchomosci", nodeList));
                company.setLocalNumber(getNodeValue("praw_adSiedzNumerLokalu", nodeList));
                company.setPhoneNumber(getNodeValue("praw_numerTelefonu", nodeList));
                company.setEmail(getNodeValue("praw_adresEmail", nodeList));
                company.setStartDate(getNodeValue("praw_dataRozpoczeciaDzialalnosci", nodeList));
                company.setEndDate(getNodeValue("praw_dataZakonczeniaDzialanosci", nodeList));

            }


        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Parse soap response
     *
     * @return node list (all nodes from response)
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     */
    private NodeList parseXmlDom() throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        // Read XML File
        String xml = IOUtils.toString(new FileInputStream(new File("response.xml")), "UTF-8");
        InputSource is = new InputSource(new StringReader(xml));

        // Parse XML String to DOM
        factory.setNamespaceAware(true);
        factory.setIgnoringComments(true);
        Document doc = builder.parse(is);

        // Extract nodes text
        NodeList nodeList = doc.getElementsByTagNameNS("*", "*");
        return nodeList;
    }

    /**
     * Takes value from given node element from nodelist
     *
     * @param tagName name of node
     * @param nodes   nodelist
     * @return string value of node
     */
    private String getNodeValue(String tagName, NodeList nodes) {
        for (int x = 0; x < nodes.getLength(); x++) {
            Node node = nodes.item(x);
            if (node.getNodeName().equalsIgnoreCase(tagName)) {
                NodeList childNodes = node.getChildNodes();
                for (int y = 0; y < childNodes.getLength(); y++) {
                    Node data = childNodes.item(y);
                    if (data.getNodeType() == Node.TEXT_NODE)
                        return data.getNodeValue();
                }
            }
        }
        return "";
    }

    public Company getCompany() {
        return company;
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
        takeSOAPResult(result, "Result SOAPMessage: ");
    }
}
