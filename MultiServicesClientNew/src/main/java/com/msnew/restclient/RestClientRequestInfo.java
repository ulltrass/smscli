package com.msnew.restclient;

import org.springframework.http.MediaType;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: opetridean
 * Date: 6/12/13
 * Time: 2:41 PM
 * To change this template use File | Settings | File Templates.
 */
public class RestClientRequestInfo {
    private String endpointUrl; // mandatory, the path for the request
    private String requestMethod; // the request requestMethod
    private MediaType requestMediaType = MediaType.APPLICATION_FORM_URLENCODED; // mandatory, the request media type, by default APPLICATION_FORM_URLENCODED
    private Map<String, String> requestParameterMap; // the request parameters

    public String getEndpointUrl() {
        return endpointUrl;
    }

    public void setEndpointUrl(String endpointUrl) {
        this.endpointUrl = endpointUrl;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(String method) {
        this.requestMethod = method;
    }

    public MediaType getRequestMediaType() {
        return requestMediaType;
    }

    public void setRequestMediaType(MediaType requestMediaType) {
        this.requestMediaType = requestMediaType;
    }

    public Map<String, String> getRequestParameterMap() {
        return requestParameterMap;
    }

    public void setRequestParameterMap(Map<String, String> requestParameterMap) {
        this.requestParameterMap = requestParameterMap;
    }
}
