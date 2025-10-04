package com.cooperative.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public  class CibilData {
	private String client_id;
	private String name;
	private String mobile;
	private String pan;
	private String credit_score;
	private String credit_report_link;
	
	
	public CibilData(String client_id, String name, String mobile, String pan) {
		super();
		this.client_id = client_id;
		this.name = name;
		this.mobile = mobile;
		this.pan = pan;
	}
	
	public String getClient_id() {
		return client_id;
	}
	public void setClient_id(String client_id) {
		this.client_id = client_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getPan() {
		return pan;
	}
	public void setPan(String pan) {
		this.pan = pan;
	}
	public String getCredit_score() {
		return credit_score;
	}
	public void setCredit_score(String credit_score) {
		this.credit_score = credit_score;
	}
	public String getCredit_report_link() {
		return credit_report_link;
	}
	public void setCredit_report_link(String credit_report_link) {
		this.credit_report_link = credit_report_link;
	}
}
