package com.cooperative.model;

import org.springframework.web.multipart.MultipartFile;

public class CooperativeModel {

	private Long copId;
	private String copName;
	private String copRegNo;
	private String copAddress;
	private Character isActive;
	
	private MultipartFile logo;
	
	String logoBytes;
	
	public Long getCopId() {
		return copId;
	}
	public void setCopId(Long copId) {
		this.copId = copId;
	}
	public String getCopName() {
		return copName;
	}
	public void setCopName(String copName) {
		this.copName = copName;
	}
	public String getCopRegNo() {
		return copRegNo;
	}
	public void setCopRegNo(String copRegNo) {
		this.copRegNo = copRegNo;
	}
	public String getCopAddress() {
		return copAddress;
	}
	public void setCopAddress(String copAddress) {
		this.copAddress = copAddress;
	}
	public MultipartFile getLogo() {
		return logo;
	}
	public void setLogo(MultipartFile logo) {
		this.logo = logo;
	}
	public String getLogoBytes() {
		return logoBytes;
	}
	public void setLogoBytes(String logoBytes) {
		this.logoBytes = logoBytes;
	}
	public Character getIsActive() {
		return isActive;
	}
	public void setIsActive(Character isActive) {
		this.isActive = isActive;
	}
	
}
