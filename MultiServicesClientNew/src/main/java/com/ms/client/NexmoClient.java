package com.ms.client;

import com.ms.beans.nexmo.AccountBalance;
import com.ms.beans.nexmo.AccountPricing;
import com.ms.beans.nexmo.SMSMessage;
import com.ms.beans.nexmo.SMSResponse;
import com.ms.restclient.*;
import com.ms.util.Constants;
import com.ms.util.NexmoAuthenticationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
    @Value("${nexmo.response.format}")
    private String responseFormat;

    public AccountBalance getAccountBalance() throws RestResponseException, RestInternalException {

        RestClientRequestInfo restClientRequestInfo = new RestClientRequestInfo();
        restClientRequestInfo.setEndpointUrl(nexmoBaseUrl + "/account/get-balance/{api_key}/{api_secret}");
        restClientRequestInfo.setRequestMethod(Constants.HTTP_METHOD_GET);

        Map<String, String> vars = nexmoAuthenticationUtil.getAuthVars();

        AccountBalance accountBalance = restClient.sendRequest(restClientRequestInfo, null, AccountBalance.class, vars);

        return accountBalance;
    }

    public AccountPricing getAccountPricing(String countryCode) throws RestResponseException, RestInternalException {

        RestClientRequestInfo restClientRequestInfo = new RestClientRequestInfo();
        restClientRequestInfo.setEndpointUrl(nexmoBaseUrl + "/account/get-pricing/outbound/{api_key}/{api_secret}/{country}");
        restClientRequestInfo.setRequestMethod(Constants.HTTP_METHOD_GET);

        Map<String, String> vars = nexmoAuthenticationUtil.getAuthVars();
        vars.put("country", countryCode);

        AccountPricing accountPricing = restClient.sendRequest(restClientRequestInfo, null, AccountPricing.class, vars);

        return accountPricing;
    }

    public SMSResponse sendSMSMessage(String from, String to, String text) throws RestResponseException, RestInternalException {

        RestClientRequestInfo restClientRequestInfo = new RestClientRequestInfo();
        restClientRequestInfo.setEndpointUrl(nexmoBaseUrl + "/sms/" + responseFormat.toLowerCase());
        restClientRequestInfo.setRequestMethod(Constants.HTTP_METHOD_POST);

        Map<String, String> requestParameterMap = new HashMap<String, String>();
        requestParameterMap.put("api_key", nexmoAuthenticationUtil.getApiKey());
        requestParameterMap.put("api_secret", nexmoAuthenticationUtil.getApiSecret());
        requestParameterMap.put("from", from);
        requestParameterMap.put("to", to);
        requestParameterMap.put("text", text);
        restClientRequestInfo.setRequestParameterMap(requestParameterMap);


        SMSResponse response = restClient.sendRequest(restClientRequestInfo, null, SMSResponse.class, null);

        return response;
    }

    public List<SMSResponse> sendBulkSMSMessage(List<SMSMessage> smsMessages) throws RestResponseException, RestInternalException {
        List<SMSResponse> smsResponses = new ArrayList<SMSResponse>();

        RestClientRequestInfo restClientRequestInfo = new RestClientRequestInfo();
        restClientRequestInfo.setEndpointUrl(nexmoBaseUrl + "/sms/" + responseFormat.toLowerCase());
        restClientRequestInfo.setRequestMethod(Constants.HTTP_METHOD_POST);

        Map<String, String> requestParameterMap = new HashMap<String, String>();
        requestParameterMap.put("api_key", nexmoAuthenticationUtil.getApiKey());
        requestParameterMap.put("api_secret", nexmoAuthenticationUtil.getApiSecret());

        for (SMSMessage smsMessage : smsMessages) {

            requestParameterMap.put("from", smsMessage.getFrom());
            requestParameterMap.put("to", smsMessage.getTo());
            requestParameterMap.put("text", smsMessage.getText());
            restClientRequestInfo.setRequestParameterMap(requestParameterMap);

            SMSResponse response = restClient.sendRequest(restClientRequestInfo, null, SMSResponse.class, null);

            smsResponses.add(response);
        }

        return smsResponses;
    }


}
