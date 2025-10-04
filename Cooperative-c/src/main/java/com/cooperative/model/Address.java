package com.cooperative.model;

public class Address {
	private String po;
	private String dist;
	private String state;
	private String country;

	public String getPo() {
		return po;
	}

	public void setPo(String po) {
		this.po = po;
	}

	public String getDist() {
		return dist;
	}

	public void setDist(String dist) {
		this.dist = dist;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Override
	public String toString() {
		return "At/Post:" + po + ", Dist:" + dist + ", State:" + state
				+ ", Country=" + country;
	}

}
