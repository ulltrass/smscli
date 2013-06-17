package com.ms.beans.nexmo;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Created with IntelliJ IDEA.
 * User: Ovi
 * Date: 6/13/13
 * Time: 8:21 PM
 * To change this template use File | Settings | File Templates.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SMSMessageResponse {


//    @JsonProperty(value = "message")
//    private SMSMessageEntityResponse smsMessageEntityResponse;

    @JsonProperty(value = "message-id")
    private String messageId;
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

    @JsonProperty
    private String type;
    @JsonProperty(value = "account-id")
    private String accountId;
    @JsonProperty
    private String from;
    @JsonProperty
    private String body;
    @JsonProperty
    private Float price;
    @JsonProperty(value = "date-received")
    private String dateReceived;
    @JsonProperty(value = "date-closed")
    private String dateClosed;
    @JsonProperty(value = "final-status")
    private String finalStatus;
    @JsonProperty
    private String latency;
    @JsonProperty(value = "error-code")
    private String errorCode;
    @JsonProperty(value = "error-code-label")
    private String errorCodeLabel;


    @JsonProperty
    private Integer status;
    @JsonProperty(value = "error-text")
    private String errorText;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getErrorText() {
        return errorText;
    }

    public void setErrorText(String errorText) {
        this.errorText = errorText;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getDateReceived() {
        return dateReceived;
    }

    public void setDateReceived(String dateReceived) {
        this.dateReceived = dateReceived;
    }

    public String getDateClosed() {
        return dateClosed;
    }

    public void setDateClosed(String dateClosed) {
        this.dateClosed = dateClosed;
    }

    public String getFinalStatus() {
        return finalStatus;
    }

    public void setFinalStatus(String finalStatus) {
        this.finalStatus = finalStatus;
    }

    public String getLatency() {
        return latency;
    }

    public void setLatency(String latency) {
        this.latency = latency;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorCodeLabel() {
        return errorCodeLabel;
    }

    public void setErrorCodeLabel(String errorCodeLabel) {
        this.errorCodeLabel = errorCodeLabel;
    }
}
