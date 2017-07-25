package com.companySearch.WsClient.Response;

import org.apache.commons.io.IOUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.StringReader;

/**
 * SOAPResponseFormat takes SOAP response from response.xml file and formats data to print
 * Created by Kamil Best on 25.07.2017.
 */
public class SOAPResponseFormat {
    private Company company = null;

    public SOAPResponseFormat() {
        company = new Company();
    }

    /**
     * Parse soap response with DOM parser
     *
     * @return node list (all nodes from response)
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     */
    private NodeList parseXmlDom(String filePath) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        // Read XML File
        String xml = IOUtils.toString(new FileInputStream(new File(filePath)), "UTF-8");
        InputSource is = new InputSource(new StringReader(xml));

        // Parse XML String to DOM
        factory.setNamespaceAware(true);
        factory.setIgnoringComments(true);
        Document doc = builder.parse(is);

        // Extract nodes text
        return doc.getElementsByTagNameNS("*", "*");
    }

    /**
     * Takes value from given node element from nodelist and returns it
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

    /**
     * Parse response file with DOM parser and loads it into nodeList then puts specified data to company object.
     *
     * @param fullRaport defines whether which option should get data, if false take data from DataSearchResult otherwise
     *                   from DownloadFullRaportResult
     */
    public void formatData(boolean fullRaport) {
        try {
            String responseFilePath = "response.xml";
            NodeList nodeList = parseXmlDom(responseFilePath);
            if (!fullRaport)
                getDataSearchResult(nodeList);
            else
                getDataFromDownloadFullRaportResult(nodeList);

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Takes specified data from search result DaneSzukajResult and puts into company object
     */
    private void getDataSearchResult(NodeList nodeList) {
        company.setRegon(getNodeValue("Regon", nodeList));
        company.setState(getNodeValue("Wojewodztwo", nodeList));
        company.setCity(getNodeValue("Miejscowosc", nodeList));
        company.setPostCode(getNodeValue("KodPocztowy", nodeList));
        company.setStreet(getNodeValue("Ulica", nodeList));
    }

    /**
     * Takes specified data from search result DanePobierzPelnyRaportResult and puts into company object
     */
    private void getDataFromDownloadFullRaportResult(NodeList nodeList) {
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

    public String getRegonNumber() {
        return company.getRegon();
    }

    public Company getCompany() {
        return company;
    }


}


