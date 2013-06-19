package com.msnew.beans.nexmo;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: opetridean
 * Date: 6/17/13
 * Time: 3:46 PM
 * To change this template use File | Settings | File Templates.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountNumbers {

    @JsonProperty
    private Integer count;

    @JsonProperty
    List<AccountNumber> numbers;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<AccountNumber> getNumbers() {
        return numbers;
    }

    public void setNumbers(List<AccountNumber> numbers) {
        this.numbers = numbers;
    }
}
