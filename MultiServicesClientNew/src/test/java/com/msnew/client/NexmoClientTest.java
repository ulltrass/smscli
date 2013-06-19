package com.msnew.client;

import com.msnew.beans.SMSResponse;
import com.msnew.beans.nexmo.*;
import com.msnew.exception.MsException;
import com.msnew.restclient.RestInternalException;
import com.msnew.restclient.RestResponseException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

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
    @Value("${nexmo.api.secret}")
    private String currentApiSecret;


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
    public void testGetAccountNumbers() throws RestInternalException, RestResponseException {

        AccountNumbers accountNumbers = nexmoClient.getAccountNumbers();

        assertNotNull(accountNumbers);
    }

    @Test
    public void testUpdateAccountSecret() throws RestInternalException, RestResponseException, MsException {
        String newSecret = "87dc1451";
        AccountSettings accountSettings = nexmoClient.updateAccountSettings(newSecret);

        assertNotNull(accountSettings);
        assertEquals(newSecret, accountSettings.getApiSecret());
    }

    @Test
    public void testNumberSearch() throws RestInternalException, RestResponseException, MsException {
        String countryCode = "US";

        AccountNumbers accountNumbers = nexmoClient.searchNumbers(countryCode, null);

        assertNotNull(accountNumbers);
        assertTrue(accountNumbers.getNumbers().size() > 0);
    }

    @Test
    public void testNumberSearchWithPattern() throws RestInternalException, RestResponseException, MsException {
        String countryCode = "US";
        String pattern = "19";

        AccountNumbers accountNumbers = nexmoClient.searchNumbers(countryCode, pattern);

        assertNotNull(accountNumbers);
        assertTrue(accountNumbers.getNumbers().size() > 0);
    }


    @Test
    public void testSearchMessage() throws RestInternalException, RestResponseException {
        SMSResponseNx responseNx = nexmoClient.sendSMSMessage("13172255527", "0040741098025", "Test");

        assertNotNull(responseNx);
        assertNotNull(responseNx.getMessages());
        assertNotNull(responseNx.getMessages().get(0));

        String messageId = responseNx.getMessages().get(0).getMessageId();

        SMSMessageResponse smsMessageResponse = nexmoClient.searchMessage(messageId);

        //This might be null as the message becomes searcheable after a few minutes since it was sent
        assertNotNull(smsMessageResponse);
    }


    @Test
    public void testSendSMSMessage() throws RestInternalException, RestResponseException {
        SMSResponseNx responseNx = nexmoClient.sendSMSMessage("13172255527", "18163491408", "Test");

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
