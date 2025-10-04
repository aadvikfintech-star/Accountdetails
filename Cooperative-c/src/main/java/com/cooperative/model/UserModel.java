package com.cooperative.model;

public class UserModel {
	Long userId;
	String userName;
	String password;
	String firstName;
	String middleName;
	String lastName;
	String mobileNo;
	String email;
	Long roleId;
	Long copId;
	String otp;
	String otp_expiry;

	String copName;
	String copLogo;
	
	Double amount;
	String walletAmount;

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	public String getOtp_expiry() {
		return otp_expiry;
	}

	public void setOtp_expiry(String otp_expiry) {
		this.otp_expiry = otp_expiry;
	}

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

	public String getCopLogo() {
		return copLogo;
	}

	public void setCopLogo(String copLogo) {
		this.copLogo = copLogo;
	}

	public String getWalletAmount() {
		return walletAmount;
	}

	public void setWalletAmount(String walletAmount) {
		this.walletAmount = walletAmount;
	}
}
