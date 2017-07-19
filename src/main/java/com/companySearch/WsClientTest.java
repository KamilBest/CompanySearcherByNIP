package com.companySearch;

import com.companySearch.WsClient.Request.SOAPDataDownloadFullRaportRequest;
import com.companySearch.WsClient.Request.SOAPDataSearchRequest;
import com.companySearch.WsClient.Request.SOAPLoginRequest;
import com.companySearch.WsClient.Request.SOAPLogoutRequest;
import com.companySearch.WsClient.SOAP.SOAPAuthorizer;
import com.companySearch.WsClient.WsClient;

import javax.xml.soap.SOAPException;

/**
 * Created by Kamil Best on 19.07.2017.
 */
public class WsClientTest {
    public static void main(String[] args) throws Exception {
        try {
            WsClient wsClient = new WsClient(new SOAPAuthorizer());
            wsClient.getResponse(new SOAPLoginRequest());
            wsClient.setSessionID();
            String sessionId = wsClient.getSessionID();

            wsClient.getResponse(new SOAPDataSearchRequest(sessionId));
            wsClient.getResponse(new SOAPDataDownloadFullRaportRequest(sessionId));
            wsClient.getResponse(new SOAPLogoutRequest(sessionId));
        } catch (SOAPException e) {
            e.printStackTrace();
        }
    }
}
