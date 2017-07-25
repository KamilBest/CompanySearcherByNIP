package com.companySearch;

import com.companySearch.WsClient.Request.SOAPDataDownloadFullRaportRequest;
import com.companySearch.WsClient.Request.SOAPDataSearchRequest;
import com.companySearch.WsClient.Request.SOAPLoginRequest;
import com.companySearch.WsClient.Request.SOAPLogoutRequest;
import com.companySearch.WsClient.SOAP.SOAPAuthorizer;
import com.companySearch.WsClient.WsClient;

import javax.xml.soap.SOAPException;
import java.util.Scanner;

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


            System.out.println("Enter NIP number:");
            Scanner scanner = new Scanner(System.in);
            String NIP = scanner.nextLine();
            wsClient.getResponse(new SOAPDataSearchRequest(sessionId, NIP));
            wsClient.getData(false);
            String regonNumber = wsClient.getRegonNumber();
            wsClient.getResponse(new SOAPDataDownloadFullRaportRequest(sessionId, regonNumber));
            wsClient.getData(true);
            System.out.println(wsClient.printData().toString());

            wsClient.getResponse(new SOAPLogoutRequest(sessionId));
        } catch (SOAPException e) {
            e.printStackTrace();
        }
    }
}
