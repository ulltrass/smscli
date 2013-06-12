package com.ms.client;

import com.ms.beans.nexmo.xml.AccountBalance;
import com.ms.beans.nexmo.xml.AccountPricing;
import com.ms.restclient.*;
import com.ms.util.Constants;
import com.ms.util.NexmoAuthenticationUtil;
import com.ms.util.RestClientUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

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

    @Value("${nexmo.base.url}")
    private String nexmoBaseUrl;
    @Autowired
    private RestClient restClient;
    @Autowired
    private NexmoAuthenticationUtil nexmoAuthenticationUtil;
    @Autowired
    private RestClientUtil restClientUtil;

    public AccountBalance getAccountBalance() throws RestResponseException, RestInternalException {

        RestClientRequestInfo restClientRequestInfo = new RestClientRequestInfo();
        restClientRequestInfo.setEndpointUrl(nexmoBaseUrl + "/account/get-balance/{api_key}/{api_secret}");
        restClientRequestInfo.setRequestMethod(Constants.HTTP_METHOD_GET);

        Map<String, String> vars = nexmoAuthenticationUtil.getAuthVars();
        Map<String, String> httpHeaderAttributeMap = restClientUtil.getXMLAcceptHeader();

        AccountBalance accountBalance = restClient.sendRequest(restClientRequestInfo, httpHeaderAttributeMap, AccountBalance.class, vars);

        return accountBalance;
    }

    public AccountPricing getAccountPricing(String countryCode) throws RestResponseException, RestInternalException {

        RestClientRequestInfo restClientRequestInfo = new RestClientRequestInfo();
        restClientRequestInfo.setEndpointUrl(nexmoBaseUrl + "/account/get-pricing/outbound/{api_key}/{api_secret}/{country}");
        restClientRequestInfo.setRequestMethod(Constants.HTTP_METHOD_GET);

        Map<String, String> vars = nexmoAuthenticationUtil.getAuthVars();
        Map<String, String> httpHeaderAttributeMap = restClientUtil.getXMLAcceptHeader();
        vars.put("country", countryCode);

        AccountPricing accountPricing = restClient.sendRequest(restClientRequestInfo, httpHeaderAttributeMap, AccountPricing.class, vars);

        return accountPricing;
    }






}
