package com.ms.beans.nexmo;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: opetridean
 * Date: 6/13/13
 * Time: 6:14 PM
 * To change this template use File | Settings | File Templates.
 */
@XmlRootElement(name = "outbound-country-pricing")
@JsonIgnoreProperties(ignoreUnknown = true)
public class SMSResponse {

    @JsonProperty(value = "message-count")
    private Integer count;

    @JsonProperty
    List<SMSMessageResponse> messages;

    @XmlElement(name = "messages")
    public List<SMSMessageResponse> getMessages() {
        return messages;
    }

    public void setMessages(List<SMSMessageResponse> messages) {
        this.messages = messages;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
