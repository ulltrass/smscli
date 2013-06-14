package com.ms.client;

import com.ms.beans.SMSResponse;
import com.ms.beans.nexmo.SMSMessage;
import com.ms.beans.twilio.SMSResponseTw;
import com.ms.exception.MsException;
import com.ms.restclient.RestInternalException;
import com.ms.restclient.RestResponseException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * Created with IntelliJ IDEA.
 * User: opetridean
 * Date: 6/14/13
 * Time: 11:16 AM
 * To change this template use File | Settings | File Templates.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-config.xml")
public class TwilioClientTest {

    @Autowired
    TwilioClient twilioClient;
    MsClientFactory msClientFactory = new MsClientFactory();

    @Test
    public void testGetAccountBalanceFromFactory() throws RestInternalException, RestResponseException, MsException {
        MsClient msClient = msClientFactory.getClient("twilio");
        TwilioClient twilioClient1 = (TwilioClient) msClient;

        SMSResponseTw responseTw = (SMSResponseTw) twilioClient1.sendSMSMessage("+15122707326", "+40741098025", "Test");

        assertTrue(!responseTw.getStatus().equals("400"));
    }



    @Test
    public void testSendSMSMessage() throws RestInternalException, RestResponseException {
        SMSResponseTw responseTw = (SMSResponseTw) twilioClient.sendSMSMessage("+15122707326", "+40741098025", "Test");

        assertTrue(!responseTw.getStatus().equals("400"));
    }

    @Test
    public void testSendBulkSMSMessage() throws RestInternalException, RestResponseException {
        List<SMSMessage> smsMessages = new ArrayList<SMSMessage>();
        smsMessages.add(new SMSMessage("+15122707326", "+40741098025", "Test bulk 1"));
        smsMessages.add(new SMSMessage("+15122707326", "+40741098025", "Test bulk 2"));

        List<SMSResponse> smsResponses = twilioClient.sendBulkSMSMessage(smsMessages);

        assertTrue(!((SMSResponseTw) smsResponses.get(0)).getStatus().equals("400"));
        assertTrue(!((SMSResponseTw) smsResponses.get(1)).getStatus().equals("400"));
    }


}
