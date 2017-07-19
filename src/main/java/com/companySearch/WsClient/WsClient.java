package com.companySearch.WsClient;

import com.companySearch.WsClient.Request.Request;
import com.companySearch.WsClient.SOAP.SOAPAuthorizer;


/**
 * Created by Kamil Best on 14.07.2017.
 * WsClient class
 * creating connection and taking responses from requests.
 */
public class WsClient {

    /**
     * SOAPAuthorizer objects, handle SOAP requests.
     */
    private SOAPAuthorizer soapAuthorizer;

    public WsClient(SOAPAuthorizer soapAuthorizer) {
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
