package com.companySearch;

import com.companySearch.WsClient.Request.DataDownloadFullRaportRequest;
import com.companySearch.WsClient.Request.DataSearchRequest;
import com.companySearch.WsClient.Request.LoginRequest;
import com.companySearch.WsClient.Request.LogoutRequest;
import com.companySearch.WsClient.SOAP.SOAPAuthorizerInterface;
import com.companySearch.WsClient.WsClient;

import javax.xml.soap.SOAPException;

/**
 * Created by Kamil Best on 19.07.2017.
 */
public class WsClientTest {
    public static void main(String[] args) throws Exception {
        try {
            WsClient wsClient = new WsClient(new SOAPAuthorizerInterface());
            wsClient.getResponse(new LoginRequest());
            wsClient.setSessionID();
            String sessionId = wsClient.getSessionID();

            wsClient.getResponse(new DataSearchRequest(sessionId));
            wsClient.getResponse(new DataDownloadFullRaportRequest(sessionId));
            wsClient.getResponse(new LogoutRequest(sessionId));
        } catch (SOAPException e) {
            e.printStackTrace();
        }
    }
}
