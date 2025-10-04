package com.cooperative.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountDetailsData {

    private String client_id;
    private String mobile_number;
    private List<AccountDetail> details;

    public AccountDetailsData() {
    }

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public String getMobile_number() {
        return mobile_number;
    }

    public void setMobile_number(String mobile_number) {
        this.mobile_number = mobile_number;
    }

    public List<AccountDetail> getDetails() {
        return details;
    }

    public void setDetails(List<AccountDetail> details) {
        this.details = details;
    }
}
