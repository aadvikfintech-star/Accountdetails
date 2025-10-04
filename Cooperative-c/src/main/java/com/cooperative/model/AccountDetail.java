package com.cooperative.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountDetail {

    private String full_name;
    private String account_number;
    private String ifsc;

    public AccountDetail() {
    }

    public AccountDetail(String full_name, String account_number, String ifsc) {
        this.full_name = full_name;
        this.account_number = account_number;
        this.ifsc = ifsc;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getAccount_number() {
        return account_number;
    }

    public void setAccount_number(String account_number) {
        this.account_number = account_number;
    }

    public String getIfsc() {
        return ifsc;
    }

    public void setIfsc(String ifsc) {
        this.ifsc = ifsc;
    }
}
