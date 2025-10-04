package com.cooperative.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@NamedQueries({
		@NamedQuery(name = "fetchUserList", query = "select u from User u order by userId"),
		@NamedQuery(name = "checkUser", query = "select u from User u where userName =:userName and password =:password"),
		@NamedQuery(name = "checkUser1", query = "select u from User u order by userId "), })
@Entity
@Table(name = "tbl_user")
public class User {
	@Id
	@SequenceGenerator(name = "tbl_user_seq", sequenceName = "tbl_user_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tbl_user_seq")
	@Column(name = "user_id")
	// db column name
	private Long userId; // java varibale name

	@Column(name = "user_name", nullable = false)
	private String userName;

	@Column(name = "password", nullable = false)
	private String password;

	@Column(name = "first_name", nullable = false)
	private String firstName;

	@Column(name = "middle_name")
	private String middleName;

	@Column(name = "last_name", nullable = false)
	private String lastName;

	@Column(name = "mobile_no")
	private String mobileNo;

	@Column(name = "email")
	private String email;

	@Column(name = "otp")
	private String otp;

	@Column(name = "otp_expiry_date")
	private Date otpExpiryDate;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "role_id", referencedColumnName = "role_id")
	private Role roleId;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "cop_id", referencedColumnName = "cop_id")
	private Cooperative copId;

	@Column(name = "is_active")
	// db column name
	private Character isActive; // java varibale name

	@Column(name = "created_by")
	private Long createdBy;

	@Column(name = "created_date")
	private Date createdDate;

	@Column(name = "updated_by")
	private Long updatedBy;

	@Column(name = "updated_date")
	private Date updatedDate;
	
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

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	public Date getOtpExpiryDate() {
		return otpExpiryDate;
	}

	public void setOtpExpiryDate(Date otpExpiryDate) {
		this.otpExpiryDate = otpExpiryDate;
	}

	public Role getRoleId() {
		return roleId;
	}

	public void setRoleId(Role roleId) {
		this.roleId = roleId;
	}

	public Cooperative getCopId() {
		return copId;
	}

	public void setCopId(Cooperative copId) {
		this.copId = copId;
	}

	public Character getIsActive() {
		return isActive;
	}

	public void setIsActive(Character isActive) {
		this.isActive = isActive;
	}

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Long getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

}
