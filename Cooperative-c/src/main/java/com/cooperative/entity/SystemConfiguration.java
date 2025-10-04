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
	@NamedQuery(name="findByConfigType",query="select s from SystemConfiguration s where s.sysType=:sysType and isActive ='Y' order by id"),
})

@Entity
@Table(name = "system_configuration")
public class SystemConfiguration  {
	@Id
	@SequenceGenerator(name = "sys_config_seq", sequenceName = "sys_config_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sys_config_seq")
	@Column(name = "id") //db column name
	private Long id; //java varibale name
	
	@Column(name = "sys_type", nullable = false)
	private String sysType;
	
	@Column(name = "sys_name", nullable = false)
	private String sysName;
	
	@Column(name = "sys_name_mr", nullable = false)
	private String sysNameMr;
	
	@Column(name = "is_active") //db column name
	private Character isActive; //java varibale name
	
	@Column(name = "api_url")
	private String apiURL;
	
	@Column(name = "api_charges")
	private Double apiCharges;
	
	@Column(name = "created_by")
	private Long createdBy;
	
	@Column(name = "created_date")
	private Date createdDate;
	
	@Column(name = "updated_by")
	private Long updatedBy;
	
	@Column(name = "updated_date")
	private Date updatedDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSysType() {
		return sysType;
	}

	public void setSysType(String sysType) {
		this.sysType = sysType;
	}

	public String getSysName() {
		return sysName;
	}

	public void setSysName(String sysName) {
		this.sysName = sysName;
	}

	public String getSysNameMr() {
		return sysNameMr;
	}

	public void setSysNameMr(String sysNameMr) {
		this.sysNameMr = sysNameMr;
	}

	public Character getIsActive() {
		return isActive;
	}

	public void setIsActive(Character isActive) {
		this.isActive = isActive;
	}
	
	public String getApiURL() {
		return apiURL;
	}

	public void setApiURL(String apiURL) {
		this.apiURL = apiURL;
	}
	

	public Double getApiCharges() {
		return apiCharges;
	}

	public void setApiCharges(Double apiCharges) {
		this.apiCharges = apiCharges;
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
