package com.ms.client;

import com.ms.beans.SMSResponse;
import com.ms.beans.nexmo.*;
import com.ms.exception.MsException;
import com.ms.restclient.RestClient;
import com.ms.restclient.RestClientRequestInfo;
import com.ms.restclient.RestInternalException;
import com.ms.restclient.RestResponseException;
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

    public AccountNumbers getAccountNumbers() throws RestResponseException, RestInternalException {

        RestClientRequestInfo restClientRequestInfo = new RestClientRequestInfo();
        restClientRequestInfo.setEndpointUrl(nexmoBaseUrl + "/account/numbers/{api_key}/{api_secret}");
        restClientRequestInfo.setRequestMethod(Constants.HTTP_METHOD_GET);

        Map<String, String> vars = nexmoAuthenticationUtil.getAuthVars();

        AccountNumbers accountNumbers = restClient.sendRequest(restClientRequestInfo, null, AccountNumbers.class, vars);

        return accountNumbers;
    }

    public AccountSettings updateAccountSettings(String apiSecret) throws RestResponseException, RestInternalException, MsException {

        if (apiSecret.length() > 8) {
            throw new MsException("api_secret size cannot be larger than 8 characters ");
        }

        RestClientRequestInfo restClientRequestInfo = new RestClientRequestInfo();
        restClientRequestInfo.setEndpointUrl(nexmoBaseUrl + "/account/settings/{api_key}/{api_secret}?newSecret={new_secret}");
        restClientRequestInfo.setRequestMethod(Constants.HTTP_METHOD_POST);

        Map<String, String> vars = nexmoAuthenticationUtil.getAuthVars();
        vars.put("new_secret", apiSecret);

        AccountSettings accountSettings = restClient.sendRequest(restClientRequestInfo, null, AccountSettings.class, vars);

        return accountSettings;
    }

    public AccountNumbers searchNumbers(String countryCode, String pattern) throws RestResponseException, RestInternalException, MsException {
        String uri = "/number/search/{api_key}/{api_secret}/{country}";
        Map<String, String> vars = nexmoAuthenticationUtil.getAuthVars();
        vars.put("country", countryCode);

        if (pattern != null) {
            uri = uri + "?pattern={search-pattern}";
            vars.put("search-pattern", pattern);
        }

        RestClientRequestInfo restClientRequestInfo = new RestClientRequestInfo();
        restClientRequestInfo.setEndpointUrl(nexmoBaseUrl + uri);
        restClientRequestInfo.setRequestMethod(Constants.HTTP_METHOD_GET);

        AccountNumbers accountNumbers = restClient.sendRequest(restClientRequestInfo, null, AccountNumbers.class, vars);

        return accountNumbers;
    }

    public AccountSettings buyNumber(String country, String msisdn) throws RestResponseException, RestInternalException, MsException {


        RestClientRequestInfo restClientRequestInfo = new RestClientRequestInfo();
        restClientRequestInfo.setEndpointUrl(nexmoBaseUrl + "/number/buy/{api_key}/{api_secret}/{country}/{msisdn}");
        restClientRequestInfo.setRequestMethod(Constants.HTTP_METHOD_POST);

        Map<String, String> vars = nexmoAuthenticationUtil.getAuthVars();
        vars.put("country", country);
        vars.put("msisdn", msisdn);

        AccountSettings accountSettings = restClient.sendRequest(restClientRequestInfo, null, AccountSettings.class, vars);

        return accountSettings;
    }

    public AccountSettings cancelNumber(String country, String msisdn) throws RestResponseException, RestInternalException, MsException {

        RestClientRequestInfo restClientRequestInfo = new RestClientRequestInfo();
        restClientRequestInfo.setEndpointUrl(nexmoBaseUrl + "/number/cancel/{api_key}/{api_secret}/{country}/{msisdn}");
        restClientRequestInfo.setRequestMethod(Constants.HTTP_METHOD_POST);

        Map<String, String> vars = nexmoAuthenticationUtil.getAuthVars();
        vars.put("country", country);
        vars.put("msisdn", msisdn);

        AccountSettings accountSettings = restClient.sendRequest(restClientRequestInfo, null, AccountSettings.class, vars);

        return accountSettings;
    }

    public AccountSettings updateNumber(String country, String msisdn) throws RestResponseException, RestInternalException, MsException {

        RestClientRequestInfo restClientRequestInfo = new RestClientRequestInfo();
        restClientRequestInfo.setEndpointUrl(nexmoBaseUrl + "/number/update/{api_key}/{api_secret}/{country}/{msisdn}");
        restClientRequestInfo.setRequestMethod(Constants.HTTP_METHOD_POST);

        Map<String, String> vars = nexmoAuthenticationUtil.getAuthVars();
        vars.put("country", country);
        vars.put("msisdn", msisdn);

        AccountSettings accountSettings = restClient.sendRequest(restClientRequestInfo, null, AccountSettings.class, vars);

        return accountSettings;
    }

    public SMSMessageResponse searchMessage(String id) throws RestResponseException, RestInternalException {

        RestClientRequestInfo restClientRequestInfo = new RestClientRequestInfo();
        restClientRequestInfo.setEndpointUrl(nexmoBaseUrl + "/search/message/{api_key}/{api_secret}/{id}");
        restClientRequestInfo.setRequestMethod(Constants.HTTP_METHOD_GET);

        Map<String, String> vars = nexmoAuthenticationUtil.getAuthVars();
        vars.put("id", id);

        SMSMessageResponse smsMessageResponse = restClient.sendRequest(restClientRequestInfo, null, SMSMessageResponse.class, vars);

        return smsMessageResponse;
    }


    public SMSMessageResponse searchRejectedMessages(String date) throws RestResponseException, RestInternalException {

        RestClientRequestInfo restClientRequestInfo = new RestClientRequestInfo();
        restClientRequestInfo.setEndpointUrl(nexmoBaseUrl + "/search/rejections/{api_key}/{api_secret}?date={date}");
        restClientRequestInfo.setRequestMethod(Constants.HTTP_METHOD_GET);

        Map<String, String> vars = nexmoAuthenticationUtil.getAuthVars();
        vars.put("date", date);

        SMSMessageResponse smsMessageResponse = restClient.sendRequest(restClientRequestInfo, null, SMSMessageResponse.class, vars);

        return smsMessageResponse;
    }


    public SMSResponseNx sendSMSMessage(String from, String to, String text) throws RestResponseException, RestInternalException {

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


        SMSResponseNx responseNx = restClient.sendRequest(restClientRequestInfo, null, SMSResponseNx.class, null);

        return responseNx;
    }

    public List<SMSResponse> sendBulkSMSMessage(List<SMSMessage> smsMessages) throws RestResponseException, RestInternalException {
        List<SMSResponse> smsResponseNxes = new ArrayList<SMSResponse>();

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

            SMSResponseNx responseNx = restClient.sendRequest(restClientRequestInfo, null, SMSResponseNx.class, null);

            smsResponseNxes.add(responseNx);
        }

        return smsResponseNxes;
    }

}
