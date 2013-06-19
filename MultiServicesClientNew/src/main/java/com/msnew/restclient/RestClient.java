package com.msnew.restclient;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: opetridean
 * Date: 6/12/13
 * Time: 2:39 PM
 * To change this template use File | Settings | File Templates.
 */
public interface RestClient {
    /**
     * Sends a request to the REST service using the information contained in the restClientRequestInfo and converts the
     * XML response to a JAXB object of the specified type using the specified JAXBContext to obtain the unmarshaller.
     *
     * @param restClientRequestInfo  The RestClientRequestInfo
     * @param httpHeaderAttributeMap Optional, Map with http header attribute name and values
     * @param responseObjectClass    The class of the response object
     * @return The JAXB object containing the response information
     * @throws RestResponseException Thrown when there is a response xml which indicates an error
     * @throws RestInternalException Thrown in all exception cases except when the response XML / status code indicates an error returned by the REST server,
     *                               the original exception is accesible using getCause method
     * @throws javax.xml.bind.JAXBException         Thrown in case of unmarshalling errors
     */
    <T> T sendRequest(RestClientRequestInfo restClientRequestInfo, Map<String, String> httpHeaderAttributeMap,
                      Class<T> responseObjectClass,  Map<String,?> uriVariables) throws RestInternalException, RestResponseException;
}
