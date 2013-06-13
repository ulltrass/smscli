package com.ms.beans.nexmo;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import javax.xml.bind.annotation.XmlElement;

/**
 * Created with IntelliJ IDEA.
 * User: Ovi
 * Date: 6/13/13
 * Time: 8:25 PM
 * To change this template use File | Settings | File Templates.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SMSMessageEntityResponse {

    @JsonProperty(value = "message-id")
    private Integer messageId;
    @JsonProperty
    private String to;
    @JsonProperty(value = "client-ref")
    private String clientRef;
    @JsonProperty(value = "remaining-balance")
    private Float remainingBalance;
    @JsonProperty(value = "message-price")
    private Float messagePrice;
    @JsonProperty
    private String network;


    @XmlElement
    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getClientRef() {
        return clientRef;
    }

    public void setClientRef(String clientRef) {
        this.clientRef = clientRef;
    }

    public Float getRemainingBalance() {
        return remainingBalance;
    }

    public void setRemainingBalance(Float remainingBalance) {
        this.remainingBalance = remainingBalance;
    }

    public Float getMessagePrice() {
        return messagePrice;
    }

    public void setMessagePrice(Float messagePrice) {
        this.messagePrice = messagePrice;
    }

    public String getNetwork() {
        return network;
    }

    public void setNetwork(String network) {
        this.network = network;
    }
}
