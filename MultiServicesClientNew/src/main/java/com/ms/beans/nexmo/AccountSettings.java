package com.ms.beans.nexmo;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Created with IntelliJ IDEA.
 * User: opetridean
 * Date: 6/17/13
 * Time: 4:36 PM
 * To change this template use File | Settings | File Templates.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountSettings {

    @JsonProperty(value = "api-secret")
    private String apiSecret;
    @JsonProperty(value = "mo-callback-url")
    private String moCallbackUrl;
    @JsonProperty(value = "dr-callback-url")
    private String drCallbackUrl;
    @JsonProperty(value = "max-outbound-request")
    private String maxOutboundRequest;
    @JsonProperty(value = "max-inbound-request")
    private String maxInboundRequest;

    public String getApiSecret() {
        return apiSecret;
    }

    public void setApiSecret(String apiSecret) {
        this.apiSecret = apiSecret;
    }

    public String getMoCallbackUrl() {
        return moCallbackUrl;
    }

    public void setMoCallbackUrl(String moCallbackUrl) {
        this.moCallbackUrl = moCallbackUrl;
    }

    public String getDrCallbackUrl() {
        return drCallbackUrl;
    }

    public void setDrCallbackUrl(String drCallbackUrl) {
        this.drCallbackUrl = drCallbackUrl;
    }

    public String getMaxOutboundRequest() {
        return maxOutboundRequest;
    }

    public void setMaxOutboundRequest(String maxOutboundRequest) {
        this.maxOutboundRequest = maxOutboundRequest;
    }

    public String getMaxInboundRequest() {
        return maxInboundRequest;
    }

    public void setMaxInboundRequest(String maxInboundRequest) {
        this.maxInboundRequest = maxInboundRequest;
    }
}
