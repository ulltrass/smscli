package com.ms.client;

import com.ms.beans.nexmo.AccountBalance;
import com.ms.restclient.*;
import com.ms.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Ovi
 * Date: 6/11/13
 * Time: 11:47 PM
 * To change this template use File | Settings | File Templates.
 */
@Component
public class NexmoClient implements MsClient {

    private static final String NEXMO_URL = "https://rest.nexmo.com";

    @Autowired
    RestClient restClient;

    public AccountBalance getAccountBalance() throws RestResponseException, RestInternalException {

        Map<String, String> vars = new HashMap<String, String>();
        vars.put("api_key", "2a39ca76");
        vars.put("api_secret", "87dc1451");


        RestClientRequestInfo restClientRequestInfo = new RestClientRequestInfo();
        restClientRequestInfo.setEndpointUrl(NEXMO_URL + "/account/get-balance/{api_key}/{api_secret}");
        restClientRequestInfo.setRequestMethod(Constants.HTTP_METHOD_GET);


        AccountBalance accountBalance = restClient.sendRequest(restClientRequestInfo, null, AccountBalance.class, vars);

        return accountBalance;
    }


}
