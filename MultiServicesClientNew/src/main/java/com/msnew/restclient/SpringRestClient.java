package com.msnew.restclient;

import com.msnew.util.Constants;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.http.converter.xml.Jaxb2RootElementHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: opetridean
 * Date: 6/12/13
 * Time: 2:42 PM
 * To change this template use File | Settings | File Templates.
 */
@Service
public class SpringRestClient implements RestClient {

    private RestTemplate restTemplate;

    @Value("${nexmo.response.format}")
    private String responseFormat;

    public SpringRestClient() {
//        HttpComponentsClientHttpRequestFactory reqFactory = new HttpComponentsClientHttpRequestFactory();
//        ThreadSafeClientConnManager connManager = (ThreadSafeClientConnManager) reqFactory.getHttpClient().getConnectionManager();
//        connManager.setMaxTotal(1000);
//        connManager.setDefaultMaxPerRoute(500);
//        this.restTemplate = new RestTemplate(reqFactory);

        this.restTemplate = new RestTemplate();
    }

    /**
     * Sends a request to the REST service using the information contained in the restClientRequestInfo and converts the
     * XML response to a JAXB object of the specified type using the specified JAXBContext to obtain the unmarshaller.
     *
     * @param restClientRequestInfo  The RestClientRequestInfo
     * @param httpHeaderAttributeMap Optional, Map with http header attribute name and values
     * @param responseObjectClass    The class of the response object
     * @throws RestResponseException Thrown when there is a response xml which indicates an error
     * @throws RestInternalException Thrown in all exception cases except when the response XML / status code indicates an error returned by the REST server,
     *                               the original exception is accesible using getCause method
     */
    public <T> T sendRequest(RestClientRequestInfo restClientRequestInfo, Map<String, String> httpHeaderAttributeMap,
                             Class<T> responseObjectClass, Map<String, ?> uriVariables) throws RestInternalException, RestResponseException {

        try {
            String method = restClientRequestInfo.getRequestMethod();
            Map<String, String> requestParameterMap = restClientRequestInfo.getRequestParameterMap();

            String endpointUrlAndParams = restClientRequestInfo.getEndpointUrl();

            if (requestParameterMap != null && !requestParameterMap.isEmpty()
                    && (Constants.HTTP_METHOD_GET.equals(method) || Constants.HTTP_METHOD_DELETE.equals(method))) {
                endpointUrlAndParams += "?" + getUrlParameters(requestParameterMap);
            }

            // get Spring HttpHeaders
            HttpHeaders httpHeaders = getHttpHeaders(httpHeaderAttributeMap);

            // get message converters - this can be either XML or JSON
            List<HttpMessageConverter<?>> messageConverters = getMessageConverters();

            restTemplate.setMessageConverters(messageConverters);

            // get Spring HttpMethod
            HttpMethod springHttpMethod = getSpringHttpMethod(method);

            // get Spring HttpEntity for request
            HttpEntity requestHttpEntity = getRequestHttpEntity(method, requestParameterMap, httpHeaders);

            if (uriVariables == null) {
                uriVariables = new HashMap<String, Object>();
            }

            ResponseEntity responseEntity = restTemplate.exchange(endpointUrlAndParams, springHttpMethod, requestHttpEntity,
                    responseObjectClass, uriVariables);


            HttpStatus httpStatus = responseEntity.getStatusCode();

            // throw RestResponseException if status code is not OK (200)
            if (!HttpStatus.OK.equals(httpStatus) && !HttpStatus.CREATED.equals(httpStatus)) {
                // throw new RestResponseException(responseEntity.getBody().toString(), httpStatus.value());
                //todo: add logging
            }

            Object response = responseEntity.getBody();

            return responseObjectClass.cast(response);

        } catch (HttpClientErrorException cee) {
            try {
                ObjectMapper mapper = new ObjectMapper();
                Object response = mapper.readValue(cee.getResponseBodyAsByteArray(), responseObjectClass);
                return responseObjectClass.cast(response);

            } catch (IOException e) {
                throw new RestResponseException(cee.getResponseBodyAsString(), cee.getStatusCode().value());
            }

        } catch (Exception e) {
            throw new RestInternalException("Exception of type " + e.getMessage() + " occured, see stack trace ", e);
        }
    }

    private List<HttpMessageConverter<?>> getMessageConverters() {
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();

        messageConverters.add(new FormHttpMessageConverter());

        if (Constants.RESPONSE_FORMAT_JSON.equals(responseFormat)) {
            //Add the Jackson Message converter
            messageConverters.add(new MappingJacksonHttpMessageConverter());
        } else {
            Jaxb2RootElementHttpMessageConverter jaxbMessageConverter = new Jaxb2RootElementHttpMessageConverter();
            List<MediaType> mediaTypes = new ArrayList<MediaType>();
            mediaTypes.add(MediaType.APPLICATION_XML);
            jaxbMessageConverter.setSupportedMediaTypes(mediaTypes);
            messageConverters.add(jaxbMessageConverter);
        }

        return messageConverters;
    }

    private HttpMethod getSpringHttpMethod(String method) {
        HttpMethod springHttpMethod;

        if (Constants.HTTP_METHOD_POST.equals(method)) {
            springHttpMethod = HttpMethod.POST;
        } else if (Constants.HTTP_METHOD_PUT.equals(method)) {
            springHttpMethod = HttpMethod.PUT;
        } else if (Constants.HTTP_METHOD_GET.equals(method)) {
            springHttpMethod = HttpMethod.GET;
        } else if (Constants.HTTP_METHOD_DELETE.equals(method)) {
            springHttpMethod = HttpMethod.DELETE;
        } else {
            throw new RuntimeException("Invalid method");
        }

        return springHttpMethod;
    }

    private HttpEntity getRequestHttpEntity(String method, Map<String, String> requestParameterMap, HttpHeaders httpHeaders) {
        HttpEntity requestHttpEntity;

        if (Constants.HTTP_METHOD_GET.equals(method) || Constants.HTTP_METHOD_DELETE.equals(method)) {
            requestHttpEntity = new HttpEntity<String>(httpHeaders);
        } else { // POST, PUT
            MultiValueMap<String, Object> form = new LinkedMultiValueMap<String, Object>();

            if (requestParameterMap != null && !requestParameterMap.isEmpty()) {
                for (String requestParameter : requestParameterMap.keySet()) {
                    form.set(requestParameter, requestParameterMap.get(requestParameter));
                }
            }

            requestHttpEntity = new HttpEntity<MultiValueMap<String, Object>>(form, httpHeaders);
        }

        return requestHttpEntity;
    }

    private HttpHeaders getHttpHeaders(Map<String, String> httpHeaderAttributeMap) {
        HttpHeaders httpHeaders = new HttpHeaders();

        if (httpHeaderAttributeMap != null) {
            for (String httpHeaderAttributeName : httpHeaderAttributeMap.keySet()) {
                httpHeaders.set(httpHeaderAttributeName, httpHeaderAttributeMap.get(httpHeaderAttributeName));
            }
        }

        if (Constants.RESPONSE_FORMAT_JSON.equals(responseFormat)) {
            httpHeaders.set("Accept", "application/json");
        } else {
            httpHeaders.set("Accept", "application/xml");
        }

        return httpHeaders;
    }

    private String getUrlParameters(Map<String, String> requestParameterMap) throws UnsupportedEncodingException {
        StringBuilder requestParametersBuilder = new StringBuilder();

        for (String requestParameter : requestParameterMap.keySet()) {
            if (requestParametersBuilder.length() > 0) {
                requestParametersBuilder.append("&");
            }

            requestParametersBuilder.append(requestParameter + "=" + URLEncoder.encode(requestParameterMap.get(requestParameter), Constants.ENCODING_UTF8));
        }

        return requestParametersBuilder.toString();
    }
}