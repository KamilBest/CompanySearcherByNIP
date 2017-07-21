package com.companySearch.WsClient.Request;

import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;

/**
 * Created by Kamil Best on 19.07.2017.
 */

public class SOAPDataSearchRequest extends Request {

    String actionName = "DaneSzukaj";
    String NIP = "";

    public SOAPDataSearchRequest(String sessionID, String NIP) {
        this.sessionID = sessionID;
        this.NIP = NIP;
        super.actionName = this.actionName;
        this.dataContract = true;
        try {
            super.constructRequestMessage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void prepareSOAPBody() throws SOAPException {
        soapBody.setPrefix("soap");
        SOAPElement soapBodyElement = soapBody.addChildElement(actionName, "ns");
        SOAPElement soapBodyElement1 = soapBodyElement.addChildElement("pParametryWyszukiwania", "ns");
        SOAPElement parametr = soapBodyElement1.addChildElement("Nip", "dat");
        parametr.addTextNode(NIP);
    }

}
