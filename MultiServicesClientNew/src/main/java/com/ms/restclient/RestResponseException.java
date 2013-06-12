package com.ms.restclient;

/**
 * Created with IntelliJ IDEA.
 * User: opetridean
 * Date: 6/12/13
 * Time: 2:40 PM
 * To change this template use File | Settings | File Templates.
 */
public class RestResponseException extends Exception {
    private int responseCode;

    public RestResponseException(String message, int errorCore) {
        super(message);
        this.responseCode = errorCore;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }
}
