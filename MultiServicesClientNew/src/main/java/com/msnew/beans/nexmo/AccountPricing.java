package com.msnew.beans.nexmo;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created with IntelliJ IDEA.
 * User: Ovi
 * Date: 6/12/13
 * Time: 8:18 PM
 * To change this template use File | Settings | File Templates.
 */
@XmlRootElement(name = "outbound-country-pricing")
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountPricing {

    @JsonProperty
    private String country;
    @JsonProperty
    private String name;
    @JsonProperty
    private String prefix;
    @JsonProperty
    private String mt;

    @XmlElement
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @XmlElement
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlElement
    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    @XmlElement
    public String getMt() {
        return mt;
    }

    public void setMt(String mt) {
        this.mt = mt;
    }
}
