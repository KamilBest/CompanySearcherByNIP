package com.Best.companySearch;

import javax.xml.soap.SOAPMessage;

/**
 * Created by Kamil Best on 18.07.2017.
 */
public interface RequestInterface {
    SOAPMessage constructRequestMessage() throws Exception;
}
