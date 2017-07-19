package com.Best.companySearch;

import javax.xml.soap.SOAPException;

/**
 * Created by Kamil Best on 19.07.2017.
 */
public class WsClientTest {
    public static void main(String[] args) throws Exception {
        try {
            WsClient wsClient = new WsClient(new SOAPAuthorizer());
            wsClient.getResponse(new LoginRequest());
            DaneSzukajTest.soapTest();
        } catch (SOAPException e) {
            e.printStackTrace();
        }
    }
}
