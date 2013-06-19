package com.msnew.client;

import com.msnew.beans.SMSResponse;
import com.msnew.beans.nexmo.SMSMessage;
import com.msnew.restclient.RestInternalException;
import com.msnew.restclient.RestResponseException;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Ovi
 * Date: 6/11/13
 * Time: 11:44 PM
 * To change this template use File | Settings | File Templates.
 */
public interface MsClient {

    public SMSResponse sendSMSMessage(String from, String to, String text) throws RestResponseException, RestInternalException;

    public List<SMSResponse> sendBulkSMSMessage(List<SMSMessage> smsMessages) throws RestResponseException, RestInternalException;



}
