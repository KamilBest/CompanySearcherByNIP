package com.companySearch.WsClient.Request;

import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;

/**
 * Created by Kamil Best on 18.07.2017.
 */
public class SOAPLoginRequest extends Request {

    String actionName = "Zaloguj";

    public SOAPLoginRequest() {
        super.actionName = this.actionName;
        super.dataContract = false;
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
        SOAPElement soapBodyElement1 = soapBodyElement.addChildElement("pKluczUzytkownika", "ns");
        soapBodyElement1.addTextNode("abcde12345abcde12345");
    }


    @Override
    public void constructRequestMessage() throws Exception {

    }
}
