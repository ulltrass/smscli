package com.ms.client;

import com.ms.beans.SMSResponse;
import com.ms.beans.nexmo.AccountBalance;
import com.ms.beans.nexmo.AccountPricing;
import com.ms.beans.nexmo.SMSMessage;
import com.ms.beans.nexmo.SMSResponseNx;
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

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created with IntelliJ IDEA.
 * User: opetridean
 * Date: 6/12/13
 * Time: 2:47 PM
 * To change this template use File | Settings | File Templates.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-config.xml")
public class NexmoClientTest {

    @Autowired
    private NexmoClient nexmoClient;
    private MsClientFactory msClientFactory = new MsClientFactory();

    @Test
    public void testGetAccountBalance() throws RestInternalException, RestResponseException {

        AccountBalance accountBalance = nexmoClient.getAccountBalance();
        assertNotNull(accountBalance);
    }

    @Test
    public void testGetAccountBalanceFromFactory() throws RestInternalException, RestResponseException, MsException {
        MsClient msClient = msClientFactory.getClient("nexmo");
        NexmoClient nexmoClient1 = (NexmoClient) msClient;

        AccountBalance accountBalance = nexmoClient1.getAccountBalance();
        assertNotNull(accountBalance);
    }

    @Test
    public void testGetAccountPricingForUS() throws RestInternalException, RestResponseException {
        String countryCode = "US";

        AccountPricing accountPricing = nexmoClient.getAccountPricing(countryCode);

        assertNotNull(accountPricing);
    }

    @Test
    public void testSendSMSMessage() throws RestInternalException, RestResponseException {
        SMSResponseNx responseNx = nexmoClient.sendSMSMessage("13172255527", "0040741098025", "Test");

        assertNotNull(responseNx);
    }


    @Test
    public void testSendBulkSMSMessage() throws RestInternalException, RestResponseException {
        List<SMSMessage> smsMessages = new ArrayList<SMSMessage>();
        smsMessages.add(new SMSMessage("13172255527", "0040741098025", "Test bulk 1"));
        smsMessages.add(new SMSMessage("13172255527", "0040741098025", "Test bulk 2"));

        List<SMSResponse> smsResponseNxes = nexmoClient.sendBulkSMSMessage(smsMessages);

        assertTrue(((SMSResponseNx) smsResponseNxes.get(0)).getCount() == 1);
        assertTrue(((SMSResponseNx) smsResponseNxes.get(1)).getCount() == 1);
        assertTrue(((SMSResponseNx) smsResponseNxes.get(0)).getMessages().get(0).getStatus() == 0);
        assertTrue(((SMSResponseNx) smsResponseNxes.get(1)).getMessages().get(0).getStatus() == 0);
    }


}
