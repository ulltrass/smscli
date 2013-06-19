package com.msnew.beans.twilio;

import com.msnew.beans.SMSResponse;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import java.sql.Timestamp;

/**
 * Created with IntelliJ IDEA.
 * User: opetridean
 * Date: 6/14/13
 * Time: 12:40 PM
 * To change this template use File | Settings | File Templates.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SMSResponseTw implements SMSResponse{

    @JsonProperty
    private String sid;
    @JsonProperty(value = "date_created")
    private Timestamp dateCreated;
    @JsonProperty(value = "date_updated")
    private Timestamp dateUpdated;
    @JsonProperty(value = "date_sent")
    private Timestamp dateSent;
    @JsonProperty(value = "account_sid")
    private String accountSid;
    @JsonProperty
    private String to;
    @JsonProperty
    private String from;
    @JsonProperty
    private String body;
    @JsonProperty
    private String direction;
    @JsonProperty(value = "api-version")
    private String apiVersion;
    @JsonProperty
    private Float price;
    @JsonProperty(value = "price_unit")
    private String priceUnit;
    @JsonProperty
    private String uri;
    @JsonProperty
    private String status;
    @JsonProperty
    private String message;
    @JsonProperty
    private String code;
    @JsonProperty(value = "more_info")
    private String moreInfo;

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public Timestamp getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Timestamp dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Timestamp getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(Timestamp dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    public Timestamp getDateSent() {
        return dateSent;
    }

    public void setDateSent(Timestamp dateSent) {
        this.dateSent = dateSent;
    }

    public String getAccountSid() {
        return accountSid;
    }

    public void setAccountSid(String accountSid) {
        this.accountSid = accountSid;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getApiVersion() {
        return apiVersion;
    }

    public void setApiVersion(String apiVersion) {
        this.apiVersion = apiVersion;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getPriceUnit() {
        return priceUnit;
    }

    public void setPriceUnit(String priceUnit) {
        this.priceUnit = priceUnit;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMoreInfo() {
        return moreInfo;
    }

    public void setMoreInfo(String moreInfo) {
        this.moreInfo = moreInfo;
    }
}
