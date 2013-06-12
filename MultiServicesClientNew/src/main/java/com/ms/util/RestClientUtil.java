package com.ms.util;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Ovi
 * Date: 6/12/13
 * Time: 8:12 PM
 * To change this template use File | Settings | File Templates.
 */
@Component
public class RestClientUtil {

    public Map<String, String> getXMLAcceptHeader(){
        Map<String, String> httpHeaderAttributeMap = new HashMap<String, String>();
        httpHeaderAttributeMap.put("Accept", "application/xml");

        return httpHeaderAttributeMap;
    }

    public Map<String, String> getJSONAcceptHeader(){
        Map<String, String> httpHeaderAttributeMap = new HashMap<String, String>();
        httpHeaderAttributeMap.put("Accept", "application/json");

        return httpHeaderAttributeMap;
    }

}
