package com.companySearch.WsClient;

import com.companySearch.WsClient.Request.Request;
import com.companySearch.WsClient.SOAP.SOAPAuthorizerInterface;


/**
 * Created by Kamil Best on 14.07.2017.
 * WsClient class
 * creating connection and taking responses from requests.
 */
public class WsClient {

    /**
     * SOAPAuthorizerInterface objects, handle SOAP requests.
     */
    private SOAPAuthorizerInterface soapAuthorizer;

    public WsClient(SOAPAuthorizerInterface soapAuthorizer) {
        this.soapAuthorizer = soapAuthorizer;
    }

    /**
     * Calls request and get response.
     *
     * @param request given request
     * @throws Exception
     */
    public void getResponse(Request request) throws Exception {
        soapAuthorizer.authorize(request);

    }

    public void setSessionID() {
        soapAuthorizer.setSessionIDFromLoginRequestResponse();
    }

    public String getSessionID() {
        return soapAuthorizer.getSessionID();
    }
}
