package com.msnew.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Ovi
 * Date: 6/12/13
 * Time: 8:01 PM
 * To change this template use File | Settings | File Templates.
 */
@Component
public class NexmoAuthenticationUtil {

    @Value("${nexmo.api.key}")
    private String apiKey;
    @Value("${nexmo.api.secret}")
    private String apiSecret;

    public Map<String, String> getAuthVars() {
        Map<String, String> vars = new HashMap<String, String>();
        vars.put("api_key", apiKey);
        vars.put("api_secret", apiSecret);

        return vars;
    }

    public String getApiKey() {
        return apiKey;
    }

    public String getApiSecret() {
        return apiSecret;
    }
}
