package com.companySearch.WsClient.SOAP;

import com.companySearch.WsClient.Request.Request;

/**
 * Created by Kamil Best on 19.07.2017.
 */
public interface Authorizer {
    void authorize(Request request) throws Exception;
}
