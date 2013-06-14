package com.ms.client;

import com.ms.beans.SMSResponse;
import com.ms.beans.nexmo.SMSMessage;
import com.ms.beans.twilio.SMSResponseTw;
import com.ms.restclient.RestClient;
import com.ms.restclient.RestClientRequestInfo;
import com.ms.restclient.RestInternalException;
import com.ms.restclient.RestResponseException;
import com.ms.util.Constants;
import com.ms.util.TwilioAuthenticationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: opetridean
 * Date: 6/14/13
 * Time: 11:08 AM
 * To change this template use File | Settings | File Templates.
 */
@Service
public class TwilioClient implements MsClient {

    @Autowired
    private RestClient restClient;

    @Value("${twilio.base.url}")
    private String baseUrl;
    @Value("${twilio.api.version}")
    private String apiVersion;
    @Value("${nexmo.response.format}")
    private String responseFormat;

    @Autowired
    private TwilioAuthenticationUtil twilioAuthenticationUtil;


    @Override
    public SMSResponse sendSMSMessage(String from, String to, String text) throws RestResponseException, RestInternalException {
        RestClientRequestInfo restClientRequestInfo = new RestClientRequestInfo();
        restClientRequestInfo.setEndpointUrl(baseUrl + "/" + apiVersion + "/Accounts/{AccountSid}/SMS/Messages." +  responseFormat);
        restClientRequestInfo.setRequestMethod(Constants.HTTP_METHOD_POST);

        Map<String, String> authHeader = twilioAuthenticationUtil.getAuthHeader();
        Map<String, String> vars = twilioAuthenticationUtil.getAuthVars();

        Map<String, String> requestParameterMap = new HashMap<String, String>();
        requestParameterMap.put("From", from);
        requestParameterMap.put("To", to);
        requestParameterMap.put("Body", text);
        restClientRequestInfo.setRequestParameterMap(requestParameterMap);


        SMSResponseTw response = restClient.sendRequest(restClientRequestInfo, authHeader, SMSResponseTw.class, vars);

        return response;
    }

    @Override
    public List<SMSResponse> sendBulkSMSMessage(List<SMSMessage> smsMessages) throws RestResponseException, RestInternalException {
        List<SMSResponse> smsResponses = new ArrayList<SMSResponse>();

        RestClientRequestInfo restClientRequestInfo = new RestClientRequestInfo();
        restClientRequestInfo.setEndpointUrl(baseUrl + "/" + apiVersion + "/Accounts/{AccountSid}/SMS/Messages." +  responseFormat);
        restClientRequestInfo.setRequestMethod(Constants.HTTP_METHOD_POST);

        Map<String, String> authHeader = twilioAuthenticationUtil.getAuthHeader();
        Map<String, String> vars = twilioAuthenticationUtil.getAuthVars();

        Map<String, String> requestParameterMap = new HashMap<String, String>();;

        for (SMSMessage smsMessage : smsMessages) {

            requestParameterMap.put("From", smsMessage.getFrom());
            requestParameterMap.put("To", smsMessage.getTo());
            requestParameterMap.put("Body", smsMessage.getText());
            restClientRequestInfo.setRequestParameterMap(requestParameterMap);

            SMSResponseTw response = restClient.sendRequest(restClientRequestInfo, authHeader, SMSResponseTw.class, vars);

            smsResponses.add(response);
        }

        return smsResponses;
    }
}
