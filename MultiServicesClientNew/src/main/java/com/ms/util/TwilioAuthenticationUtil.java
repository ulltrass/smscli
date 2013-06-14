package com.ms.util;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: opetridean
 * Date: 6/14/13
 * Time: 11:14 AM
 * To change this template use File | Settings | File Templates.
 */
@Component
public class TwilioAuthenticationUtil {

    @Value("${twilio.account.sid}")
    private String accountSid;
    @Value("${twilio.account.token}")
    private String accountToken;



    public Map<String, String> getAuthHeader() {
        Map<String, String> vars = new HashMap<String, String>();

        String auth = accountSid + ":" + accountToken;
        byte[] encodedAuth = Base64.encodeBase64(
                auth.getBytes(Charset.forName("US-ASCII")));
        String authHeader = "Basic " + new String( encodedAuth );

        vars.put("Authorization", authHeader);

        return vars;
    }

    public Map<String, String> getAuthVars(){
        Map<String, String> vars = new HashMap<String, String>();
        vars.put("AccountSid", accountSid);

        return vars;
    }

    public String getAccountSid() {
        return accountSid;
    }

    public void setAccountSid(String accountSid) {
        this.accountSid = accountSid;
    }
}
