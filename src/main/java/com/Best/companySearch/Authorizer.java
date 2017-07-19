package com.Best.companySearch;

/**
 * Created by Kamil Best on 19.07.2017.
 */
public interface Authorizer {
    void authorize(Request request) throws Exception;
}
