package com.companySearch.WsClient.Request;

import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;

/**
 * Created by Kamil Best on 19.07.2017.
 */
public class SOAPDataDownloadFullRaportRequest extends Request {

    String actionName = "DanePobierzPelnyRaport";
    public SOAPDataDownloadFullRaportRequest(String sessionID) {
        this.sessionID = sessionID;
        super.actionName = this.actionName;
        this.dataContract = false;
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
        SOAPElement soapBodyElement1 = soapBodyElement.addChildElement("pRegon", "ns");
        soapBodyElement1.addTextNode("000331501");
        SOAPElement parametr = soapBodyElement.addChildElement("pNazwaRaportu", "ns");
        parametr.addTextNode("PublDaneRaportPrawna");
    }

}


