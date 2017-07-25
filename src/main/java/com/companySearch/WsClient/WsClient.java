package com.companySearch.WsClient;

import com.companySearch.WsClient.Request.Request;
import com.companySearch.WsClient.Response.Company;
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

    public Company printData() {
        return soapAuthorizer.getCompany();
    }

    public void getData(boolean fullRaport) {
        soapAuthorizer.getDataSearchResult(fullRaport);
    }

    public void setSessionID() {
        soapAuthorizer.setSessionIDFromLoginRequestResponse();
    }

    public String getRegonNumber() {
        return soapAuthorizer.getRegonNumber();
    }
    public String getSessionID() {
        return soapAuthorizer.getSessionID();
    }
}
