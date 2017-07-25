package com.companySearch.gui.Controller;


import com.companySearch.WsClient.Request.SOAPDataDownloadFullRaportRequest;
import com.companySearch.WsClient.Request.SOAPDataSearchRequest;
import com.companySearch.WsClient.Request.SOAPLoginRequest;
import com.companySearch.WsClient.Request.SOAPLogoutRequest;
import com.companySearch.WsClient.SOAP.SOAPAuthorizer;
import com.companySearch.WsClient.WsClient;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import javax.xml.soap.SOAPException;

public class MainController {

    private String sessionId;
    private WsClient wsClient;

    @FXML
    private TextField NIP_TextField;

    /**
     * Text are which contains required company info.
     */
    @FXML
    private TextArea companyInfoTextArea;

    @FXML
    private Button searchButton;

    /**
     * Initializes Client
     */
    @FXML
    public void initialize() {

        try {
            wsClient = new WsClient(new SOAPAuthorizer());
            wsClient.getResponse(new SOAPLoginRequest());
            wsClient.setSessionID();

            //take session ID to every next request
            sessionId = wsClient.getSessionID();
        } catch (SOAPException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Search button handler.
     * Searches company by NIP and puts info into companyInfoTextArea
     */
    public void searchCompanyByNip() {
        String NIP = NIP_TextField.getText();
        if (NIP != null && !NIP.isEmpty()) {
            try {
                wsClient.getResponse(new SOAPDataSearchRequest(sessionId, NIP));
                wsClient.getData(false);

                //take regon number to DataDownloadFullRaport request
                String regonNumber = wsClient.getRegonNumber();

                //take specified data from DataDownloadFullRaportResult
                wsClient.getResponse(new SOAPDataDownloadFullRaportRequest(sessionId, regonNumber));
                wsClient.getData(true);
                //print searched company values
                companyInfoTextArea.setText(wsClient.printData().toString());
                searchButton.setDisable(true);

                //logout
                wsClient.getResponse(new SOAPLogoutRequest(sessionId));


            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Clear button handler.
     * Clears all data in Fields and restores previous Label value
     */
    public void clear() {
        searchButton.setDisable(false);

        NIP_TextField.setText("");
        companyInfoTextArea.setText("");

    }


}
