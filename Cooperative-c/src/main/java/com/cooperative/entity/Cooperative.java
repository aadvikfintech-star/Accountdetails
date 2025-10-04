package com.cooperative.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@NamedQueries({
		@NamedQuery(name = "fetchCooprativeList", query = "select c from Cooperative c order by copId"),
		@NamedQuery(name = "fetchActiveCooperativeList", query = "select c from Cooperative c where isActive = 'Y' order by copId"), })
@Entity
@Table(name = "tbl_cooperative")
public class Cooperative {
	@Id
	@SequenceGenerator(name = "tbl_cooperative_seq", sequenceName = "tbl_cooperative_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tbl_cooperative_seq")
	@Column(name = "cop_id")
	// db column name
	private Long copId; // java varibale name

	@Column(name = "cop_name", nullable = false)
	private String copName;

	@Column(name = "cop_address")
	private String copAddress;

	@Column(name = "cop_reg_no")
	private String copRegNo;

	@Column(name = "cop_logo")
	private String copLogo;

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

	public String getCopAddress() {
		return copAddress;
	}

	public void setCopAddress(String copAddress) {
		this.copAddress = copAddress;
	}

	public String getCopRegNo() {
		return copRegNo;
	}

	public void setCopRegNo(String copRegNo) {
		this.copRegNo = copRegNo;
	}

	public String getCopLogo() {
		return copLogo;
	}

	public void setCopLogo(String copLogo) {
		this.copLogo = copLogo;
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
