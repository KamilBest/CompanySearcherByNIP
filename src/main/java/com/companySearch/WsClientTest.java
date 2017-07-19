package com.companySearch;

import com.companySearch.WsClient.Request.LoginRequest;
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
        } catch (SOAPException e) {
            e.printStackTrace();
        }
    }
}
