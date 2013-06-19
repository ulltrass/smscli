package com.msnew.beans.nexmo;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created with IntelliJ IDEA.
 * User: Ovi
 * Date: 6/11/13
 * Time: 11:51 PM
 * To change this template use File | Settings | File Templates.
 */
@XmlRootElement(name = "accountBalance")
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountBalance{

    @JsonProperty
    private String value;
    @JsonProperty
    private String autoReload;

    @XmlElement
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @XmlElement
    public String getAutoReload() {
        return autoReload;
    }

    public void setAutoReload(String autoReload) {
        this.autoReload = autoReload;
    }
}
